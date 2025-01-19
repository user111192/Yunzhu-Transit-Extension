package top.xfunny.block;

import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.tool.HolderBase;
import org.mtr.mod.block.IBlock;
import top.xfunny.BlockEntityTypes;
import top.xfunny.block.base.LiftPanelBase;

import javax.annotation.Nonnull;
import java.util.List;

public class SchindlerDSeriesScreen2BlueEven extends LiftPanelBase {
    public SchindlerDSeriesScreen2BlueEven() {
        super(false);
    }

    @Nonnull
    @Override
    public VoxelShape getOutlineShape2(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        switch (IBlock.getStatePropertySafe(state, SIDE)) {
            case LEFT -> {
                return IBlock.getVoxelShapeByDirection(12.25, 9, 0, 16, 14, 0.1, IBlock.getStatePropertySafe(state, FACING));
            }
            case RIGHT -> {
                return IBlock.getVoxelShapeByDirection(0, 9, 0, 3.75, 14, 0.1, IBlock.getStatePropertySafe(state, FACING));
            }
        }
        return VoxelShapes.empty();
    }

    @Nonnull
    @Override
    public BlockEntityExtension createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new SchindlerDSeriesScreen2BlueEven.BlockEntity(blockPos, blockState);
    }

    @Override
    public void addBlockProperties(List<HolderBase<?>> properties) {
        // 添加块的方向属性
        properties.add(FACING);
        properties.add(SIDE);
    }

    public static class BlockEntity extends BlockEntityBase {

        public BlockEntity(BlockPos pos, BlockState state) {
            super(BlockEntityTypes.SCHINDLER_D_SERIES_SCREEN_2_BLUE_EVEN.get(), pos, state);
        }
    }
}
