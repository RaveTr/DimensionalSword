package com.slepderp.dimensionalswords.enums;

import com.google.common.base.Supplier;

import com.slepderp.dimensionalswords.config.DimSwordConfig.Common;

import net.minecraft.item.Items;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

public enum DimSwordsTiers implements IItemTier{
	
	DIMENSIONAL_SWORD(Common.dimensionalSwordHarvestLevel.get(), Common.dimensionalSwordDurability.get(), Common.dimensionalSwordEfficiency.get(), 10, 64, () -> Ingredient.of(Items.ENDER_PEARL)),
	DIMENSIONAL_NETHER_SWORD(Common.dimensionalNetherSwordHarvestLevel.get(), Common.dimensionalNetherSwordDurability.get(), Common.dimensionalNetherSwordEfficiency.get(), 10, 96, () -> Ingredient.of(Items.ENDER_PEARL));

	
    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairMaterial;

    DimSwordsTiers(int harvestLevel, int maxUses, float efficiency, float damage, int enchantability, Supplier<Ingredient> repairMaterial) {
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDamage = damage;
        this.enchantability = enchantability;
        this.repairMaterial = repairMaterial;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.attackDamage;
    }

    @Override
    public float getSpeed() {
        return this.efficiency;
    }
    
    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    public int getLevel() {
        return this.harvestLevel;
    }

    @Override
    public int getUses() {
        return this.maxUses;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairMaterial.get();
    }

}
