package com.dyonovan.factoriocraft.common.blocks.blockentities;

import com.dyonovan.factoriocraft.common.containers.ResearchStationContainer;
import com.dyonovan.factoriocraft.lib.Registration;
import com.pauljoda.nucleus.capabilities.energy.EnergyBank;
import com.pauljoda.nucleus.capabilities.item.InventoryContents;
import com.pauljoda.nucleus.common.blocks.entity.energy.EnergyAndItemHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class ResearchStationBlockEntity extends EnergyAndItemHandler implements MenuProvider {

    public final ContainerData data = new ContainerData() {
        @Override
        public int get(int i) {
            return 0;
        }

        @Override
        public void set(int i, int i1) {

        }

        @Override
        public int getCount() {
            return 4;
        }
    };

    public ResearchStationBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(Registration.RESEARCH_STATION_BLOCK_ENTITY.get(), blockPos, blockState);
    }

    @Override
    protected int getDefaultEnergyStorageSize() {
        return 10000;
    }

    @Override
    protected EnergyBank initializeEnergyStorage() {
        return new EnergyBank(this.getDefaultEnergyStorageSize(), 100, 0);
    }

    @Override
    protected int getInventorySize() {
        return 5;
    }

    @Override
    protected boolean isItemValidForSlot(int index, ItemStack stack) {
        return false;
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
        return new ResearchStationContainer(containerID, inventory, this, data);
    }
}
