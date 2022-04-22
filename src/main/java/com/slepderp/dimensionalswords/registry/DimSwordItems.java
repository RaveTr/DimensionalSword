package com.slepderp.dimensionalswords.registry;

import com.slepderp.dimensionalswords.DimensionalSwords;

import com.slepderp.dimensionalswords.enums.DimSwordsTiers;
import com.slepderp.dimensionalswords.item.DimensionalNetherSwordItem;
import com.slepderp.dimensionalswords.item.DimensionalSwordItem;
import com.slepderp.dimensionalswords.config.DimSwordConfig.Common;

import net.minecraft.item.Item;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class DimSwordItems {
	
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DimensionalSwords.MODID);
	public static final DeferredRegister<Item> LOGO_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DimensionalSwords.MODID);
	
	//Swords
	public static final RegistryObject<DimensionalSwordItem> DIMENSIONAL_SWORD = ITEMS.register("dimensional_sword", () -> new DimensionalSwordItem(DimSwordsTiers.DIMENSIONAL_SWORD , Common.dimensionalSwordDamage.get() - 11, -2.4F, new Item.Properties().rarity(DimSwordRarities.DIMENSIONAL).tab(DimSwordItemGroups.dimSwordsGroup)));
	public static final RegistryObject<DimensionalNetherSwordItem> DIMENSIONAL_NETHER_SWORD = ITEMS.register("dimensional_nether_sword", () -> new DimensionalNetherSwordItem(DimSwordsTiers.DIMENSIONAL_NETHER_SWORD , Common.dimensionalNetherSwordDamage.get() - 11, -2.4F, new Item.Properties().rarity(DimSwordRarities.NETHER_DIMENSIONAL).tab(DimSwordItemGroups.dimSwordsGroup), ServerWorld.NETHER));
	
	//Logos for stuff like advancements, etc.
	public static final RegistryObject<Item> MAIN_DIMENSIONAL_ADVANCEMENT_LOGO = LOGO_ITEMS.register("main_dimensional_advancement_logo", () -> new Item(new Item.Properties().rarity(DimSwordRarities.LOGO)));
	
}
