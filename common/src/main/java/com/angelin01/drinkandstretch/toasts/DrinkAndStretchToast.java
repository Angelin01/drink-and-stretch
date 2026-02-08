package com.angelin01.drinkandstretch.toasts;

import com.angelin01.drinkandstretch.DrinkAndStretch;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.client.gui.components.toasts.ToastComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class DrinkAndStretchToast implements Toast {
	private static final long DISPLAY_DURATION = 5000L;

	private static final ResourceLocation BG_SPRITE = DrinkAndStretch.resourceLocation("toast/background");
	private static final int BG_WIDTH = 160;
	private static final int BG_HEIGHT = 32;
	private static final int BG_SIDE_CAP_WIDTH = 40;
	private static final int BG_MIDDLE_U = DrinkAndStretchToast.BG_SIDE_CAP_WIDTH;
	private static final int BG_MIDDLE_WIDTH = DrinkAndStretchToast.BG_WIDTH - DrinkAndStretchToast.BG_SIDE_CAP_WIDTH * 2;

	private static final int TEXT_LEFT_MARGIN = 29;
	private static final int TEXT_RIGHT_MARGIN = 4;

	private static final int ICON_MARGIN = 8;
	private static final int ICON_SIZE = 16;

	private final DrinkAndStretchToastId id;
	private final int width;
	private Component title;
	private FormattedCharSequence message;
	private long lastChanged;
	private boolean changed;

	public DrinkAndStretchToast(DrinkAndStretchToastId id, Component title, @Nullable Component message) {
		this(
				id,
				title,
				message != null ? message.getVisualOrderText() : null,
				Math.max(
						DrinkAndStretchToast.BG_WIDTH,
						DrinkAndStretchToast.TEXT_LEFT_MARGIN + DrinkAndStretchToast.TEXT_RIGHT_MARGIN + Math.max(
								Minecraft.getInstance().font.width(title),
								message == null ? 0 : Minecraft.getInstance().font.width(message)
						)
				)
		);
	}

	private DrinkAndStretchToast(DrinkAndStretchToastId id, Component title, FormattedCharSequence message, int width) {
		this.id = id;
		this.title = title;
		this.message = message;
		this.width = width;
	}

	public static void add(ToastComponent toastComponent, DrinkAndStretchToastId id, Component component, @Nullable Component component2) {
		toastComponent.addToast(new DrinkAndStretchToast(id, component, component2));
	}

	public static void addOrUpdate(ToastComponent toastComponent, DrinkAndStretchToastId id, Component component, @Nullable Component component2) {
		DrinkAndStretchToast toast = toastComponent.getToast(DrinkAndStretchToast.class, id);
		if (toast == null) {
			DrinkAndStretchToast.add(toastComponent, id, component, component2);
		} else {
			toast.reset(component, component2);
		}
	}

	@Override
	public Toast.@NotNull Visibility render(GuiGraphics guiGraphics, ToastComponent toastComponent, long nowMillis) {
		if (this.changed) {
			this.lastChanged = nowMillis;
			this.changed = false;
		}

		this.renderBackground(guiGraphics);
		this.renderIcon(guiGraphics);
		this.renderText(guiGraphics, toastComponent);

		final double totalDuration = DrinkAndStretchToast.DISPLAY_DURATION * toastComponent.getNotificationDisplayTimeMultiplier();
		final long elapsed = nowMillis - this.lastChanged;
		return elapsed < totalDuration ? Toast.Visibility.SHOW : Toast.Visibility.HIDE;
	}

	private void renderBackground(GuiGraphics g) {
		final int targetWidth = this.width();
		final int height = DrinkAndStretchToast.BG_HEIGHT;

		final int left = DrinkAndStretchToast.BG_SIDE_CAP_WIDTH;
		final int right = DrinkAndStretchToast.BG_SIDE_CAP_WIDTH;
		final int middleTargetWidth = targetWidth - left - right;

		g.blitSprite(
				DrinkAndStretchToast.BG_SPRITE,
				DrinkAndStretchToast.BG_WIDTH, DrinkAndStretchToast.BG_HEIGHT,
				0, 0,
				0, 0,
				left, height
		);

		for (int x = 0; x < middleTargetWidth; x += DrinkAndStretchToast.BG_MIDDLE_WIDTH) {
			int sliceWidth = Math.min(DrinkAndStretchToast.BG_MIDDLE_WIDTH, middleTargetWidth - x);
			g.blitSprite(
					DrinkAndStretchToast.BG_SPRITE,
					DrinkAndStretchToast.BG_WIDTH, DrinkAndStretchToast.BG_HEIGHT,
					DrinkAndStretchToast.BG_MIDDLE_U, 0,
					left + x, 0,
					sliceWidth, height
			);
		}

		g.blitSprite(
				DrinkAndStretchToast.BG_SPRITE,
				DrinkAndStretchToast.BG_WIDTH, DrinkAndStretchToast.BG_HEIGHT,
				DrinkAndStretchToast.BG_WIDTH - right, 0,
				left + middleTargetWidth, 0,
				right, height
		);
	}

	private void renderIcon(GuiGraphics g) {
		g.blitSprite(
				this.id.icon,
				DrinkAndStretchToast.ICON_MARGIN,
				DrinkAndStretchToast.ICON_MARGIN,
				DrinkAndStretchToast.ICON_SIZE,
				DrinkAndStretchToast.ICON_SIZE
		);
	}

	private void renderText(GuiGraphics g, ToastComponent toastComponent) {
		final int posX = DrinkAndStretchToast.TEXT_LEFT_MARGIN;
		final int titleColor = 0x19e0fa;
		final int messageColor = 0xFFFFFF;
		final Font font = toastComponent.getMinecraft().font;
		g.drawString(font, this.title, posX, 6, titleColor, true);
		g.drawString(font, this.message, posX, 18, messageColor, true);
	}

	public void reset(Component title, @Nullable Component message) {
		this.title = title;
		this.message = message != null ? message.getVisualOrderText() : null;
		this.changed = true;
	}

	@Override
	public int width() {
		return this.width;
	}

	@Override
	public int height() {
		return DrinkAndStretchToast.BG_HEIGHT;
	}

	@Override
	public int slotCount() {
		return Toast.super.slotCount();
	}

	@Environment(EnvType.CLIENT)
	public final static class DrinkAndStretchToastId {
		public static final DrinkAndStretchToastId DRINK = new DrinkAndStretchToastId("toast/icon_drink");
		public static final DrinkAndStretchToastId STRETCH = new DrinkAndStretchToastId("toast/icon_stretch");

		final ResourceLocation icon;

		private DrinkAndStretchToastId(String path) {
			this.icon = DrinkAndStretch.resourceLocation(path);
		}
	}
}
