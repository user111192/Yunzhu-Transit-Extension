package top.xfunny;

import org.mtr.core.tool.Utilities;
import org.mtr.mapping.holder.NativeImage;
import org.mtr.mod.config.Config;
import org.mtr.mod.data.IGui;


public class YteRouteMapGenerator implements IGui {
	private static int scale;
	private static int lineSize;
	private static int fontSizeBig;
	private static int fontSizeSmall;

	public static void setConstants() {
		scale = (int) Math.pow(2, Config.getClient().getDynamicTextureResolution() + 5);
		lineSize = scale / 8;
		fontSizeBig = lineSize * 2;
		fontSizeSmall = fontSizeBig / 2;
	}
	public static void clearColor(NativeImage nativeImage, int color) {
		for (int x = 0; x < nativeImage.getWidth(); x++) {
			for (int y = 0; y < nativeImage.getHeight(); y++) {
				if (nativeImage.getColor(x, y) == color) {
					nativeImage.setPixelColor(x, y, 0);
				}
			}
		}
	}
	public static void drawString(NativeImage nativeImage, byte[] pixels, int x, int y, int[] textDimensions, IGui.HorizontalAlignment horizontalAlignment, IGui.VerticalAlignment verticalAlignment, int backgroundColor, int textColor, boolean rotate90) {
		if (((backgroundColor >> 24) & 0xFF) > 0) {
			for (int drawX = 0; drawX < textDimensions[rotate90 ? 1 : 0]; drawX++) {
				for (int drawY = 0; drawY < textDimensions[rotate90 ? 0 : 1]; drawY++) {
					drawPixelSafe(nativeImage, (int) horizontalAlignment.getOffset(drawX + x, textDimensions[rotate90 ? 1 : 0]), (int) verticalAlignment.getOffset(drawY + y, textDimensions[rotate90 ? 0 : 1]), backgroundColor);
				}
			}
		}
		int drawX = 0;
		int drawY = rotate90 ? textDimensions[0] - 1 : 0;
		for (int i = 0; i < textDimensions[0] * textDimensions[1]; i++) {
			blendPixel(nativeImage, (int) horizontalAlignment.getOffset(x + drawX, textDimensions[rotate90 ? 1 : 0]), (int) verticalAlignment.getOffset(y + drawY, textDimensions[rotate90 ? 0 : 1]), ((pixels[i] & 0xFF) << 24) + (textColor & RGB_WHITE));
			if (rotate90) {
				drawY--;
				if (drawY < 0) {
					drawY = textDimensions[0] - 1;
					drawX++;
				}
			} else {
				drawX++;
				if (drawX == textDimensions[0]) {
					drawX = 0;
					drawY++;
				}
			}
		}
	}

	private static void blendPixel(NativeImage nativeImage, int x, int y, int color) {
		if (Utilities.isBetween(x, 0, nativeImage.getWidth() - 1) && Utilities.isBetween(y, 0, nativeImage.getHeight() - 1)) {
			final float percent = (float) ((color >> 24) & 0xFF) / 0xFF;
			if (percent > 0) {
				final int existingPixel = nativeImage.getColor(x, y);
				final boolean existingTransparent = ((existingPixel >> 24) & 0xFF) == 0;
				final int r1 = existingTransparent ? 0xFF : (existingPixel & 0xFF);
				final int g1 = existingTransparent ? 0xFF : ((existingPixel >> 8) & 0xFF);
				final int b1 = existingTransparent ? 0xFF : ((existingPixel >> 16) & 0xFF);
				final int r2 = (color >> 16) & 0xFF;
				final int g2 = (color >> 8) & 0xFF;
				final int b2 = color & 0xFF;
				final float inversePercent = 1 - percent;
				final int finalColor = ARGB_BLACK | (((int) (r1 * inversePercent + r2 * percent) << 16) + ((int) (g1 * inversePercent + g2 * percent) << 8) + (int) (b1 * inversePercent + b2 * percent));
				drawPixelSafe(nativeImage, x, y, finalColor);
			}
		}
	}

	private static void drawPixelSafe(NativeImage nativeImage, int x, int y, int color) {
		if (Utilities.isBetween(x, 0, nativeImage.getWidth() - 1) && Utilities.isBetween(y, 0, nativeImage.getHeight() - 1)) {
			nativeImage.setPixelColor(x, y, invertColor(color));
		}
	}

	public static int invertColor(int color) {
		return ((color & ARGB_BLACK) != 0 ? ARGB_BLACK : 0) + ((color & 0xFF) << 16) + (color & 0xFF00) + ((color & 0xFF0000) >> 16);
	}


}

