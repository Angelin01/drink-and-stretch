package com.angelin01.drinkandstretch;

import com.angelin01.drinkandstretch.toasts.DrinkAndStretchToast;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public final class DrinkAndStretch {
	public static final String MOD_ID = "drinkandstretch";
	private static final Object DRINK_AND_STRETCH = new Object();

	private static ReminderScheduler periodicToaster;

	public static void init() {
		System.out.println("Hello from common!");
		DrinkAndStretch.periodicToaster = new ReminderScheduler();
	}

	public static ResourceLocation resourceLocation(String path) {
		return ResourceLocation.fromNamespaceAndPath(DrinkAndStretch.MOD_ID, path);
	}

	public static void startPeriodicReminders() {
		DrinkAndStretch.periodicToaster.cancelAll();
		DrinkAndStretch.periodicToaster.schedule(5, DrinkAndStretch.showToast(DrinkAndStretchToast.DrinkAndStretchToastId.DRINK, "Drink Water", "Do it NOW!"));
		DrinkAndStretch.periodicToaster.schedule(15, DrinkAndStretch.showToast(DrinkAndStretchToast.DrinkAndStretchToastId.STRETCH, "Stretch", "Get up you lazy bum"));
	}

	public static void stopPeriodicReminders() {
		DrinkAndStretch.periodicToaster.cancelAll();
	}

	private static Runnable showToast(DrinkAndStretchToast.DrinkAndStretchToastId id, String title, String content) {
		return () -> {
			Minecraft minecraft = Minecraft.getInstance();
			minecraft.execute(() -> DrinkAndStretchToast.add(
				minecraft.getToasts(),
				id,
				Component.literal(title),
				Component.literal(content)
			));
		};
	}
}
