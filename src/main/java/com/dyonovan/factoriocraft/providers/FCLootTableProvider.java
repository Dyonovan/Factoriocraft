package com.dyonovan.factoriocraft.providers;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;

public class FCLootTableProvider extends LootTableProvider {
    public FCLootTableProvider(PackOutput packOutput) {
        super(packOutput, Set.of(), List.of(new SubProviderEntry(FCBlockLoot::new, LootContextParamSets.BLOCK)));
    }
}
