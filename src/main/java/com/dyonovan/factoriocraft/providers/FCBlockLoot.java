package com.dyonovan.factoriocraft.providers;

import com.dyonovan.factoriocraft.lib.Registration;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Set;
import java.util.stream.Collectors;

public class FCBlockLoot extends BlockLootSubProvider {

    private static final Set<Item> EXPLOSION_RESISTANT = Set.of();

    protected FCBlockLoot() {
        super(EXPLOSION_RESISTANT, FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.add(Registration.RESEARCH_STATION_BLOCK.get(), this::createNameableBlockEntityTable);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return Registration.BLOCKS.getEntries().stream().map(DeferredHolder::get).collect(Collectors.toList());
    }
}
