package com.slepderp.dimensionalswords.data;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;
import java.util.function.Consumer;

import org.apache.commons.lang3.Validate;

import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.slepderp.dimensionalswords.DimensionalSwords;
import com.slepderp.dimensionalswords.registry.DimSwordItems;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.LocationPredicate;
import net.minecraft.advancements.criterion.PositionTrigger;
import net.minecraft.data.AdvancementProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.TranslationTextComponent;

public class DimSwordsAdvancementsGen extends AdvancementProvider {
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(DimensionalSwords.MODID, "textures/gui/advancement_bg.png");
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
    private final DataGenerator generator;

    public DimSwordsAdvancementsGen(DataGenerator generatorIn) {
        super(generatorIn);
        this.generator = generatorIn;
    }

    private static Path getPath(Path pathIn, Advancement advancementIn) {
        return pathIn.resolve("data/" + advancementIn.getId().getNamespace() + "/advancements/" + advancementIn.getId().getPath() + ".json");
    }

    private static String id(String s) {
        return DimensionalSwords.MODID + ":" + s;
    }

    @Override
    public void run(DirectoryCache cache) {
        Path path = this.generator.getOutputFolder();
        Set<ResourceLocation> set = Sets.newHashSet();
        Consumer<Advancement> consumer = (advancement) -> {
            if (!set.add(advancement.getId())) {
                throw new IllegalStateException("Duplicate advancement " + advancement.getId());
            } else {
                Path path1 = getPath(path, advancement);
                try {
                    IDataProvider.save(GSON, cache, advancement.deconstruct().serializeToJson(), path1);
                } catch (IOException e) {
                    DimensionalSwords.LOGGER.error("Couldn't save advancement {}", path1, e);
                }
            }
        };
        this.register(consumer);
    }

    public void register(Consumer<Advancement> t) {
    	Advancement scientificallyPossible = registerAdvancement("scientifically_possible", FrameType.TASK, DimSwordItems.MAIN_DIMENSIONAL_ADVANCEMENT_LOGO.get()).addCriterion("root",
                PositionTrigger.Instance.located(LocationPredicate.inDimension(RegistryKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation("overworld"))))).save(t, id("scientifically_possible"));
    	
    	@SuppressWarnings("unused")
		Advancement firstOfMany = registerAdvancement("first_of_many", FrameType.TASK, DimSwordItems.DIMENSIONAL_SWORD.get()).parent(scientificallyPossible).addCriterion("has_dimensional_sword",
    			InventoryChangeTrigger.Instance.hasItems(DimSwordItems.DIMENSIONAL_SWORD.get())).save(t, id("first_of_many"));
    }

    private Advancement.Builder registerAdvancement(String name, FrameType type, IItemProvider... items) {
        Validate.isTrue(items.length > 0);
        return Advancement.Builder.advancement().display(items[0], new TranslationTextComponent("advancements.dimensionalswords." + name + ".title"),
                new TranslationTextComponent("advancements.dimensionalswords." + name + ".description"), BACKGROUND_TEXTURE, type, true, true, false);
    }

}
