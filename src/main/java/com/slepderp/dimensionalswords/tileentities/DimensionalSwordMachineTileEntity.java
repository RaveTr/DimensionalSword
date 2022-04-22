package com.slepderp.dimensionalswords.tileentities;

import javax.annotation.Nullable;

import com.slepderp.dimensionalswords.recipe.DimensionalSwordMachineRecipe;
import com.slepderp.dimensionalswords.registry.DimSwordRecipes;
import com.slepderp.dimensionalswords.registry.DimSwordTileEntities;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

public class DimensionalSwordMachineTileEntity extends LockableTileEntity implements ISidedInventory, ITickableTileEntity {
    private static final int[] SLOTS_FOR_UP = new int[]{0};
    private static final int[] SLOTS_FOR_DOWN = new int[]{3};
    private static final int[] SLOTS_FOR_SIDES = new int[]{1, 2};

    static final int WORK_TIME = 2 * 20;

    private NonNullList<ItemStack> items;
    private final LazyOptional<? extends IItemHandler>[] handlers;

    private int progress = 0;

    private final IIntArray fields = new IIntArray() {
        @Override
        public int get(int index) {
            switch (index) {
                case 0:
                    return progress;
                default:
                    return 0;
            }
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0:
                    progress = value;
                    break;
            }
        }

        @Override
        public int getCount() {
            return 1;
        }
    };

    public DimensionalSwordMachineTileEntity() {
        super(DimSwordTileEntities.DIMENSIONAL_SWORD_MACHINE.get());
        this.handlers = SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);
        this.items = NonNullList.withSize(4, ItemStack.EMPTY);
    }

   public void encodeExtraData(PacketBuffer buffer) {
        buffer.writeByte(fields.getCount());
    }

    @Override
    public void tick() {
        if (this.level == null || this.level.isClientSide) {
            return;
        }

        DimensionalSwordMachineRecipe recipe = getRecipe();
        if (recipe != null) {
            doWork(recipe);
        } else {
            stopWork();
        }
    }

    @Nullable
    public DimensionalSwordMachineRecipe getRecipe() {
        if (this.level == null || getItem(0).isEmpty() || getItem(1).isEmpty() || getItem(2).isEmpty()) {
            return null;
        }
        return level.getRecipeManager().getRecipeFor(DimSwordRecipes.DIMENSIONAL_SWORD_MACHINE_RECIPE_TYPE, this, level).orElse(null);
    }

    private ItemStack getWorkOutput(@Nullable DimensionalSwordMachineRecipe recipe) {
        if (recipe != null) {
            return recipe.assemble(this);
        }
        return ItemStack.EMPTY;
    }

    private void doWork(DimensionalSwordMachineRecipe recipe) {
        assert this.level != null;

        ItemStack current = getItem(3);
        ItemStack output = getWorkOutput(recipe);

        if (!current.isEmpty()) {
            int newCount = current.getCount() + output.getCount();

            if (!ItemStack.matches(current, output) || newCount > 64) {
                stopWork();
                return;
            }
        }

        if (progress < WORK_TIME) {
            ++progress;
        }

        if (progress >= WORK_TIME && !level.isClientSide) {
            finishWork(recipe, current);
        }
    }

    private void stopWork() {
        progress = 0;
    }

    private void finishWork(DimensionalSwordMachineRecipe recipe, ItemStack current) {
        ItemStack output = getWorkOutput(recipe);
        if (!current.isEmpty()) {
            current.grow(output.getCount());
        } else {
            setItem(3, output);
        }

        progress = 0;
        this.removeItem(0, 1);
        this.setItem(1, Items.BUCKET.getDefaultInstance());
        this.removeItem(2, 1);
    }

    @Override
    public int[] getSlotsForFace(Direction direction) {
        if (direction == Direction.DOWN) {
            return SLOTS_FOR_DOWN;
        } else {
            return direction == Direction.UP ? SLOTS_FOR_UP : SLOTS_FOR_SIDES;
        }
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction) {
        return this.canPlaceItem(index, stack);
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        if (direction == Direction.DOWN && index == 1) {
            Item item = stack.getItem();
            return item == Items.WATER_BUCKET || item == Items.BUCKET;
        }
        return true;
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container.dimensionalswords.dimensional_sword_machine");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory playerInventory) {
        return new DimensionalSwordMachineContainer(DimSwordRecipes.DIMENSIONAL_SWORD_MACHINE_RECIPE_TYPE, id, playerInventory, this, this.fields);
//        return null;
    }

    @Override
    public int getContainerSize() {
        return 4;
    }

    @Override
    public boolean isEmpty() {
        return getItem(0).isEmpty() && getItem(1).isEmpty() && getItem(2).isEmpty() && getItem(3).isEmpty();
    }

    @Override
    public ItemStack getItem(int index) {
        return items.get(index);
    }

    @Override
    public ItemStack removeItem(int index, int count) {
        return ItemStackHelper.removeItem(items, index, count);
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        return ItemStackHelper.takeItem(items, index);
    }

    @Override
    public void setItem(int index, ItemStack stack) {
        items.set(index, stack);
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return this.level != null
                && this.level.getBlockEntity(this.worldPosition) == this
                && player.distanceToSqr(this.worldPosition.getX() + 0.5 , this.worldPosition.getY() + 0.5, this.worldPosition.getZ()) <= 64;
    }

    @Override
    public void clearContent() {
        items.clear();
    }

    @Override
    public void load(BlockState state, CompoundNBT tags) {
        super.load(state, tags);
        this.items = NonNullList.withSize(4, ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(tags, this.items);

        this.progress = tags.getInt("Progress");
    }

    @Override
    public CompoundNBT save(CompoundNBT tags) {
        super.save(tags);
        ItemStackHelper.saveAllItems(tags, this.items);
        tags.putInt("Progress", this.progress);
        return tags;
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT tags = this.getUpdateTag();
        ItemStackHelper.saveAllItems(tags, this.items);
        return new SUpdateTileEntityPacket(this.worldPosition, 1, tags);
    }

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT tags = super.getUpdateTag();
        tags.putInt("Progress", this.progress);
        return tags;
    }

    @Nullable
    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
        if (!this.remove && side != null && cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (side == Direction.UP) {
                return this.handlers[0].cast();
            } else if (side == Direction.WEST) {
                return this.handlers[1].cast();
            } else if (side == Direction.EAST) {
                return this.handlers[2].cast();
            } else if (side == Direction.DOWN) {
                return this.handlers[3].cast();
            } else {
                return this.handlers[4].cast();
            }
        } else {
            return super.getCapability(cap, side);
        }
    }

    @Override
    public void setRemoved() {
        super.setRemoved();

        for (LazyOptional<? extends IItemHandler> handler : this.handlers) {
            handler.invalidate();
        }
    }
}
