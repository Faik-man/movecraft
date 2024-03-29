package net.minecraft.src;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.EntityGhast;
import net.minecraft.src.EntityMagmaCube;
import net.minecraft.src.EntityPigZombie;
import net.minecraft.src.SpawnListEntry;

public class BiomeGenHell extends BiomeGenBase {

   public BiomeGenHell(int p_i3757_1_) {
      super(p_i3757_1_);
      this.field_76761_J.clear();
      this.field_76762_K.clear();
      this.field_76755_L.clear();
      this.field_76761_J.add(new SpawnListEntry(EntityGhast.class, 50, 4, 4));
      this.field_76761_J.add(new SpawnListEntry(EntityPigZombie.class, 100, 4, 4));
      this.field_76761_J.add(new SpawnListEntry(EntityMagmaCube.class, 1, 4, 4));
   }
}
