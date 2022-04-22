package com.slepderp.dimensionalswords.config;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class DimSwordConfig {
	
	public static final ForgeConfigSpec COMMON_CONFIG_SPEC;
	public static final Common COMMON;
	
	static {
        final Pair<Common, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON_CONFIG_SPEC = commonSpecPair.getRight();
        COMMON = commonSpecPair.getLeft();
	}
	
	public static class Common {
		public static ConfigValue<Integer> dimensionalSwordDamage;
		public static ConfigValue<Integer> dimensionalSwordHarvestLevel;
		public static ConfigValue<Integer> dimensionalSwordDurability;
		public static ConfigValue<Float> dimensionalSwordEfficiency;
		public static ConfigValue<Double> dimensionalSwordAmountOfBlocksTeleportedOnUse;
		public static ConfigValue<Integer> dimensionalNetherSwordDamage;
		public static ConfigValue<Integer> dimensionalNetherSwordBurningSeconds;
		public static ConfigValue<Integer> dimensionalNetherSwordDurability;
		public static ConfigValue<Float> dimensionalNetherSwordEfficiency;
		public static ConfigValue<Integer> dimensionalNetherSwordHarvestLevel;
		public static ConfigValue<Boolean> toolTipsEnabled;
		
		 Common(ForgeConfigSpec.Builder builder) {
			 builder.push("ToolTips");
			 toolTipsEnabled = builder.define("enable tooltips", true);
			 builder.pop();
			 
			 builder.push("Swords");
			 builder.pop();
			 builder.push("Dimensional Sword");
			 dimensionalSwordDamage = builder.define("The amount of damage dealt by the dimensional sword", 15);
			 dimensionalSwordHarvestLevel = builder.define("The harvest level of the dimensional sword, 3 being the equivalent of diamond", 3);
			 dimensionalSwordDurability = builder.define("The durability/maximum number of usage for the dimensional sword", 1600);
			 dimensionalSwordEfficiency = builder.define("The efficiency/mining speed of the dimensional sword", 10.0F);
			 dimensionalSwordAmountOfBlocksTeleportedOnUse = builder.define("The amount of blocks you'll teleport away upon right-clicking the dimensional sword, anywhere between 0 blocks and the value you entered", 5.0D);
			 builder.pop();
			 
			 builder.push("Dimensional Nether Sword");
			 dimensionalNetherSwordDamage = builder.define("The amount of damage dealt by the dimensional nether sword", 18);
			 dimensionalNetherSwordHarvestLevel = builder.define("The harvest level of the dimensional nether sword, 3 being the equivalent of diamond", 4);
			 dimensionalNetherSwordDurability = builder.define("The durability/maximum number of usage for the dimensional nether sword", 2400);
			 dimensionalNetherSwordEfficiency = builder.define("The efficiency/mining speed of the dimensional sword", 12.0F);
			 dimensionalNetherSwordBurningSeconds = builder.define("The amount of seconds your target will be set on fire", 10);
			 builder.pop();
			 
		 }
	}

}
