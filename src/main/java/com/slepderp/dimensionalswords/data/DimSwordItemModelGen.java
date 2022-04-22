package com.slepderp.dimensionalswords.data;

import java.util.Collection;


import javax.annotation.Nonnull;

import com.slepderp.dimensionalswords.DimensionalSwords;
import com.slepderp.dimensionalswords.registry.DimSwordBlocks;
import com.slepderp.dimensionalswords.registry.DimSwordItems;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelFile.ExistingModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;

public class DimSwordItemModelGen extends ItemModelProvider {

    public DimSwordItemModelGen(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, DimensionalSwords.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        generate(DimSwordItems.ITEMS.getEntries());
        generateBlockItems(DimSwordBlocks.ITEM_BLOCKS.getEntries());
        generate(DimSwordItems.LOGO_ITEMS.getEntries());
    }

    @Nonnull
    @Override
    public String getName() {
        return DimensionalSwords.NAME + " Item models";
    }

    private void generate(final Collection<RegistryObject<Item>> items) {
        final ModelFile parentGenerated = getExistingFile(mcLoc("item/generated"));
        final ExistingModelFile parentHandheld = getExistingFile(mcLoc("item/handheld"));

        for (RegistryObject<Item> item : items) {
            String name = item.getId().getPath();

            if (name.startsWith("enchanted"))
                name = name.substring(name.indexOf("_") + 1);
            
            if (!existingFileHelper.exists(new ResourceLocation(DimensionalSwords.MODID, "item/" + name), TEXTURE) || existingFileHelper.exists(new ResourceLocation(DimensionalSwords.MODID, "item/" + name), MODEL))
                continue;

            DimensionalSwords.LOGGER.info(item.getId());

            getBuilder(item.getId().getPath()).parent(item.get().getMaxDamage(ItemStack.EMPTY) > 0 && !(item.get() instanceof ArmorItem) ? parentHandheld : parentGenerated).texture("layer0", ItemModelProvider.ITEM_FOLDER + "/" + name);
        }
    }

    private void generateBlockItems(final Collection<RegistryObject<Item>> itemBlocks) {
        for (RegistryObject<Item> item : itemBlocks) {
            String name = item.getId().getPath();

            if (!existingFileHelper.exists(new ResourceLocation(DimensionalSwords.MODID, "block/" + name), MODEL) || existingFileHelper.exists(new ResourceLocation(DimensionalSwords.MODID, "item/" + name), MODEL))
                continue;

            DimensionalSwords.LOGGER.info(item.getId());

            withExistingParent(name, new ResourceLocation(DimensionalSwords.MODID, "block/" + name));

        }
    }

}
