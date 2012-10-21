package net.minecraft.src;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.StepSound;

final class StepSoundStone extends StepSound {

   StepSoundStone(String p_i4006_1_, float p_i4006_2_, float p_i4006_3_) {
      super(p_i4006_1_, p_i4006_2_, p_i4006_3_);
   }

   @SideOnly(Side.CLIENT)
   public String func_72676_a() {
      return "random.glass";
   }
}
