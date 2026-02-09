package com.angelin01.drinkandstretch;

import com.angelin01.drinkandstretch.toasts.DrinkAndStretchToast;
import com.angelin01.drinkandstretch.toasts.ToastDispatcher;
import com.angelin01.drinkandstretch.toasts.ToastVariants;
import net.minecraft.resources.ResourceLocation;

public final class DrinkAndStretch {
	public static final String MOD_ID = "drinkandstretch";

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

		DrinkAndStretch.periodicToaster.schedule(
			7,
			ToastDispatcher.show(
				DrinkAndStretchToast.DrinkAndStretchToastId.DRINK,
				ToastVariants.DRINK
			)
		);

		DrinkAndStretch.periodicToaster.schedule(
			10,
			ToastDispatcher.show(
				DrinkAndStretchToast.DrinkAndStretchToastId.STRETCH,
				ToastVariants.STRETCH
			)
		);
	}

	public static void stopPeriodicReminders() {
		DrinkAndStretch.periodicToaster.cancelAll();
	}
}
