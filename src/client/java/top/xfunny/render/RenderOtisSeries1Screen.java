package top.xfunny.render;


import org.mtr.core.data.Lift;
import org.mtr.core.data.LiftDirection;
import org.mtr.libraries.it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.mtr.libraries.it.unimi.dsi.fastutil.objects.ObjectArraySet;
import org.mtr.libraries.it.unimi.dsi.fastutil.objects.ObjectObjectImmutablePair;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockEntityRenderer;
import org.mtr.mapping.mapper.DirectionHelper;
import org.mtr.mapping.mapper.GraphicsHolder;
import org.mtr.mapping.mapper.PlayerHelper;
import org.mtr.mod.Init;
import org.mtr.mod.InitClient;
import org.mtr.mod.block.BlockLiftTrackFloor;
import org.mtr.mod.block.IBlock;
import org.mtr.mod.client.IDrawing;
import org.mtr.mod.data.IGui;
import org.mtr.mod.render.MainRenderer;
import org.mtr.mod.render.QueuedRenderLayer;
import org.mtr.mod.render.StoredMatrixTransformations;
import top.xfunny.TextureCache;
import top.xfunny.block.OtisSeries1Screen;
import top.xfunny.item.YteGroupLiftButtonsLinker;
import top.xfunny.item.YteLiftButtonsLinker;
import top.xfunny.resource.TextureList;
import top.xfunny.util.GetLiftDetails;
import top.xfunny.util.ReverseRendering;

import java.util.Comparator;

public class RenderOtisSeries1Screen extends BlockEntityRenderer<OtisSeries1Screen.BlockEntity> implements DirectionHelper, IGui, IBlock {

	private static final int HOVER_COLOR = 0xFFADD8E6;
	private static final int PRESSED_COLOR = 0xFF0000FF;
	private static final float ARROW_SPEED = 0.04F;
	private static final Identifier ARROW_TEXTURE = new Identifier(Init.MOD_ID, "textures/block/lift_arrow.png");
	private static final Identifier BUTTON_TEXTURE = new Identifier(Init.MOD_ID, "textures/block/lift_button.png");

	public RenderOtisSeries1Screen(Argument dispatcher) {
		super(dispatcher);
	}

