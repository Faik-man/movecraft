package psetmaj.common;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class FloatingFeather extends Item {
	
	public FloatingFeather(int id) {
		super(id);
		this.maxStackSize = 64;
		this.setTabToDisplayOn(CreativeTabs.tabTransport);
	}
	
	@Override
	public boolean hasEffect(ItemStack par1ItemStack) {
		return true;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
		player.motionY += .5D;
		player.fallDistance = 0;
		itemStack.stackSize--;
		return itemStack;
	}
	
	

}
