package com.dyonovan.factoriocraft.providers;

import com.dyonovan.factoriocraft.Factoriocraft;
import com.dyonovan.factoriocraft.lib.Registration;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class LangProvider extends LanguageProvider {

    public LangProvider(PackOutput output, String locale) {
        super(output, Factoriocraft.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        this.addBlock(Registration.RESEARCH_STATION_BLOCK, "Research Station");

        this.add("itemGroup.factoriocraft", "Factoriocraft");

        this.add("factoriocraft.btn.research_queue" , "Queue");
    }
}
