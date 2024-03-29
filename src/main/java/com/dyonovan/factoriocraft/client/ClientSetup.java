package com.dyonovan.factoriocraft.client;

import com.dyonovan.factoriocraft.Factoriocraft;
import com.dyonovan.factoriocraft.client.screens.ResearchStationScreen;
import com.dyonovan.factoriocraft.lib.Registration;
import net.minecraft.client.gui.screens.MenuScreens;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Factoriocraft.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    @SubscribeEvent
    public static void setupClient(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            //noinspection deprecation
            MenuScreens.register(Registration.RESEARCH_STATION_CONTAINER.get(), ResearchStationScreen::new);
        });
    }
}
