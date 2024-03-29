package net.minecraft.src;

import java.util.Random;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenBigMushroom;
import net.minecraft.src.WorldGenCactus;
import net.minecraft.src.WorldGenClay;
import net.minecraft.src.WorldGenDeadBush;
import net.minecraft.src.WorldGenFlowers;
import net.minecraft.src.WorldGenLiquids;
import net.minecraft.src.WorldGenMinable;
import net.minecraft.src.WorldGenPumpkin;
import net.minecraft.src.WorldGenReed;
import net.minecraft.src.WorldGenSand;
import net.minecraft.src.WorldGenWaterlily;
import net.minecraft.src.WorldGenerator;

public class BiomeDecorator {

   protected World field_76815_a;
   protected Random field_76813_b;
   protected int field_76814_c;
   protected int field_76811_d;
   protected BiomeGenBase field_76812_e;
   protected WorldGenerator field_76809_f = new WorldGenClay(4);
   protected WorldGenerator field_76810_g;
   protected WorldGenerator field_76822_h;
   protected WorldGenerator field_76823_i;
   protected WorldGenerator field_76820_j;
   protected WorldGenerator field_76821_k;
   protected WorldGenerator field_76818_l;
   protected WorldGenerator field_76819_m;
   protected WorldGenerator field_76816_n;
   protected WorldGenerator field_76817_o;
   protected WorldGenerator field_76831_p;
   protected WorldGenerator field_76830_q;
   protected WorldGenerator field_76829_r;
   protected WorldGenerator field_76828_s;
   protected WorldGenerator field_76827_t;
   protected WorldGenerator field_76826_u;
   protected WorldGenerator field_76825_v;
   protected WorldGenerator field_76824_w;
   protected WorldGenerator field_76834_x;
   protected int field_76833_y;
   protected int field_76832_z;
   protected int field_76802_A;
   protected int field_76803_B;
   protected int field_76804_C;
   protected int field_76798_D;
   protected int field_76799_E;
   protected int field_76800_F;
   protected int field_76801_G;
   protected int field_76805_H;
   protected int field_76806_I;
   protected int field_76807_J;
   public boolean field_76808_K;


   public BiomeDecorator(BiomeGenBase p_i3750_1_) {
      this.field_76810_g = new WorldGenSand(7, Block.field_71939_E.field_71990_ca);
      this.field_76822_h = new WorldGenSand(6, Block.field_71940_F.field_71990_ca);
      this.field_76823_i = new WorldGenMinable(Block.field_71979_v.field_71990_ca, 32);
      this.field_76820_j = new WorldGenMinable(Block.field_71940_F.field_71990_ca, 32);
      this.field_76821_k = new WorldGenMinable(Block.field_71950_I.field_71990_ca, 16);
      this.field_76818_l = new WorldGenMinable(Block.field_71949_H.field_71990_ca, 8);
      this.field_76819_m = new WorldGenMinable(Block.field_71941_G.field_71990_ca, 8);
      this.field_76816_n = new WorldGenMinable(Block.field_72047_aN.field_71990_ca, 7);
      this.field_76817_o = new WorldGenMinable(Block.field_72073_aw.field_71990_ca, 7);
      this.field_76831_p = new WorldGenMinable(Block.field_71947_N.field_71990_ca, 6);
      this.field_76830_q = new WorldGenFlowers(Block.field_72097_ad.field_71990_ca);
      this.field_76829_r = new WorldGenFlowers(Block.field_72107_ae.field_71990_ca);
      this.field_76828_s = new WorldGenFlowers(Block.field_72109_af.field_71990_ca);
      this.field_76827_t = new WorldGenFlowers(Block.field_72103_ag.field_71990_ca);
      this.field_76826_u = new WorldGenBigMushroom();
      this.field_76825_v = new WorldGenReed();
      this.field_76824_w = new WorldGenCactus();
      this.field_76834_x = new WorldGenWaterlily();
      this.field_76833_y = 0;
      this.field_76832_z = 0;
      this.field_76802_A = 2;
      this.field_76803_B = 1;
      this.field_76804_C = 0;
      this.field_76798_D = 0;
      this.field_76799_E = 0;
      this.field_76800_F = 0;
      this.field_76801_G = 1;
      this.field_76805_H = 3;
      this.field_76806_I = 1;
      this.field_76807_J = 0;
      this.field_76808_K = true;
      this.field_76812_e = p_i3750_1_;
   }

