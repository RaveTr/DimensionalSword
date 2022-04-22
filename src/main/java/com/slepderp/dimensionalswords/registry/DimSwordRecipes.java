package com.slepderp.dimensionalswords.registry;

import com.slepderp.dimensionalswords.DimensionalSwords;
import com.slepderp.dimensionalswords.recipe.DimensionalSwordMachineRecipe;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class DimSwordRecipes {
	
	public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, DimensionalSwords.MODID);

    public static final IRecipeType<DimensionalSwordMachineRecipe> DIMENSIONAL_SWORD_MACHINE_RECIPE_TYPE = IRecipeType.register(DimensionalSwords.MODID + "converting_sword");

    public static final RegistryObject<IRecipeSerializer<?>> DIMENSIONAL_SWORD_MACHINE_SERIALIZER = RECIPE_SERIALIZERS.register("converting_sword", DimensionalSwordMachineRecipe.Serializer::new);

}
