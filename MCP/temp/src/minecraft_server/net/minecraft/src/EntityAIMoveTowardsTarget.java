package net.minecraft.src;

import net.minecraft.src.EntityAIBase;
import net.minecraft.src.EntityCreature;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.RandomPositionGenerator;
import net.minecraft.src.Vec3;

public class EntityAIMoveTowardsTarget extends EntityAIBase {

   private EntityCreature field_75431_a;
   private EntityLiving field_75429_b;
   private double field_75430_c;
   private double field_75427_d;
   private double field_75428_e;
   private float field_75425_f;
   private float field_75426_g;


   public EntityAIMoveTowardsTarget(EntityCreature p_i3481_1_, float p_i3481_2_, float p_i3481_3_) {
      this.field_75431_a = p_i3481_1_;
      this.field_75425_f = p_i3481_2_;
      this.field_75426_g = p_i3481_3_;
      this.func_75248_a(1);
   }

   public boolean func_75250_a() {
      this.field_75429_b = this.field_75431_a.func_70638_az();
      if(this.field_75429_b == null) {
         return false;
      } else if(this.field_75429_b.func_70068_e(this.field_75431_a) > (double)(this.field_75426_g * this.field_75426_g)) {
         return false;
      } else {
         Vec3 var1 = RandomPositionGenerator.func_75464_a(this.field_75431_a, 16, 7, Vec3.func_72437_a().func_72345_a(this.field_75429_b.field_70165_t, this.field_75429_b.field_70163_u, this.field_75429_b.field_70161_v));
         if(var1 == null) {
            return false;
         } else {
            this.field_75430_c = var1.field_72450_a;
            this.field_75427_d = var1.field_72448_b;
            this.field_75428_e = var1.field_72449_c;
            return true;
         }
      }
   }

   public boolean func_75253_b() {
      return !this.field_75431_a.func_70661_as().func_75500_f() && this.field_75429_b.func_70089_S() && this.field_75429_b.func_70068_e(this.field_75431_a) < (double)(this.field_75426_g * this.field_75426_g);
   }

   public void func_75251_c() {
      this.field_75429_b = null;
   }

   public void func_75249_e() {
      this.field_75431_a.func_70661_as().func_75492_a(this.field_75430_c, this.field_75427_d, this.field_75428_e, this.field_75425_f);
   }
}