	@Override
	public void render(OtisSeries1Screen.BlockEntity blockEntity, float tickDelta, GraphicsHolder graphicsHolder1, int light, int overlay){
		final World world = blockEntity.getWorld2();
		if (world == null) {
			return;
		}

		final ClientPlayerEntity clientPlayerEntity = MinecraftClient.getInstance().getPlayerMapped();
		if (clientPlayerEntity == null) {
			return;
		}

		final BlockPos blockPos = blockEntity.getPos2();
		final BlockState blockState = world.getBlockState(blockPos);
		final Direction facing = IBlock.getStatePropertySafe(blockState, FACING);
		final boolean holdingLinker = PlayerHelper.isHolding(PlayerEntity.cast(clientPlayerEntity), item -> item.data instanceof YteLiftButtonsLinker || item.data instanceof YteGroupLiftButtonsLinker);
		// 创建一个存储矩阵转换的实例，用于后续的渲染操作
		// 参数为方块的中心位置坐标 (x, y, z)
		final StoredMatrixTransformations storedMatrixTransformations1 = new StoredMatrixTransformations(blockPos.getX() + 0.5, blockPos.getY(), blockPos.getZ() + 0.5);


		// 定义一个布尔数组，用于记录按钮的状态
		// 数组顺序：向下按钮存在、向上按钮存在、向下按钮被按下、向上按钮被按下
		final boolean[] buttonStates = {false, false, false, false};

		// 创建一个对象列表，用于存储排序后的位置和升降机的配对信息
		final ObjectArrayList<ObjectObjectImmutablePair<BlockPos, Lift>> sortedPositionsAndLifts = new ObjectArrayList<>();

		// 遍历每个轨道位置，进行后续处理
		blockEntity.forEachTrackPosition(trackPosition -> {
			// 手持连接器进行连线
			if (world.getBlockState(trackPosition).getBlock().data instanceof BlockLiftTrackFloor) {
				final Direction trackFacing = IBlock.getStatePropertySafe(world, trackPosition, FACING);
				RenderLiftObjectLink.RenderLiftObjectLink(
						storedMatrixTransformations1,
						new Vector3d(facing.getOffsetX() / 2F, 0.5, facing.getOffsetZ() / 2F),
						new Vector3d(trackPosition.getX() - blockPos.getX() + trackFacing.getOffsetX() / 2F, trackPosition.getY() - blockPos.getY() + 0.5, trackPosition.getZ() - blockPos.getZ() + trackFacing.getOffsetZ() / 2F),
						holdingLinker
				);
			}

			// Figure out whether the up and down buttons should be rendered
			OtisSeries1Screen.hasButtonsClient(trackPosition, buttonStates, (floorIndex, lift) -> {
				// 确定是否渲染上下按钮，基于当前trackPosition和楼层信息
				// 该方法通过floorIndex和lift来决定是否添加trackPosition和lift到已排序的列表中
				// 同时，根据lift的方向（上或下），更新buttonStates数组以指示按钮的渲染状态
				// 这里使用lambda表达式来处理按钮状态的逻辑
				sortedPositionsAndLifts.add(new ObjectObjectImmutablePair<>(trackPosition, lift));
				final ObjectArraySet<LiftDirection> instructionDirections = lift.hasInstruction(floorIndex);
				instructionDirections.forEach(liftDirection -> {
					switch (liftDirection) {
						case DOWN:
							buttonStates[2] = true;
							break;
						case UP:
							buttonStates[3] = true;
							break;
					}
				});
			});
		});

		// Sort list and only render the two closest lifts
		sortedPositionsAndLifts.sort(Comparator.comparingInt(sortedPositionAndLift -> blockPos.getManhattanDistance(new Vector3i(sortedPositionAndLift.left().data))));

		// Check whether the player is looking at the top or bottom button
		final HitResult hitResult = MinecraftClient.getInstance().getCrosshairTargetMapped();
		final boolean lookingAtTopHalf;
		final boolean lookingAtBottomHalf;
		if (hitResult == null || !IBlock.getStatePropertySafe(blockState, OtisSeries1Screen.UNLOCKED)) {
			lookingAtTopHalf = false;
			lookingAtBottomHalf = false;
		} else {
			final Vector3d hitLocation = hitResult.getPos();
			final double hitY = MathHelper.fractionalPart(hitLocation.getYMapped());
			final boolean inBlock = hitY < 0.5 && Init.newBlockPos(hitLocation.getXMapped(), hitLocation.getYMapped(), hitLocation.getZMapped()).equals(blockPos);
			lookingAtTopHalf = inBlock && (!buttonStates[0] || hitY > 0.25);
			lookingAtBottomHalf = inBlock && (!buttonStates[1] || hitY < 0.25);
		}

		final StoredMatrixTransformations storedMatrixTransformations2 = storedMatrixTransformations1.copy();
		storedMatrixTransformations2.add(graphicsHolder -> {
			graphicsHolder.rotateYDegrees(-facing.asRotation());
			graphicsHolder.translate(0, 0, 0.4375 - SMALL_OFFSET);
		});

		// 根据按钮状态渲染按钮
		// 第一个按钮的渲染逻辑

		// 检查排序后的电梯位置列表是否非空
		if (!sortedPositionsAndLifts.isEmpty()) {
			// 确定要渲染的电梯数量，最多为2个
			final int count = Math.min(2, sortedPositionsAndLifts.size());
			// 设置每个电梯显示的宽度，根据数量不同而变化
			final float width = count == 1 ? 0.25F: 0.495F;

			// 创建当前矩阵变换的副本以供后续修改
			final StoredMatrixTransformations storedMatrixTransformations3 = storedMatrixTransformations2.copy();
			// 添加旋转和平移变换
			storedMatrixTransformations3.add(graphicsHolder -> {
				graphicsHolder.rotateZDegrees(180);
				graphicsHolder.translate(-width / 2, 0, 0);
			});

			// 渲染黑色背景
			MainRenderer.scheduleRender(new Identifier(Init.MOD_ID, "textures/block/black.png"), false, QueuedRenderLayer.EXTERIOR, (graphicsHolder, offset) -> {
				storedMatrixTransformations3.transform(graphicsHolder, offset);
				IDrawing.drawTexture(graphicsHolder, 0.025F, -0.375F, 0.2F, 0.25F, Direction.UP, light);
				graphicsHolder.pop();
			});

			// 根据按钮朝向判断两个最近的电梯是否需要反转渲染顺序
			final boolean reverseRendering = count > 1 && ReverseRendering.reverseRendering(facing.rotateYCounterclockwise(), sortedPositionsAndLifts.get(0).left(), sortedPositionsAndLifts.get(1).left());
			// 遍历要渲染的每个电梯
			for (int i = 0; i < count; i++) {
				// 计算当前电梯显示的x位置，考虑反转渲染顺序
				final double x = ((reverseRendering ? count - i - 1 : i) + 0.5) * width / count;
				// 创建另一个矩阵变换的副本用于当前电梯
				final StoredMatrixTransformations storedMatrixTransformations4 = storedMatrixTransformations3.copy();
				// 添加平移变换以定位电梯显示
				storedMatrixTransformations4.add(graphicsHolder -> graphicsHolder.translate(x, -0.875, -SMALL_OFFSET));

				// 渲染当前电梯的显示
				renderLiftDisplay(storedMatrixTransformations4, world, sortedPositionsAndLifts.get(i).right(), width * 4  / count, 0.2F,0.2F,0.2F);

			}
		}
	}
	private void renderLiftDisplay(StoredMatrixTransformations storedMatrixTransformations, World world , Lift lift ,float width,float width1,float height1,float height) {
		// 获取电梯的详细信息，包括运行方向和楼层信息
		final ObjectObjectImmutablePair<LiftDirection, ObjectObjectImmutablePair<String, String>> liftDetails = GetLiftDetails.getLiftDetails(world, lift, Init.positionToBlockPos(lift.getCurrentFloor().getPosition()));
		final LiftDirection liftDirection = liftDetails.left();
		final String floorNumber = liftDetails.right().left();
		final String floorDescription = liftDetails.right().right();

		// 判断楼层编号和描述是否为空
		final boolean noFloorNumber = floorNumber.isEmpty();
		final boolean noFloorDisplay = floorDescription.isEmpty();
		final float y = height; // 箭头的Y轴位置

		// 渲染楼层信息
		if (!noFloorNumber || !noFloorDisplay) {
			float offset1;
			final String text = String.format("%s%s", floorNumber, noFloorNumber? " " : ""); // 合并楼层信息文本

			MainRenderer.scheduleRender(TextureList.instance.getOtisSeries1ScreenDisplay(text, 0x009900).identifier, false, QueuedRenderLayer.LIGHT_TRANSLUCENT, (graphicsHolder, offset) -> {
				storedMatrixTransformations.transform(graphicsHolder, offset);
				// 绘制楼层信息纹理
				IDrawing.drawTexture(graphicsHolder, -width + 0.9F, y + 0.325F, width1, height1, 0, 0, 1.75F, 1.75F, Direction.UP, ARGB_WHITE, GraphicsHolder.getDefaultLight());//楼层数字尺寸设置
				graphicsHolder.pop();
			});
		}
	}
}
