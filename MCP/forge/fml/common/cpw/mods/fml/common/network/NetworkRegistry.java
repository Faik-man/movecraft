package cpw.mods.fml.common.network;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

import net.minecraft.server.MinecraftServer;
import net.minecraft.src.Container;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.NetHandler;
import net.minecraft.src.NetLoginHandler;
import net.minecraft.src.NetServerHandler;
import net.minecraft.src.NetworkManager;
import net.minecraft.src.Packet1Login;
import net.minecraft.src.Packet250CustomPayload;
import net.minecraft.src.TcpConnection;
import net.minecraft.src.World;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.network.FMLPacket.Type;

public class NetworkRegistry
{

    private static final NetworkRegistry INSTANCE = new NetworkRegistry();
    /**
     * A map of active channels per player
     */
    private Multimap<Player, String> activeChannels = ArrayListMultimap.create();
    /**
     * A map of the packet handlers for packets
     */
    private Multimap<String, IPacketHandler> universalPacketHandlers = ArrayListMultimap.create();
    private Multimap<String, IPacketHandler> clientPacketHandlers = ArrayListMultimap.create();
    private Multimap<String, IPacketHandler> serverPacketHandlers = ArrayListMultimap.create();
    /**
     * A linked set of registered connection handlers
     */
    private Set<IConnectionHandler> connectionHandlers = Sets.newLinkedHashSet();
    private Map<ModContainer, IGuiHandler> serverGuiHandlers = Maps.newHashMap();
    private Map<ModContainer, IGuiHandler> clientGuiHandlers = Maps.newHashMap();

    public static NetworkRegistry instance()
    {
        return INSTANCE;
    }
    /**
     * Get the packet 250 channel registration string
     * @return
     */
    byte[] getPacketRegistry(Side side)
    {
        return Joiner.on('\0').join(Iterables.concat(Arrays.asList("FML"),universalPacketHandlers.keySet(), side.isClient() ? clientPacketHandlers.keySet() : serverPacketHandlers.keySet())).getBytes(Charsets.UTF_8);
    }
    /**
     * Is the specified channel active for the player?
     * @param channel
     * @param player
     * @return
     */
    public boolean isChannelActive(String channel, Player player)
    {
        return activeChannels.containsEntry(player,channel);
    }
    /**
     * register a channel to a mod
     * @param container
     * @param channelName
     */
    public void registerChannel(IPacketHandler handler, String channelName)
    {
        universalPacketHandlers.put(channelName, handler);
    }

    public void registerChannel(IPacketHandler handler, String channelName, Side side)
    {
        if (side.isClient())
        {
            clientPacketHandlers.put(channelName, handler);
        }
        else
        {
            serverPacketHandlers.put(channelName, handler);
        }
    }
    /**
     * Activate the channel for the player
     * @param player
     */
    void activateChannel(Player player, String channel)
    {
        activeChannels.put(player, channel);
    }
    /**
     * Deactivate the channel for the player
     * @param player
     * @param channel
     */
    void deactivateChannel(Player player, String channel)
    {
        activeChannels.remove(player, channel);
    }
    /**
     * Register a connection handler
     *
     * @param handler
     */
    public void registerConnectionHandler(IConnectionHandler handler)
    {
        connectionHandlers.add(handler);
    }

    void playerLoggedIn(EntityPlayerMP player, NetServerHandler netHandler, NetworkManager manager)
    {
        generateChannelRegistration(player, netHandler, manager);
        for (IConnectionHandler handler : connectionHandlers)
        {
            handler.playerLoggedIn((Player)player, netHandler, manager);
        }
    }

    String connectionReceived(NetLoginHandler netHandler, NetworkManager manager)
    {
        for (IConnectionHandler handler : connectionHandlers)
        {
            String kick = handler.connectionReceived(netHandler, manager);
            if (!Strings.isNullOrEmpty(kick))
            {
                return kick;
            }
        }
        return null;
    }

    void connectionOpened(NetHandler netClientHandler, String server, int port, NetworkManager networkManager)
    {
        for (IConnectionHandler handler : connectionHandlers)
        {
            handler.connectionOpened(netClientHandler, server, port, networkManager);
        }
    }

    void connectionOpened(NetHandler netClientHandler, MinecraftServer server, NetworkManager networkManager)
    {
        for (IConnectionHandler handler : connectionHandlers)
        {
            handler.connectionOpened(netClientHandler, server, networkManager);
        }
    }

    void clientLoggedIn(NetHandler clientHandler, NetworkManager manager, Packet1Login login)
    {
        generateChannelRegistration(clientHandler.getPlayer(), clientHandler, manager);
        for (IConnectionHandler handler : connectionHandlers)
        {
            handler.clientLoggedIn(clientHandler, manager, login);
        }
    }

