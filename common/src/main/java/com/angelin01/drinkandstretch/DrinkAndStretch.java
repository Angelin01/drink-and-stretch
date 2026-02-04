package com.angelin01.drinkandstretch;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.toasts.SystemToast;
import net.minecraft.network.chat.Component;

public final class DrinkAndStretch {
	public static final String MOD_ID = "drinkandstretch";

	public static void init() {
		System.out.println("Hello from common!");
	}

	public static final SystemToast.SystemToastId DRINK_AND_STRETCH = new SystemToast.SystemToastId();

	public static void showToast() {
		Minecraft minecraft = Minecraft.getInstance();
		minecraft.execute(() -> SystemToast.add(
			minecraft.getToasts(),
			DrinkAndStretch.DRINK_AND_STRETCH,
			Component.literal("Drink and Stretch"),
			Component.literal("Hello from Drink and Stretch!")
		));
	}
}
