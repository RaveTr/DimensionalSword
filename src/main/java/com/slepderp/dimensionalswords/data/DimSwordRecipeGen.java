package com.slepderp.dimensionalswords.data;

import java.util.function.Consumer;

import com.slepderp.dimensionalswords.DimensionalSwords;
import com.slepderp.dimensionalswords.builder.DimensionalSwordMachineRecipeBuilder;
import com.slepderp.dimensionalswords.registry.DimSwordItems;
import com.slepderp.dimensionalswords.registry.DimSwordRecipes;

import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.CookingRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

public class DimSwordRecipeGen extends RecipeProvider {

	public DimSwordRecipeGen(DataGenerator gen) {
		super(gen);
	}

	public String getName() {
		return "Dimensional Swords Recipes";
	}

	@Override
	protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
		//insert recipes here (all types)
		buildSwordRecipes(consumer);
	}

	//Insert custom recipe methods here

	protected static void buildCookingRecipes(Consumer<IFinishedRecipe> consumer, String condition, CookingRecipeSerializer<?> recipe, int exp) {
		//insert cooking recipes here
	}
	
	private void buildSwordRecipes(Consumer<IFinishedRecipe> consumer) {
		DimensionalSwordMachineRecipeBuilder.builder(DimSwordRecipes.DIMENSIONAL_SWORD_MACHINE_SERIALIZER.get(), 
				Ingredient.of(Items.DIAMOND_SWORD),
				Ingredient.of(Items.LAVA_BUCKET), 
				Ingredient.of(Blocks.NETHERRACK), 
	//			Ingredient.of(DimSwordItems.DIMENSIONAL_SWORD.get()), 
				DimSwordItems.DIMENSIONAL_NETHER_SWORD.get(), 1, 50).build(consumer, new ResourceLocation(DimensionalSwords.MODID, "converting_sword/" + DimSwordItems.DIMENSIONAL_SWORD.getId().toString().replaceAll("dimensionalswords:", "") + "_to_dimensional_nether_sword"));
		
	}

}
