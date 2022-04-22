package com.slepderp.dimensionalswords.registry;

import com.slepderp.dimensionalswords.DimensionalSwords;

import com.slepderp.dimensionalswords.tileentities.DimensionalSwordMachineTileEntity;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class DimSwordTileEntities {
	
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, DimensionalSwords.MODID);
	
    public static final RegistryObject<TileEntityType<DimensionalSwordMachineTileEntity>> DIMENSIONAL_SWORD_MACHINE = TILE_ENTITIES.register("dimensional_sword_machine",
            () -> TileEntityType.Builder.of(DimensionalSwordMachineTileEntity::new, DimSwordBlocks.DIMENSIOAN_SWORD_MACHINE.get()).build(null));
}
