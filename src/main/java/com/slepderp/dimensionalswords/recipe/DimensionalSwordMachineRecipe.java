package com.slepderp.dimensionalswords.recipe;

import javax.annotation.Nullable;

import com.google.gson.JsonObject;
import com.slepderp.dimensionalswords.registry.DimSwordRecipes;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class DimensionalSwordMachineRecipe extends AbstractDimensionalSwordMachineRecipe{
	
	 public DimensionalSwordMachineRecipe(ResourceLocation recipeId, Ingredient ingredient1, Ingredient ingredient2, Ingredient ingredient3, ItemStack result, float experience, int timeTaken) {
	        super(DimSwordRecipes.DIMENSIONAL_SWORD_MACHINE_RECIPE_TYPE, DimSwordRecipes.DIMENSIONAL_SWORD_MACHINE_SERIALIZER.get(), recipeId, ingredient1, ingredient2, ingredient3, result, experience, timeTaken);
	    }

	    public ItemStack getResult() {
	        return result;
	    }

	    @Override
	    public boolean matches(IInventory inv, World world) {
	        return this.ingredient1.test(inv.getItem(0)) && this.ingredient2.test(inv.getItem(1));
	    }

	    public IRecipeType<?> getType() {
	        return DimSwordRecipes.DIMENSIONAL_SWORD_MACHINE_RECIPE_TYPE;
	    }

	    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<DimensionalSwordMachineRecipe> {
	        @Override
	        public DimensionalSwordMachineRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
	            Ingredient swordIngredient = Ingredient.fromJson(JSONUtils.getAsJsonObject(json, "sword_ingredient"));
	            Ingredient moduleIngredient = Ingredient.fromJson(JSONUtils.getAsJsonObject(json, "module_ingredient"));
	            Ingredient blockIngredient = Ingredient.fromJson(JSONUtils.getAsJsonObject(json, "block_ingredient"));
	  //          Ingredient swordIngredient2 = Ingredient.fromJson(JSONUtils.getAsJsonObject(json, "sword_ingredient_2"));
	            ResourceLocation itemId = new ResourceLocation(JSONUtils.getAsString(json, "result"));
	            float experience = JSONUtils.getAsFloat(json, "experience", 0);
	            ItemStack result = new ItemStack(ForgeRegistries.ITEMS.getValue(itemId));
	            int timeTaken = JSONUtils.getAsInt(json, "time_taken_for_conversion", 50);
	            return new DimensionalSwordMachineRecipe(recipeId, swordIngredient, moduleIngredient, blockIngredient, result, experience, timeTaken);
	        }

	        @Nullable
	        @Override
	        public DimensionalSwordMachineRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
	            Ingredient swordIngredient = Ingredient.fromNetwork(buffer);
	            Ingredient moduleIngredient = Ingredient.fromNetwork(buffer);
	            Ingredient blockIngredient = Ingredient.fromNetwork(buffer);
	  //          Ingredient swordIngredient2 = Ingredient.fromNetwork(buffer);
	            ItemStack result = buffer.readItem();
	            float experience = buffer.readFloat();
	            int timeTaken = buffer.readInt();
	            return new DimensionalSwordMachineRecipe(recipeId, swordIngredient, moduleIngredient, blockIngredient, result, experience, timeTaken);
	        }

	        @Override
	        public void toNetwork(PacketBuffer buffer, DimensionalSwordMachineRecipe recipe) {
	            recipe.ingredient1.toNetwork(buffer);
	            recipe.ingredient2.toNetwork(buffer);
	            recipe.ingredient3.toNetwork(buffer);
	       //     recipe.ingredient4.toNetwork(buffer);
	            buffer.writeItem(recipe.result);
	            buffer.writeFloat(recipe.experience);
	            buffer.writeInt(recipe.swordConversionTime);
	        }
	    }
}
