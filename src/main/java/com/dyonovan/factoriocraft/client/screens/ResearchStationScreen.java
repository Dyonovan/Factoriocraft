package com.dyonovan.factoriocraft.client.screens;

import com.dyonovan.factoriocraft.Factoriocraft;
import com.dyonovan.factoriocraft.common.containers.ResearchStationContainer;
import com.pauljoda.nucleus.client.gui.MenuBase;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ResearchStationScreen extends MenuBase<ResearchStationContainer> {

    public ResearchStationScreen(ResearchStationContainer inventory, Inventory playerInventory, Component title) {
        super(inventory, playerInventory, title, 175, 165, new ResourceLocation(Factoriocraft.MODID, "textures/gui/research_station.png"));
    }

    @Override
    protected void addComponents() {

    }
}
