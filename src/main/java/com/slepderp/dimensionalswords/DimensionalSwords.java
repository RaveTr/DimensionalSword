package com.slepderp.dimensionalswords;

import net.minecraft.data.DataGenerator;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.fml.config.ModConfig;

import java.util.Locale;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.slepderp.dimensionalswords.api.ReflectionClassLoader;
import com.slepderp.dimensionalswords.client.ClientStuff;
import com.slepderp.dimensionalswords.client.ToolTips;
import com.slepderp.dimensionalswords.config.DimSwordConfig;
import com.slepderp.dimensionalswords.data.DimSwordBlockModelGen;
import com.slepderp.dimensionalswords.data.DimSwordItemModelGen;
import com.slepderp.dimensionalswords.data.DimSwordRecipeGen;
import com.slepderp.dimensionalswords.data.DimSwordTagGen;
import com.slepderp.dimensionalswords.data.DimSwordsAdvancementsGen;
import com.slepderp.dimensionalswords.registry.DimSwordBlocks;
import com.slepderp.dimensionalswords.registry.DimSwordContainerTypes;
import com.slepderp.dimensionalswords.registry.DimSwordItems;
import com.slepderp.dimensionalswords.registry.DimSwordRecipes;
import com.slepderp.dimensionalswords.registry.DimSwordStats;
import com.slepderp.dimensionalswords.registry.DimSwordTileEntities;

@Mod(DimensionalSwords.MODID)
public class DimensionalSwords {	
	public static final String NAME = "Dimensional Swords";
    public static final String MODID = "dimensionalswords";
    public static final String VERSION = "1.0";
    public static DimensionalSwords Instance;
    
    public static final Logger LOGGER = LogManager.getLogger();

    public DimensionalSwords() {
    	Instance = this;
    	
        ReflectionClassLoader.classLoad("com.slepderp.dimensionalswords.registry.DimSwordTags");
    	
        IEventBus eBus = FMLJavaModLoadingContext.get().getModEventBus();
        
        eBus.addListener(this::gatherData);
        
        DimSwordItems.ITEMS.register(eBus);
        DimSwordItems.LOGO_ITEMS.register(eBus);
        DimSwordBlocks.ITEM_BLOCKS.register(eBus);
        DimSwordBlocks.BLOCKS.register(eBus);
        DimSwordContainerTypes.CONTAINERS.register(eBus);
        DimSwordTileEntities.TILE_ENTITIES.register(eBus);
        DimSwordRecipes.RECIPE_SERIALIZERS.register(eBus);
        DimSwordStats.STAT_TYPES.register(eBus);
        
		if (FMLEnvironment.dist == Dist.CLIENT) {
			eBus.addListener(ClientStuff::onFMLClientSetupEvent);
			MinecraftForge.EVENT_BUS.addListener(ToolTips::onToolTipEvent);
		}
    	
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DimSwordConfig.COMMON_CONFIG_SPEC);
    	
    }
    
	public static ResourceLocation locPrefix(String name) {
		return new ResourceLocation(MODID, name.toLowerCase(Locale.ROOT));
	}
    
    private void gatherData(final GatherDataEvent event) {
		DataGenerator dataGen = event.getGenerator();
		final ExistingFileHelper exists = event.getExistingFileHelper();
		if(event.includeServer()) {
			dataGen.addProvider(new DimSwordTagGen(dataGen, exists));
			dataGen.addProvider(new DimSwordTagGen.DimSwordTagGenForBlocks(dataGen, exists));
			dataGen.addProvider(new DimSwordTagGen.DimSwordItemTagGen(dataGen, new DimSwordTagGen.DimSwordTagGenForBlocks(dataGen, exists), exists));
			dataGen.addProvider(new DimSwordItemModelGen(dataGen, exists));
			dataGen.addProvider(new DimSwordsAdvancementsGen(dataGen));
			dataGen.addProvider(new DimSwordRecipeGen(dataGen));
			dataGen.addProvider(new DimSwordBlockModelGen(dataGen, DimensionalSwords.MODID, exists));
		}
    }
    
    public static <D> void debug(String domain, D message) {
		LOGGER.debug("[" + domain + "]: " + (message == null ? "null" : message.toString()));
	}

	public static <I> void info(String domain, I message) {
		LOGGER.info("[" + domain + "]: " + (message == null ? "null" : message.toString()));
	}

	public static <W> void warn(String domain, W message) {
		LOGGER.warn("[" + domain + "]: " + (message == null ? "null" : message.toString()));
	}

	public static <E> void error(String domain, E message) {
		LOGGER.error("[" + domain + "]: " + (message == null ? "null" : message.toString()));
	}
	
}
