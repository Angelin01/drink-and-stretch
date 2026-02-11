package com.angelin01.drinkandstretch;

import com.angelin01.drinkandstretch.config.DrinkAndStretchConfig;
import com.angelin01.drinkandstretch.toasts.DrinkAndStretchToast;
import com.angelin01.drinkandstretch.toasts.ToastDispatcher;
import com.angelin01.drinkandstretch.toasts.ToastVariants;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;

@Environment(EnvType.CLIENT)
public final class DrinkAndStretch {
	public static final String MOD_ID = "drinkandstretch";

	private static ReminderScheduler periodicToaster;
	private static DrinkAndStretchConfig config;
	private static boolean periodRemindersRunning = false;

	public static void init() {
		System.out.println("Hello from common!");
		DrinkAndStretch.periodicToaster = new ReminderScheduler();
	}

	public static void setConfig(DrinkAndStretchConfig config) {
		DrinkAndStretch.config = config;
	}

	public static void onConfigSave(DrinkAndStretchConfig config) {
		DrinkAndStretch.config = config;
		if (DrinkAndStretch.periodRemindersRunning) {
			DrinkAndStretch.startPeriodicReminders();
		}
	}

	public static ResourceLocation resourceLocation(String path) {
		return ResourceLocation.fromNamespaceAndPath(DrinkAndStretch.MOD_ID, path);
	}

	public static void startPeriodicReminders() {
		DrinkAndStretch.periodicToaster.cancelAll();

		if (DrinkAndStretch.config.isDrinkReminderEnabled()) {
			DrinkAndStretch.periodicToaster.schedule(
				DrinkAndStretch.config.getDrinkReminderInterval(),
				ToastDispatcher.show(
					DrinkAndStretchToast.DrinkAndStretchToastId.DRINK,
					ToastVariants.DRINK
				)
			);
		}

		if (DrinkAndStretch.config.isStretchReminderEnabled()) {
			DrinkAndStretch.periodicToaster.schedule(
				DrinkAndStretch.config.getStretchReminderInterval(),
				ToastDispatcher.show(
					DrinkAndStretchToast.DrinkAndStretchToastId.STRETCH,
					ToastVariants.STRETCH
				)
			);
		}

		DrinkAndStretch.periodRemindersRunning = true;
	}

	public static void stopPeriodicReminders() {
		DrinkAndStretch.periodicToaster.cancelAll();
		DrinkAndStretch.periodRemindersRunning = false;
	}
}
