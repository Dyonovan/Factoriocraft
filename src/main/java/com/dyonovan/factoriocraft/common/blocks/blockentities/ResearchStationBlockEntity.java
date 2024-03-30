package com.dyonovan.factoriocraft.common.blocks.blockentities;

import com.dyonovan.factoriocraft.common.containers.ResearchStationContainer;
import com.dyonovan.factoriocraft.lib.Registration;
import com.pauljoda.nucleus.capabilities.energy.EnergyBank;
import com.pauljoda.nucleus.capabilities.item.InventoryContents;
import com.pauljoda.nucleus.capabilities.item.InventoryHolderCapability;
import com.pauljoda.nucleus.common.blocks.entity.energy.EnergyAndItemHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class ResearchStationBlockEntity extends EnergyAndItemHandler implements MenuProvider {

    private static int requiredTicks;

    protected InventoryHolderCapability inventory;

    public final ContainerData data = new ContainerData() {
        @Override
        public int get(int i) {
            return switch (i) {
                case 0 -> getDefaultEnergyStorageSize();
                case 1 -> getEnergyCapability().getEnergyStored();
                default -> throw new IllegalStateException("Invalid Index: " + i);
            };
        }

        @Override
        public void set(int i, int i1) {
            throw new IllegalStateException("Cannot set values through IIntArray");
        }

        @Override
        public int getCount() {
            return 2;
        }
    };

    public ResearchStationBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(Registration.RESEARCH_STATION_BLOCK_ENTITY.get(), blockPos, blockState);

        inventory = new InventoryHolderCapability(getInventoryContents()) {
            @Override
            protected int getInventorySize() {
                return ResearchStationBlockEntity.this.getInventorySize();
            }

            @Override
            protected boolean isItemValidForSlot(int index, ItemStack stack) {
                return ResearchStationBlockEntity.this.isItemValidForSlot(index, stack);
            }
        };
        inventory.addCallback((inventory, slotNumber) -> markForUpdate(Block.UPDATE_ALL));
    }

    @Override
    public void onServerTick() {

    }

    @Override
    protected int getDefaultEnergyStorageSize() {
        return 1000000;
    }

    @Override
    protected EnergyBank initializeEnergyStorage() {
        return new EnergyBank(this.getDefaultEnergyStorageSize(), 100, 0, 0) {
            @Override
            public boolean canExtract() {
                return false;
            }
        };
    }

    public EnergyBank getEnergyBank() {
        return energyStorage;
    }

    @Override
    protected int getInventorySize() {
        return 6;
    }

    @Override
    protected boolean isItemValidForSlot(int index, ItemStack stack) {
        return true;
    }

    @Override
    protected InventoryContents initializeInventory() {
        return new InventoryContents() {
            @Override
            public int getInventorySize() {
                return ResearchStationBlockEntity.this.getInventorySize();
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable(getBlockState().getBlock().getDescriptionId());
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerID, Inventory inventory, Player player) {
        return new ResearchStationContainer(containerID, inventory, this, this.data);
    }
}