   public void func_76796_a(World p_76796_1_, Random p_76796_2_, int p_76796_3_, int p_76796_4_) {
      if(this.field_76815_a != null) {
         throw new RuntimeException("Already decorating!!");
      } else {
         this.field_76815_a = p_76796_1_;
         this.field_76813_b = p_76796_2_;
         this.field_76814_c = p_76796_3_;
         this.field_76811_d = p_76796_4_;
         this.func_76794_a();
         this.field_76815_a = null;
         this.field_76813_b = null;
      }
   }

   protected void func_76794_a() {
      this.func_76797_b();

      int var1;
      int var2;
      int var3;
      for(var1 = 0; var1 < this.field_76805_H; ++var1) {
         var2 = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
         var3 = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
         this.field_76810_g.func_76484_a(this.field_76815_a, this.field_76813_b, var2, this.field_76815_a.func_72825_h(var2, var3), var3);
      }

      for(var1 = 0; var1 < this.field_76806_I; ++var1) {
         var2 = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
         var3 = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
         this.field_76809_f.func_76484_a(this.field_76815_a, this.field_76813_b, var2, this.field_76815_a.func_72825_h(var2, var3), var3);
      }

      for(var1 = 0; var1 < this.field_76801_G; ++var1) {
         var2 = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
         var3 = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
         this.field_76810_g.func_76484_a(this.field_76815_a, this.field_76813_b, var2, this.field_76815_a.func_72825_h(var2, var3), var3);
      }

      var1 = this.field_76832_z;
      if(this.field_76813_b.nextInt(10) == 0) {
         ++var1;
      }

      int var4;
      for(var2 = 0; var2 < var1; ++var2) {
         var3 = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
         var4 = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
         WorldGenerator var5 = this.field_76812_e.func_76740_a(this.field_76813_b);
         var5.func_76487_a(1.0D, 1.0D, 1.0D);
         var5.func_76484_a(this.field_76815_a, this.field_76813_b, var3, this.field_76815_a.func_72976_f(var3, var4), var4);
      }

      for(var2 = 0; var2 < this.field_76807_J; ++var2) {
         var3 = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
         var4 = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
         this.field_76826_u.func_76484_a(this.field_76815_a, this.field_76813_b, var3, this.field_76815_a.func_72976_f(var3, var4), var4);
      }

      int var7;
      for(var2 = 0; var2 < this.field_76802_A; ++var2) {
         var3 = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
         var4 = this.field_76813_b.nextInt(128);
         var7 = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
         this.field_76830_q.func_76484_a(this.field_76815_a, this.field_76813_b, var3, var4, var7);
         if(this.field_76813_b.nextInt(4) == 0) {
            var3 = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
            var4 = this.field_76813_b.nextInt(128);
            var7 = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
            this.field_76829_r.func_76484_a(this.field_76815_a, this.field_76813_b, var3, var4, var7);
         }
      }

      for(var2 = 0; var2 < this.field_76803_B; ++var2) {
         var3 = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
         var4 = this.field_76813_b.nextInt(128);
         var7 = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
         WorldGenerator var6 = this.field_76812_e.func_76730_b(this.field_76813_b);
         var6.func_76484_a(this.field_76815_a, this.field_76813_b, var3, var4, var7);
      }

      for(var2 = 0; var2 < this.field_76804_C; ++var2) {
         var3 = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
         var4 = this.field_76813_b.nextInt(128);
         var7 = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
         (new WorldGenDeadBush(Block.field_71961_Y.field_71990_ca)).func_76484_a(this.field_76815_a, this.field_76813_b, var3, var4, var7);
      }

      for(var2 = 0; var2 < this.field_76833_y; ++var2) {
         var3 = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
         var4 = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;

         for(var7 = this.field_76813_b.nextInt(128); var7 > 0 && this.field_76815_a.func_72798_a(var3, var7 - 1, var4) == 0; --var7) {
            ;
         }

         this.field_76834_x.func_76484_a(this.field_76815_a, this.field_76813_b, var3, var7, var4);
      }

      for(var2 = 0; var2 < this.field_76798_D; ++var2) {
         if(this.field_76813_b.nextInt(4) == 0) {
            var3 = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
            var4 = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
            var7 = this.field_76815_a.func_72976_f(var3, var4);
            this.field_76828_s.func_76484_a(this.field_76815_a, this.field_76813_b, var3, var7, var4);
         }

         if(this.field_76813_b.nextInt(8) == 0) {
            var3 = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
            var4 = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
            var7 = this.field_76813_b.nextInt(128);
            this.field_76827_t.func_76484_a(this.field_76815_a, this.field_76813_b, var3, var7, var4);
         }
      }

      if(this.field_76813_b.nextInt(4) == 0) {
         var2 = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
         var3 = this.field_76813_b.nextInt(128);
         var4 = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
         this.field_76828_s.func_76484_a(this.field_76815_a, this.field_76813_b, var2, var3, var4);
      }

      if(this.field_76813_b.nextInt(8) == 0) {
         var2 = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
         var3 = this.field_76813_b.nextInt(128);
         var4 = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
         this.field_76827_t.func_76484_a(this.field_76815_a, this.field_76813_b, var2, var3, var4);
      }

      for(var2 = 0; var2 < this.field_76799_E; ++var2) {
         var3 = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
         var4 = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
         var7 = this.field_76813_b.nextInt(128);
         this.field_76825_v.func_76484_a(this.field_76815_a, this.field_76813_b, var3, var7, var4);
      }

      for(var2 = 0; var2 < 10; ++var2) {
         var3 = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
         var4 = this.field_76813_b.nextInt(128);
         var7 = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
         this.field_76825_v.func_76484_a(this.field_76815_a, this.field_76813_b, var3, var4, var7);
      }

      if(this.field_76813_b.nextInt(32) == 0) {
         var2 = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
         var3 = this.field_76813_b.nextInt(128);
         var4 = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
         (new WorldGenPumpkin()).func_76484_a(this.field_76815_a, this.field_76813_b, var2, var3, var4);
      }

      for(var2 = 0; var2 < this.field_76800_F; ++var2) {
         var3 = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
         var4 = this.field_76813_b.nextInt(128);
         var7 = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
         this.field_76824_w.func_76484_a(this.field_76815_a, this.field_76813_b, var3, var4, var7);
      }

      if(this.field_76808_K) {
         for(var2 = 0; var2 < 50; ++var2) {
            var3 = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
            var4 = this.field_76813_b.nextInt(this.field_76813_b.nextInt(120) + 8);
            var7 = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
            (new WorldGenLiquids(Block.field_71942_A.field_71990_ca)).func_76484_a(this.field_76815_a, this.field_76813_b, var3, var4, var7);
         }

         for(var2 = 0; var2 < 20; ++var2) {
            var3 = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
            var4 = this.field_76813_b.nextInt(this.field_76813_b.nextInt(this.field_76813_b.nextInt(112) + 8) + 8);
            var7 = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
            (new WorldGenLiquids(Block.field_71944_C.field_71990_ca)).func_76484_a(this.field_76815_a, this.field_76813_b, var3, var4, var7);
         }
      }

   }