    void connectionClosed(NetworkManager manager)
    {
        for (IConnectionHandler handler : connectionHandlers)
        {
            handler.connectionClosed(manager);
        }
    }

    void generateChannelRegistration(EntityPlayer player, NetHandler netHandler, NetworkManager manager)
    {
        Packet250CustomPayload pkt = new Packet250CustomPayload();
        pkt.field_73630_a = "REGISTER";
        pkt.field_73629_c = getPacketRegistry(player instanceof EntityPlayerMP ? Side.SERVER : Side.CLIENT);
        pkt.field_73628_b = pkt.field_73629_c.length;
        manager.func_74429_a(pkt);
    }

    void handleCustomPacket(Packet250CustomPayload packet, NetworkManager network, NetHandler handler)
    {
        if ("REGISTER".equals(packet.field_73630_a))
        {
            handleRegistrationPacket(packet, (Player)handler.getPlayer());
        }
        else if ("UNREGISTER".equals(packet.field_73630_a))
        {
            handleUnregistrationPacket(packet, (Player)handler.getPlayer());
        }
        else
        {
            handlePacket(packet, network, (Player)handler.getPlayer());
        }
    }


    private void handlePacket(Packet250CustomPayload packet, NetworkManager network, Player player)
    {
        String channel = packet.field_73630_a;
        if (activeChannels.containsEntry(player, channel))
        {
            for (IPacketHandler handler : Iterables.concat(universalPacketHandlers.get(channel), player instanceof EntityPlayerMP ? serverPacketHandlers.get(channel) : clientPacketHandlers.get(channel)))
            {
                handler.onPacketData(network, packet, player);
            }
        }
    }

    private void handleRegistrationPacket(Packet250CustomPayload packet, Player player)
    {
        List<String> channels = extractChannelList(packet);
        for (String channel : channels)
        {
            activateChannel(player, channel);
        }
    }
    private void handleUnregistrationPacket(Packet250CustomPayload packet, Player player)
    {
        List<String> channels = extractChannelList(packet);
        for (String channel : channels)
        {
            deactivateChannel(player, channel);
        }
    }
    /**
     * @param packet
     * @return
     */
    private List<String> extractChannelList(Packet250CustomPayload packet)
    {
        String request = new String(packet.field_73629_c, Charsets.UTF_8);
        List<String> channels = Lists.newArrayList(Splitter.on('\0').split(request));
        return channels;
    }

    public void registerGuiHandler(Object mod, IGuiHandler handler)
    {
        ModContainer mc = FMLCommonHandler.instance().findContainerFor(mod);
        NetworkModHandler nmh = FMLNetworkHandler.instance().findNetworkModHandler(mc);
        if (nmh == null)
        {
            FMLLog.log(Level.FINE, "The mod %s needs to be a @NetworkMod to register a Networked Gui Handler", mc.getModId());
        }
        else
        {
            serverGuiHandlers.put(mc, handler);
        }
        clientGuiHandlers.put(mc, handler);
    }
    void openRemoteGui(ModContainer mc, EntityPlayerMP player, int modGuiId, World world, int x, int y, int z)
    {
        IGuiHandler handler = serverGuiHandlers.get(mc);
        NetworkModHandler nmh = FMLNetworkHandler.instance().findNetworkModHandler(mc);
        if (handler != null && nmh != null)
        {
            Container container = (Container)handler.getServerGuiElement(modGuiId, player, world, x, y, z);
            if (container != null)
            {
                player.func_71117_bO();
                player.func_71128_l();
                int windowId = player.field_71139_cq;
                Packet250CustomPayload pkt = new Packet250CustomPayload();
                pkt.field_73630_a = "FML";
                pkt.field_73629_c = FMLPacket.makePacket(Type.GUIOPEN, windowId, nmh.getNetworkId(), modGuiId, x, y, z);
                pkt.field_73628_b = pkt.field_73629_c.length;
                player.field_71135_a.func_72567_b(pkt);
                player.field_71070_bA = container;
                player.field_71070_bA.field_75152_c = windowId;
                player.field_71070_bA.func_75132_a(player);
            }
        }
    }
    void openLocalGui(ModContainer mc, EntityPlayer player, int modGuiId, World world, int x, int y, int z)
    {
        IGuiHandler handler = clientGuiHandlers.get(mc);
        FMLCommonHandler.instance().showGuiScreen(handler.getClientGuiElement(modGuiId, player, world, x, y, z));
    }
}
