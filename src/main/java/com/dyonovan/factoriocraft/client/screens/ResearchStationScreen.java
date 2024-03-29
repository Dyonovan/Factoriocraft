package com.dyonovan.factoriocraft.client.screens;

import com.dyonovan.factoriocraft.Factoriocraft;
import com.dyonovan.factoriocraft.common.containers.ResearchStationContainer;
import com.pauljoda.nucleus.client.gui.MenuBase;
import com.pauljoda.nucleus.client.gui.widget.control.MenuWidgetButton;
import com.pauljoda.nucleus.client.gui.widget.display.MenuWidgetTextureAnimated;
import com.pauljoda.nucleus.util.EnergyUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ResearchStationScreen extends MenuBase<ResearchStationContainer> {

    protected ResearchStationContainer container;

    public ResearchStationScreen(ResearchStationContainer inventory, Inventory playerInventory, Component title) {
        super(inventory, playerInventory, title, 175, 165, new ResourceLocation(Factoriocraft.MODID, "textures/gui/research_station.png"));

        container = inventory;
    }

    @Override
    protected void addComponents() {
        components.add(new MenuWidgetButton(this, 10, 32, 202, 0, 50, 20,
                Factoriocraft.MODID + ".btn.research_queue") {
            @Override
            protected void doAction() {

            }
        });

        components.add(new MenuWidgetTextureAnimated(this, 152, 13, 179, 61,
                16, 63, MenuWidgetTextureAnimated.ANIMATION_DIRECTION.UP) {
            @Override
            protected int getCurrentProgress(int scale) {
                if (container.getEnergyStored() == 0)
                    return 0;

                return (container.getEnergyStored() * scale) / container.getMaxEnergy();
            }

            @Override
            public @NotNull List<Component> getDynamicToolTip(int mouseX, int mouseY) {
                return List.of(Component.literal(EnergyUtils.getEnergyDisplay(container.getEnergyStored())));
            }
        });
    }
}
