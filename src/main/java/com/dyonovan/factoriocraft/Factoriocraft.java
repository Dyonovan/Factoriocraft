package com.dyonovan.factoriocraft;

import com.dyonovan.factoriocraft.lib.Registration;
import com.dyonovan.factoriocraft.providers.FCLootTableProvider;
import com.dyonovan.factoriocraft.providers.LangProvider;
import com.mojang.logging.LogUtils;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.slf4j.Logger;

@Mod(Factoriocraft.MODID)
public class Factoriocraft
{
    public static final String MODID = "factoriocraft";

    private static final Logger LOGGER = LogUtils.getLogger();

    public Factoriocraft(IEventBus modEventBus)
    {
        modEventBus.addListener(this::gatherData);

        Registration.init(modEventBus);
    }

    private void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();

        generator.addProvider(event.includeServer(), new FCLootTableProvider(packOutput));
        generator.addProvider(event.includeClient(), new LangProvider(packOutput, "en_us"));
    }
}
