package com.angelin01.drinkandstretch;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.toasts.SystemToast;
import net.minecraft.network.chat.Component;

public final class DrinkAndStretch {
	public static final String MOD_ID = "drinkandstretch";
	private static final SystemToast.SystemToastId DRINK_AND_STRETCH = new SystemToast.SystemToastId();

	private static ReminderScheduler periodicToaster;

	public static void init() {
		System.out.println("Hello from common!");
		DrinkAndStretch.periodicToaster = new ReminderScheduler();
	}

	public static void startPeriodicReminders() {
		DrinkAndStretch.periodicToaster.schedule(5, DrinkAndStretch.showToast("Drink Water", "Do it NOW!"));
		DrinkAndStretch.periodicToaster.schedule(15, DrinkAndStretch.showToast("Stretch", "Get up you lazy bum"));
	}

	public static void stopPeriodicReminders() {
		DrinkAndStretch.periodicToaster.cancelAll();
	}

	private static Runnable showToast(String title, String content) {
		return () -> {
			Minecraft minecraft = Minecraft.getInstance();
			minecraft.execute(() -> SystemToast.add(
				minecraft.getToasts(),
				DrinkAndStretch.DRINK_AND_STRETCH,
				Component.literal(title),
				Component.literal(content)
			));
		};
	}
}
