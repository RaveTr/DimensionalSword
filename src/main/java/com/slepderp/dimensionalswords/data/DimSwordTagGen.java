package com.slepderp.dimensionalswords.data;

import java.nio.file.Path;

import com.slepderp.dimensionalswords.DimensionalSwords;
import com.slepderp.dimensionalswords.registry.DimSwordItems;
import com.slepderp.dimensionalswords.registry.DimSwordTags;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;

public class DimSwordTagGen extends BlockTagsProvider {
	
	public DimSwordTagGen(DataGenerator gen, ExistingFileHelper existingFileHelper) {
		super(gen, DimensionalSwords.MODID, existingFileHelper);
	}
	
	protected Path getPath(ResourceLocation resourceLocation) {
		return this.generator.getOutputFolder().resolve("data/" + resourceLocation.getNamespace() + "/tags/items/" + resourceLocation.getPath() + ".json");
	}
	
	public String getName() {
		return "Dimensional Swords Item-Block Tags";
	}
	
	@Override
	protected void addTags() {
		
	}

	public static class DimSwordTagGenForBlocks extends BlockTagsProvider {

		public DimSwordTagGenForBlocks(DataGenerator gen, ExistingFileHelper existingFileHelper) {
			super(gen, DimensionalSwords.MODID, existingFileHelper);
		}

		protected Path getPath(ResourceLocation resourceLocation) {
			return this.generator.getOutputFolder().resolve("data/" + resourceLocation.getNamespace() + "/tags/blocks/" + resourceLocation.getPath() + ".json");
		}

		public String getName() {
			return "Dimensional Swords Block Tags";
		}

		@Override
		protected void addTags() {
			
		}
	}

	public static class DimSwordItemTagGen extends ItemTagsProvider {

		public DimSwordItemTagGen(DataGenerator gen, BlockTagsProvider blockTagsProvider, @javax.annotation.Nullable net.minecraftforge.common.data.ExistingFileHelper existingFileHelper) {
			super(gen, new DimSwordTagGenForBlocks(gen, existingFileHelper), DimensionalSwords.MODID, existingFileHelper);
		}

		protected Path getPath(ResourceLocation resourceLocation) {
			return this.generator.getOutputFolder().resolve("data/" + resourceLocation.getNamespace() + "/tags/items/" + resourceLocation.getPath() + ".json");
		}

		public String getName() {
			return "Dimensional Swords Item Tags";
		}

		@Override
		protected void addTags() {
			this.tag(DimSwordTags.TOOLTIP).add(DimSwordItems.DIMENSIONAL_SWORD.get());
			this.tag(DimSwordTags.TOOLTIP).add(DimSwordItems.DIMENSIONAL_NETHER_SWORD.get());
		}
	}

}
