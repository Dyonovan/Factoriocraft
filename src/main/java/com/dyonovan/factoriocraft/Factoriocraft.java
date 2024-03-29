package com.dyonovan.factoriocraft;

import com.dyonovan.factoriocraft.lib.Registration;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(Factoriocraft.MODID)
public class Factoriocraft
{
    public static final String MODID = "factoriocraft";

    private static final Logger LOGGER = LogUtils.getLogger();

    public Factoriocraft(IEventBus modEventBus)
    {
        Registration.init(modEventBus);
    }
}
