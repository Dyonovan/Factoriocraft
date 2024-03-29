package com.dyonovan.factoriocraft.lib;

import com.dyonovan.factoriocraft.Factoriocraft;
import com.dyonovan.factoriocraft.common.blocks.ResearchStationBlock;
import com.dyonovan.factoriocraft.common.blocks.blockentities.ResearchStationBlockEntity;
import com.dyonovan.factoriocraft.common.containers.ResearchStationContainer;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = Factoriocraft.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Registration {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Factoriocraft.MODID);

    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Factoriocraft.MODID);

    private static final DeferredRegister<MenuType<?>> CONTAINERS =
            DeferredRegister.create(Registries.MENU, Factoriocraft.MODID);

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Factoriocraft.MODID);

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Factoriocraft.MODID);

    public static void init(IEventBus bus) {
        BLOCKS.register(bus);
        BLOCK_ENTITY_TYPES.register(bus);
        ITEMS.register(bus);
        CONTAINERS.register(bus);
        CREATIVE_MODE_TABS.register(bus);
    }

    //BLOCKS
    public static final DeferredHolder<Block, ResearchStationBlock> RESEARCH_STATION_BLOCK =
            BLOCKS.register("research_station",
                    () -> new ResearchStationBlock(BlockBehaviour.Properties.of().strength(2.0F)));
    public static final DeferredHolder<Item, BlockItem> RESEARCH_STATION_BLOCK_ITEM =
            ITEMS.register("research_station", () -> new BlockItem(RESEARCH_STATION_BLOCK.get(), new Item.Properties()));

    //BLOCK ENTITIES
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ResearchStationBlockEntity>> RESEARCH_STATION_BLOCK_ENTITY =
            BLOCK_ENTITY_TYPES.register("research_station",
                    () -> BlockEntityType.Builder.of(ResearchStationBlockEntity::new, RESEARCH_STATION_BLOCK.get()).build(null));

    //CONTAINERS
    public static final DeferredHolder<MenuType<?>, MenuType<ResearchStationContainer>> RESEARCH_STATION_CONTAINER =
            CONTAINERS.register("research_station", () -> IMenuTypeExtension.create(ResearchStationContainer::new));

    //CREATIVE TAB
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> FC_TAB = CREATIVE_MODE_TABS.register("fc_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .title(Component.translatable("itemGroup." + Factoriocraft.MODID))
            .icon(() -> RESEARCH_STATION_BLOCK_ITEM.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(RESEARCH_STATION_BLOCK_ITEM.get());
            }).build());

    @SubscribeEvent
    private static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(
                Capabilities.EnergyStorage.BLOCK, RESEARCH_STATION_BLOCK_ENTITY.get(), ResearchStationBlockEntity::getEnergyBank
        );
    }
}
