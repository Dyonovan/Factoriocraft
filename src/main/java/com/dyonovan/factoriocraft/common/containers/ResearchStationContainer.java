package com.dyonovan.factoriocraft.common.containers;

import com.dyonovan.factoriocraft.common.blocks.blockentities.ResearchStationBlockEntity;
import com.dyonovan.factoriocraft.lib.Registration;
import com.pauljoda.nucleus.common.container.BaseContainer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.neoforged.neoforge.items.SlotItemHandler;

public class ResearchStationContainer extends BaseContainer {

    protected ResearchStationBlockEntity researchStationBlockEntity;
    protected ContainerData data;

    public ResearchStationContainer(int containerID, Inventory playerInventory, FriendlyByteBuf extraData) {
        this(containerID, playerInventory,
                (ResearchStationBlockEntity) playerInventory.player.level().getBlockEntity(extraData.readBlockPos()),
                new SimpleContainerData(2));
    }

    public ResearchStationContainer(int containerID, Inventory playerInventory,
                                    ResearchStationBlockEntity blockEntity, ContainerData data) {
        super(Registration.RESEARCH_STATION_CONTAINER.get(), containerID, playerInventory, blockEntity.getItemCapability(),
                blockEntity.getLevel(), blockEntity.getBlockPos(), Registration.RESEARCH_STATION_BLOCK.get());

        this.researchStationBlockEntity = blockEntity;
        this.data = data;

        addSlot(new SlotItemHandler(blockEntity.getItemCapability(), 0, 62, 24));
        addSlot(new SlotItemHandler(blockEntity.getItemCapability(), 1, 80, 24));
        addSlot(new SlotItemHandler(blockEntity.getItemCapability(), 2, 98, 24));
        addSlot(new SlotItemHandler(blockEntity.getItemCapability(), 3, 62, 42));
        addSlot(new SlotItemHandler(blockEntity.getItemCapability(), 4, 80, 42));
        addSlot(new SlotItemHandler(blockEntity.getItemCapability(), 5, 98, 42));

        addPlayerInventorySlots(8, 84);

        addDataSlots(data);
    }

    public int getMaxEnergy() {
        return this.data.get(0);
    }

    public int getEnergyStored() {
        return this.data.get(1);
    }
}
