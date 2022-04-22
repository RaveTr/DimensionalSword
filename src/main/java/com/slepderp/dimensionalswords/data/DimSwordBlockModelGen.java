package com.slepderp.dimensionalswords.data;

import com.slepderp.dimensionalswords.DimensionalSwords;

import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;


public class DimSwordBlockModelGen extends BlockModelProvider {
	
	public DimSwordBlockModelGen(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
		super(generator, modid, existingFileHelper);
	}
	
	@Override
	protected void registerModels() {
		this.orientableWithBottom("dimensional_sword_machine", mcRL("iron_block"), dsRL("dimensional_sword_machine_front"), mcRL("iron_block"), dsRL("dimensional_sword_machine_top"));
	
	}

	private ResourceLocation dsRL(String texture) {
		return new ResourceLocation(DimensionalSwords.MODID, BLOCK_FOLDER + "/" + texture);
	}

	private ResourceLocation mcRL(String texture) {
		return new ResourceLocation("minecraft", BLOCK_FOLDER + "/" + texture);
	}

	@Override
	public BlockModelBuilder cubeAll(String name, ResourceLocation texture) {
		return singleTexture(name, mcLoc(BLOCK_FOLDER + "/cube_all"), "all", texture);
	}

	@Override
	public BlockModelBuilder cubeBottomTop(String name, ResourceLocation side, ResourceLocation bottom, ResourceLocation top) {
		return withExistingParent(name, BLOCK_FOLDER).texture("side", side).texture("bottom", bottom).texture("top", top);
	}
	
	@Override
	public BlockModelBuilder cubeColumn(String name, ResourceLocation side, ResourceLocation end) {
		return withExistingParent(name, BLOCK_FOLDER).texture("side", side).texture("end", end);
	}
	
	@Override
	public BlockModelBuilder cubeColumnHorizontal(String name, ResourceLocation side, ResourceLocation end) {
		return withExistingParent(name, BLOCK_FOLDER).texture("side", side).texture("end", end);
	}

}
