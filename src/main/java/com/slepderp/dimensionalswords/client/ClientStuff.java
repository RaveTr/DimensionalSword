package com.slepderp.dimensionalswords.client;

import com.slepderp.dimensionalswords.registry.DimSwordContainerTypes;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ClientStuff {
	
	 public static void register() {
	        FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientStuff::onFMLClientSetupEvent);
	    }

	    public static void onFMLClientSetupEvent(FMLClientSetupEvent event) {
	    	
	    	DimSwordContainerTypes.registerScreens(event);
	    	
	    }

}