   protected void func_76795_a(int p_76795_1_, WorldGenerator p_76795_2_, int p_76795_3_, int p_76795_4_) {
      for(int var5 = 0; var5 < p_76795_1_; ++var5) {
         int var6 = this.field_76814_c + this.field_76813_b.nextInt(16);
         int var7 = this.field_76813_b.nextInt(p_76795_4_ - p_76795_3_) + p_76795_3_;
         int var8 = this.field_76811_d + this.field_76813_b.nextInt(16);
         p_76795_2_.func_76484_a(this.field_76815_a, this.field_76813_b, var6, var7, var8);
      }

   }

   protected void func_76793_b(int p_76793_1_, WorldGenerator p_76793_2_, int p_76793_3_, int p_76793_4_) {
      for(int var5 = 0; var5 < p_76793_1_; ++var5) {
         int var6 = this.field_76814_c + this.field_76813_b.nextInt(16);
         int var7 = this.field_76813_b.nextInt(p_76793_4_) + this.field_76813_b.nextInt(p_76793_4_) + (p_76793_3_ - p_76793_4_);
         int var8 = this.field_76811_d + this.field_76813_b.nextInt(16);
         p_76793_2_.func_76484_a(this.field_76815_a, this.field_76813_b, var6, var7, var8);
      }

   }

   protected void func_76797_b() {
      this.func_76795_a(20, this.field_76823_i, 0, 128);
      this.func_76795_a(10, this.field_76820_j, 0, 128);
      this.func_76795_a(20, this.field_76821_k, 0, 128);
      this.func_76795_a(20, this.field_76818_l, 0, 64);
      this.func_76795_a(2, this.field_76819_m, 0, 32);
      this.func_76795_a(8, this.field_76816_n, 0, 16);
      this.func_76795_a(1, this.field_76817_o, 0, 16);
      this.func_76793_b(1, this.field_76831_p, 16, 16);
   }
}
