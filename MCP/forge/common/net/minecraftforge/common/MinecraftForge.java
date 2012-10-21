package net.minecraftforge.common;

import java.lang.reflect.Constructor;
import java.util.*;

import net.minecraft.src.*;
import net.minecraftforge.common.ForgeHooks.GrassEntry;
import net.minecraftforge.common.ForgeHooks.SeedEntry;
import net.minecraftforge.event.EventBus;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityEvent;

public class MinecraftForge
{
    /**
     * The core Forge EventBus, all events for Forge will be fired on this, 
     * you should use this to register all your listeners.
     * This replaces every register*Handler() function in the old version of Forge.
     */
    public static final EventBus EVENT_BUS = new EventBus();
    public static boolean SPAWNER_ALLOW_ON_INVERTED = false;
    
    
    /** Register a new plant to be planted when bonemeal is used on grass.
     * @param block The block to place.
     * @param metadata The metadata to set for the block when being placed.
     * @param weight The weight of the plant, where red flowers are
     *               10 and yellow flowers are 20.
     */
    public static void addGrassPlant(Block block, int metadata, int weight)
    {
        ForgeHooks.grassList.add(new GrassEntry(block, metadata, weight));
    }

    /** 
     * Register a new seed to be dropped when breaking tall grass.
     * 
     * @param seed The item to drop as a seed.
     * @param weight The relative probability of the seeds, 
     *               where wheat seeds are 10.
     */
    public static void addGrassSeed(ItemStack seed, int weight)
    {
        ForgeHooks.seedList.add(new SeedEntry(seed, weight));
    }
    
    /** 
     * 
     * Register a tool as a tool class with a given harvest level.
     *
     * @param tool The custom tool to register.
     * @param toolClass The tool class to register as.  The predefined tool
     *                  clases are "pickaxe", "shovel", "axe".  You can add 
     *                  others for custom tools.
     * @param harvestLevel The harvest level of the tool.
     */
   public static void setToolClass(Item tool, String toolClass, int harvestLevel)
   {
       ForgeHooks.toolClasses.put(tool, Arrays.asList(toolClass, harvestLevel));
   }

   /** 
    * Register a block to be harvested by a tool class.  This is the metadata
    * sensitive version, use it if your blocks are using metadata variants.
    * By default, this sets the block class as effective against that type.
    *
    * @param block The block to register.
    * @param metadata The metadata for the block subtype.
    * @param toolClass The tool class to register as able to remove this block.
    *                  You may register the same block multiple times with different tool
    *                  classes, if multiple tool types can be used to harvest this block.
    * @param harvestLevel The minimum tool harvest level required to successfully
    * harvest the block.
    * @see setToolClass for details on tool classes.
    */
   public static void setBlockHarvestLevel(Block block, int metadata, String toolClass, int harvestLevel)
   {
       List key = Arrays.asList(block, metadata, toolClass);
       ForgeHooks.toolHarvestLevels.put(key, harvestLevel);
       ForgeHooks.toolEffectiveness.add(key);
   }

   /** 
    * Remove a block effectiveness mapping.  Since setBlockHarvestLevel
    * makes the tool class effective against the block by default, this can be
    * used to remove that mapping.  This will force a block to be harvested at
    * the same speed regardless of tool quality, while still requiring a given
    * harvesting level.
    * 
    * @param block The block to remove effectiveness from.
    * @param metadata The metadata for the block subtype.
    * @param toolClass The tool class to remove the effectiveness mapping from.
    * @see setToolClass for details on tool classes.
    */
   public static void removeBlockEffectiveness(Block block, int metadata, String toolClass)
   {
       List key = Arrays.asList(block, metadata, toolClass);
       ForgeHooks.toolEffectiveness.remove(key);
   }

   /** 
    * Register a block to be harvested by a tool class.
    * By default, this sets the block class as effective against that type.
    *
    * @param block The block to register.
    * @param toolClass The tool class to register as able to remove this block.
    *                  You may register the same block multiple times with different tool
    *                  classes, if multiple tool types can be used to harvest this block.
    * @param harvestLevel The minimum tool harvest level required to successfully
    *                     harvest the block.
    * @see setToolClass for details on tool classes.
    */
   public static void setBlockHarvestLevel(Block block, String toolClass, int harvestLevel)
   {
       for (int metadata = 0; metadata < 16; metadata++)
       {
           List key = Arrays.asList(block, metadata, toolClass);
           ForgeHooks.toolHarvestLevels.put(key, harvestLevel);
           ForgeHooks.toolEffectiveness.add(key);
       }
   }
   
   /** 
    * Returns the block harvest level for a particular tool class.
    *
    * @param block The block to check.
    * @param metadata The metadata for the block subtype.
    * @param toolClass The tool class to check as able to remove this block.
    * @see setToolClass for details on tool classes.
    * @return The harvest level or -1 if no mapping exists.
    */
   public static int getBlockHarvestLevel(Block block, int metadata, String toolClass)
   {
       ForgeHooks.initTools();
       List key = Arrays.asList(block, metadata, toolClass);
       Integer harvestLevel = (Integer)ForgeHooks.toolHarvestLevels.get(key);
       if(harvestLevel == null)
       {
           return -1;
       }
       return harvestLevel;
   }

   /** 
    * Remove a block effectiveness mapping.  Since setBlockHarvestLevel
    * makes the tool class effective against the block by default, this can be
    * used to remove that mapping.  This will force a block to be harvested at
    * the same speed regardless of tool quality, while still requiring a given
    * harvesting level.
    * 
    * @param block The block to remove effectiveness from.
    * @param toolClass The tool class to remove the effectiveness mapping from.
    * @see setToolClass for details on tool classes.
    */
   public static void removeBlockEffectiveness(Block block, String toolClass)
   {
       for (int metadata = 0; metadata < 16; metadata++)
       {
           List key = Arrays.asList(block, metadata, toolClass);
           ForgeHooks.toolEffectiveness.remove(key);
       }
   }
   
   /**
    * Method invoked by FML before any other mods are loaded.
    */
   public static void initialize()
   {
       //Cause the classes to initialize if they already haven't
       Block.stone.getTextureFile();
       Item.appleGold.getTextureFile();
       
       Block filler = null;
       try 
       {
           Constructor ctr = Block.class.getDeclaredConstructor(int.class, Material.class);
           ctr.setAccessible(true);
           filler = (Block)ctr.newInstance(256, Material.air);
       }
       catch (Exception e)
       {
           RuntimeException rte = new RuntimeException("Could not create Forge filler block", e);
           rte.printStackTrace();
           System.exit(1);
       }

       for (int x = 256; x < 4096; x++)
       {
           if (Item.itemsList[x - 256] != null)
           {
               Block.blocksList[x] = filler;
           }
       }
       
       boolean[] temp = new boolean[4096];
       for (int x = 0; x < EntityEnderman.carriableBlocks.length; x++)
       {
           temp[x] = EntityEnderman.carriableBlocks[x];
       }
       EntityEnderman.carriableBlocks = temp;
   }
   
   public static String getBrandingVersion()
   {
       return "Minecraft Forge "+ ForgeVersion.getVersion();
   }
}
