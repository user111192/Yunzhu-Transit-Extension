package top.xfunny.data;

import org.mtr.mapping.holder.BooleanProperty;
import org.mtr.mapping.holder.DirectionProperty;
import org.mtr.mapping.holder.EnumProperty;
import org.mtr.mapping.holder.IntegerProperty;
import org.mtr.mapping.mapper.DirectionHelper;
import org.mtr.mod.block.IBlock;

/**
 * Stores all block properties JCM uses. Block classes from JCM should reference the block properties in here
 */
public interface BlockProperties {
    public static final DirectionProperty FACING = DirectionHelper.FACING;
    public static final IntegerProperty BARRIER_FENCE_TYPE = IntegerProperty.of("type", 0, 10);
    public static final BooleanProperty BARRIER_FLIPPED = BooleanProperty.of("flipped");
}
