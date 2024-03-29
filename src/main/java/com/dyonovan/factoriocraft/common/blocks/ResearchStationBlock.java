package com.dyonovan.factoriocraft.common.blocks;

import com.dyonovan.factoriocraft.common.blocks.blockentities.ResearchStationBlockEntity;
import com.mojang.serialization.MapCodec;
import com.pauljoda.nucleus.capabilities.item.InventoryHolderCapability;
import com.pauljoda.nucleus.common.UpdatingBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class ResearchStationBlock extends UpdatingBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public ResearchStationBlock(Properties props) {
        super(props);

        registerDefaultState(getStateDefinition().any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        var direction = context.getHorizontalDirection();
        return this.defaultBlockState().setValue(FACING, direction);
    }

    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult result) {
        if (level.isClientSide)
            return InteractionResult.SUCCESS;
        else {
            if (level.getBlockEntity(blockPos) instanceof ResearchStationBlockEntity) {
                player.openMenu((MenuProvider) level.getBlockEntity(blockPos), blockPos);
                return InteractionResult.CONSUME;
            }
        }
        return InteractionResult.PASS;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onRemove(BlockState blockState, Level level, BlockPos blockPos, BlockState newBlockState, boolean movedByPiston) {
        if (newBlockState.getBlock() != this) {
            if (level.getBlockEntity(blockPos) instanceof ResearchStationBlockEntity researchStationBlockEntity) {
                var inventory = (InventoryHolderCapability) researchStationBlockEntity.getItemCapability();
                Containers.dropContents(level, blockPos, inventory.inventoryContents.inventory);
            }
        }
        super.onRemove(blockState, level, blockPos, newBlockState, movedByPiston);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(ResearchStationBlock::new);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new ResearchStationBlockEntity(blockPos, blockState);
    }
}
