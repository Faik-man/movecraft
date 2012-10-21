package psetmaj.common;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "psetmaj", name = "moveCraft", version="0.0.1")
@NetworkMod(clientSideRequired=true,serverSideRequired=false)
public class mod_BaseMod 
{
	public static Block SoulStoneIdle;
	public static Block SoulStonePowered;
	public static Block ScratchBlock;
	
	@Init
	public void load(FMLInitializationEvent event)
	{
		SoulStoneIdle = (new SoulStoneBlock(255,104,false)).setBlockName("SoulStone").setHardness(1F).setResistance(5F);
		SoulStonePowered = (new SoulStoneBlock(254,103,true)).setBlockName("SoulStone").setHardness(1F).setResistance(5F);
		ScratchBlock = (new ScratchBlock(253,102)).setBlockName("ScratchBlock").setHardness(1F).setResistance(5F);
		
		GameRegistry.registerBlock(SoulStoneIdle);
		GameRegistry.registerBlock(SoulStonePowered);
		GameRegistry.registerBlock(ScratchBlock);

		LanguageRegistry.addName(SoulStoneIdle, "Soul Stone");
		LanguageRegistry.addName(ScratchBlock, "Scratch Block");
		
		GameRegistry.addRecipe(new ItemStack(SoulStoneIdle), new Object[] {
			"SRS",
			"RSR",
			"SRS",
			'S',Block.slowSand,
			'R',Item.redstone
		});
	}
}
