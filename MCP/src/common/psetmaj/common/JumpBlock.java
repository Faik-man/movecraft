package psetmaj.common;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Entity;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class JumpBlock extends Block {

	public JumpBlock(int id, int textureID) {
		super(id,textureID, Material.rock);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
			entity.fallDistance = -10f;
		entity.motionY += 2D;
	}
	
    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        float var5 = 0.125F;
        return AxisAlignedBB.getAABBPool().addOrModifyAABBInPool((double)par2, (double)par3, (double)par4, (double)(par2 + 1), (double)((float)(par3 + 1) - var5), (double)(par4 + 1));
    }
}
