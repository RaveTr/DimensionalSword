package com.slepderp.dimensionalswords.registry;

import com.google.common.base.Supplier;
import com.slepderp.dimensionalswords.DimensionalSwords;
import com.slepderp.dimensionalswords.block.DimensionalSwordMachineBlock;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class DimSwordBlocks {
	
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, DimensionalSwords.MODID);
	public static final DeferredRegister<Item> ITEM_BLOCKS = DeferredRegister.create(ForgeRegistries.ITEMS, DimensionalSwords.MODID);
	
	public static final RegistryObject<DimensionalSwordMachineBlock> DIMENSIOAN_SWORD_MACHINE = registerBlock("dimensional_sword_machine", () -> new DimensionalSwordMachineBlock(Block.Properties.of(Material.METAL).strength(4, 20).sound(SoundType.METAL)), DimSwordItemGroups.dimSwordsGroup, true);

	
    public static <B extends Block> RegistryObject<B> registerBlock(String name, Supplier<? extends B> supplier, ItemGroup itemGroup) {
        return registerBlock(name, supplier, itemGroup, true);
    }

    public static <B extends Block> RegistryObject<B> registerBlock(String name, Supplier<? extends B> supplier, ItemGroup itemGroup, boolean generateItem) {
        return registerBlock(name, supplier, itemGroup, 64, generateItem);
    }

    public static <B extends Block> RegistryObject<B> registerBlock(String name, Supplier<? extends B> supplier, ItemGroup itemGroup, int stackSize) {
        return registerBlock(name, supplier, itemGroup, stackSize, true);
    }

    public static <B extends Block> RegistryObject<B> registerBlock(String name, Supplier<? extends B> supplier, ItemGroup itemGroup, int stackSize, boolean generateItem) {
        RegistryObject<B> block = DimSwordBlocks.BLOCKS.register(name, supplier);
        if (generateItem)
            ITEM_BLOCKS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(itemGroup).stacksTo(stackSize)));
        return block;
    }

}
