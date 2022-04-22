package com.slepderp.dimensionalswords.data;

import com.slepderp.dimensionalswords.DimensionalSwords;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class DimSwordBlockStateGen extends BlockStateProvider {

    public DimSwordBlockStateGen(DataGenerator gen, String modid, ExistingFileHelper exFileHelper) {
        super(gen, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

    }

    @SuppressWarnings("unused")
	private ResourceLocation dsRL(String texture) {
        return new ResourceLocation(DimensionalSwords.MODID, "block/" + texture);
    }

}
