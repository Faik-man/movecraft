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
	//Blocks
	public static Block SoulStoneIdle;
	public static Block SoulStonePowered;
	public static Block JumpBlock;
	public static Block ScratchBlock;
	
	//Items
	public static Item FloatFeather;
	
	@Init
	public void load(FMLInitializationEvent event)
	{
		//Blocks
		SoulStoneIdle = (new SoulStoneBlock(255,104,false)).setBlockName("SoulStone").setHardness(1F).setResistance(5F);
		SoulStonePowered = (new SoulStoneBlock(254,103,true)).setBlockName("SoulStone").setHardness(1F).setResistance(5F);
		JumpBlock = (new JumpBlock(253,102)).setBlockName("JumpBlock").setHardness(1F).setResistance(5F);
		ScratchBlock = (new ScratchBlock(252,101)).setBlockName("Bounce Block").setHardness(1F).setResistance(5F);
		
		//Items
		FloatFeather = (new FloatingFeather(550)).setItemName("FloatingFeather").setIconCoord(8, 1);
		
		//Blocks
		GameRegistry.registerBlock(SoulStoneIdle);
		GameRegistry.registerBlock(SoulStonePowered);
		GameRegistry.registerBlock(JumpBlock);
		
		//Name Addition
		LanguageRegistry.addName(SoulStoneIdle, "Soul Stone");
		LanguageRegistry.addName(JumpBlock, "Jump Block");
		LanguageRegistry.addName(FloatFeather, "Floating Feather");
		LanguageRegistry.addName(ScratchBlock, "Bounce Block");
		
		GameRegistry.addRecipe(new ItemStack(SoulStoneIdle), new Object[] {
			"SRS",
			"RSR", 
			"SRS",
			'S',Block.slowSand,
			'R',Item.redstone
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(FloatFeather,6),Item.feather,Item.lightStoneDust);
	}
}
