package com.slepderp.dimensionalswords.tileentities;

import com.slepderp.dimensionalswords.item.ModuleItem;
import com.slepderp.dimensionalswords.recipe.AbstractDimensionalSwordMachineRecipe;
import com.slepderp.dimensionalswords.registry.DimSwordContainerTypes;
import com.slepderp.dimensionalswords.registry.DimSwordRecipes;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraft.world.World;

public class DimensionalSwordMachineContainer extends Container {
    private final IInventory inventory;
    private IIntArray fields;
    protected final World level;
    @SuppressWarnings("unused")
	private final IRecipeType<? extends AbstractDimensionalSwordMachineRecipe> recipeType;
    private final Slot swordSlot;
    private final Slot moduletSlot;
    private final Slot blockSlot;
 //   private final Slot swordSlot2;
    private final Slot resultSlot;
	
	

    public DimensionalSwordMachineContainer(int id, PlayerInventory playerInventory, PacketBuffer buffer) {
        this(DimSwordRecipes.DIMENSIONAL_SWORD_MACHINE_RECIPE_TYPE, id, playerInventory, new DimensionalSwordMachineTileEntity(), new IntArray(buffer.readByte()));
    }

    public DimensionalSwordMachineContainer(IRecipeType<? extends AbstractDimensionalSwordMachineRecipe> recipeType, int id, PlayerInventory playerInventory, IInventory inventory, IIntArray fields) {
        super(DimSwordContainerTypes.DIMENSIONAL_SWORD_MACHINE.get(), id);
        this.recipeType = recipeType;
        this.level = playerInventory.player.level;
        this.inventory = inventory;
        this.fields = fields;

        this.swordSlot = this.addSlot(new Slot(this.inventory, 0, 56, 17));
        this.moduletSlot = this.addSlot(new Slot(this.inventory, 1, 47, 53) {
            @Override
            public boolean mayPlace(ItemStack stack) { return stack.getItem() instanceof BucketItem || stack.getItem() instanceof ModuleItem; }
        });
        this.blockSlot = this.addSlot(new Slot(this.inventory, 2, 65, 53) {
            @Override
            public boolean mayPlace(ItemStack stack) { return stack.getItem() instanceof BlockItem; }
        });
        this.resultSlot = this.addSlot(new Slot(this.inventory, 3, 116, 35) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
        });

        // Player backpack
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        // Player hotbar
        for(int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
        }

        this.addDataSlots(fields);
    }

    public  int getProgressArrowScale() {
        int progress = fields.get(0);
        return progress != 0 ? progress * 24 / DimensionalSwordMachineTileEntity.WORK_TIME : 0;
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return inventory.stillValid(player);
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index == this.resultSlot.index) {
                if (!this.moveItemStackTo(itemstack1, 4, 40, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
            } else if (index != this.swordSlot.index && index != this.moduletSlot.index && index != this.blockSlot.index) {
                if (!(itemstack1.getItem() instanceof BucketItem) && !(itemstack1.getItem() instanceof ModuleItem) ) {
                    if (!this.moveItemStackTo(itemstack1, this.moduletSlot.index, this.moduletSlot.index + 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (itemstack1.getItem() instanceof SwordItem) {
                    if (!this.moveItemStackTo(itemstack1, this.swordSlot.index, this.swordSlot.index + 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (itemstack1.getItem() instanceof BlockItem) {
                    if (!this.moveItemStackTo(itemstack1, this.blockSlot.index, this.blockSlot.index + 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 4 && index < 31) {
                    if (!this.moveItemStackTo(itemstack1, 31, 40, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 31 && index < 40 && !this.moveItemStackTo(itemstack1, 4, 31, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 4, 40, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemstack1);
        }

        return itemstack;
    }

}
