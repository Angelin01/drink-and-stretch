package com.angelin01.drinkandstretch;

import com.angelin01.drinkandstretch.config.DrinkAndStretchConfig;
import com.angelin01.drinkandstretch.toasts.DrinkAndStretchToast;
import com.angelin01.drinkandstretch.toasts.ToastDispatcher;
import com.angelin01.drinkandstretch.toasts.ToastVariants;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;

public final class DrinkAndStretch {
	private static final Logger LOGGER = LogUtils.getLogger();
	public static final String MOD_ID = "drinkandstretch";

	private static ReminderScheduler periodicToaster;

	public static void init() {
		DrinkAndStretch.LOGGER.info("Drink and Stretch starting up!");
		DrinkAndStretch.periodicToaster = new ReminderScheduler();
	}

	public static ResourceLocation resourceLocation(String path) {
		return ResourceLocation.fromNamespaceAndPath(DrinkAndStretch.MOD_ID, path);
	}

	public static void startPeriodicReminders() {
		DrinkAndStretch.periodicToaster.cancelAll();

		if (DrinkAndStretchConfig.CLIENT.enableDrinkReminder.get()) {
			int interval = DrinkAndStretchConfig.CLIENT.drinkReminderInterval.get();
			DrinkAndStretch.periodicToaster.schedule(
				interval,
				ToastDispatcher.show(
					DrinkAndStretchToast.DrinkAndStretchToastId.DRINK,
					ToastVariants.DRINK
				)
			);
		}

		if (DrinkAndStretchConfig.CLIENT.enableStretchReminder.get()) {
			int interval = DrinkAndStretchConfig.CLIENT.stretchReminderInterval.get();
			DrinkAndStretch.periodicToaster.schedule(
				interval,
				ToastDispatcher.show(
					DrinkAndStretchToast.DrinkAndStretchToastId.STRETCH,
					ToastVariants.STRETCH
				)
			);
		}
	}

	public static void stopPeriodicReminders() {
		DrinkAndStretch.periodicToaster.cancelAll();
	}
}
