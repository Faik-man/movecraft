package net.minecraft.src;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import java.util.Random;

public class BlockRedstoneLight extends Block
{
    /** Whether this lamp block is the powered version. */
    private final boolean powered;

    public BlockRedstoneLight(int par1, boolean par2)
    {
        super(par1, 211, Material.redstoneLight);
        this.powered = par2;

        if (par2)
        {
            this.setLightValue(1.0F);
            ++this.blockIndexInTexture;
        }
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        if (!par1World.isRemote)
        {
            if (this.powered && !par1World.isBlockIndirectlyGettingPowered(par2, par3, par4))
            {
                par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, 4);
            }
            else if (!this.powered && par1World.isBlockIndirectlyGettingPowered(par2, par3, par4))
            {
                par1World.setBlockWithNotify(par2, par3, par4, Block.redstoneLampActive.blockID);
            }
        }
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World par1World, int x, int y, int z, int neighborId)
    {
        if (!par1World.isRemote)
        {
            if (this.powered && !par1World.isBlockIndirectlyGettingPowered(x, y, z))
            {
                par1World.scheduleBlockUpdate(x, y, z, this.blockID, 4);
            }
            else if (!this.powered && par1World.isBlockIndirectlyGettingPowered(x, y, z))
            {
                par1World.setBlockWithNotify(x, y, z, Block.redstoneLampActive.blockID);
            }
        }
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (!par1World.isRemote && this.powered && !par1World.isBlockIndirectlyGettingPowered(par2, par3, par4))
        {
            par1World.setBlockWithNotify(par2, par3, par4, Block.redstoneLampIdle.blockID);
        }
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return Block.redstoneLampIdle.blockID;
    }

    @SideOnly(Side.CLIENT)

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return Block.redstoneLampIdle.blockID;
    }
}
