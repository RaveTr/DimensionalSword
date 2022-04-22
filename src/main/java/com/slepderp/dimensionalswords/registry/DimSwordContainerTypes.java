package com.slepderp.dimensionalswords.registry;

import com.slepderp.dimensionalswords.DimensionalSwords;

import com.slepderp.dimensionalswords.tileentities.DimensionalSwordMachineContainer;
import com.slepderp.dimensionalswords.tileentities.DimensionalSwordMachineScreen;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.api.distmarker.Dist;

public class DimSwordContainerTypes {
	
	public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, DimensionalSwords.MODID);

    public static final RegistryObject<ContainerType<DimensionalSwordMachineContainer>> DIMENSIONAL_SWORD_MACHINE = register("dimensional_sword_machine", DimensionalSwordMachineContainer::new);

    private static <T extends Container> RegistryObject<ContainerType<T>> register(String name, IContainerFactory<T> factory) {
        return CONTAINERS.register(name, () -> IForgeContainerType.create(factory));
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerScreens(FMLClientSetupEvent event) {
        ScreenManager.register(DIMENSIONAL_SWORD_MACHINE.get(), DimensionalSwordMachineScreen::new);
    }

}
