package com.slepderp.dimensionalswords.registry;

import com.slepderp.dimensionalswords.DimensionalSwords;

import net.minecraft.stats.IStatFormatter;
import net.minecraft.stats.StatType;
import net.minecraft.stats.Stats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class DimSwordStats {
	
	public static final DeferredRegister<StatType<?>> STAT_TYPES = DeferredRegister.create(ForgeRegistries.STAT_TYPES, DimensionalSwords.MODID);

    public static final ResourceLocation INTERACT_WITH_DIMENSIONAL_SWORD_MACHINE = registerCustom("interact_with_dimensional_sword_machine");

    private static ResourceLocation registerCustom(String key) {
        ResourceLocation resourcelocation = new ResourceLocation(DimensionalSwords.MODID, key);
        Registry.register(Registry.CUSTOM_STAT, key, resourcelocation);
        Stats.CUSTOM.get(resourcelocation, IStatFormatter.DEFAULT);
        return resourcelocation;
    }

}
