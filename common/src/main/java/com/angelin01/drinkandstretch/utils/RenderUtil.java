package com.angelin01.drinkandstretch.utils;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

public final class RenderUtil {
	public static void blitSprite(GuiGraphics guiGraphics, ResourceLocation resourceLocation, int i, int j, int k, int l, int m, int n, int o, int p) {
		#if MINECRAFT_VERSION >= 1021000
		guiGraphics.blitSprite(resourceLocation, i, j, k, l, m, n, o, p);
		#else
		guiGraphics.blit(resourceLocation, m, n, k, l, o, p, i, j);
		#endif
	}

	public static void blitSprite(GuiGraphics guiGraphics, ResourceLocation resourceLocation, int i, int j, int k, int l) {
		#if MINECRAFT_VERSION >= 1021000
		guiGraphics.blitSprite(resourceLocation, i, j, 0, k, l);
		#else
		guiGraphics.blit(resourceLocation, i, j, 0, 0, k, l, k, l);
		#endif
	}
}
