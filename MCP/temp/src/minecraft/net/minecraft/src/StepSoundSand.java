package net.minecraft.src;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.StepSound;

final class StepSoundSand extends StepSound {

   StepSoundSand(String p_i4007_1_, float p_i4007_2_, float p_i4007_3_) {
      super(p_i4007_1_, p_i4007_2_, p_i4007_3_);
   }

   @SideOnly(Side.CLIENT)
   public String func_72676_a() {
      return "step.gravel";
   }
}
