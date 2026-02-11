package com.angelin01.drinkandstretch;

import com.angelin01.drinkandstretch.config.DrinkAndStretchConfig;
import com.angelin01.drinkandstretch.reminders.ReminderScheduler;
import com.angelin01.drinkandstretch.reminders.ReminderService;
import com.mojang.logging.LogUtils;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

@Environment(EnvType.CLIENT)
public final class DrinkAndStretch {
	public static final String MOD_ID = "drinkandstretch";
	private static final Logger LOGGER = LogUtils.getLogger();
	private static ReminderService reminderService;
	private static DrinkAndStretchConfig config;

	public static void init() {
		DrinkAndStretch.LOGGER.info("Drink and Stretch getting its water bottle filled up");
		DrinkAndStretch.reminderService = new ReminderService(new ReminderScheduler());
	}

	public static @NotNull ConfigHolder<DrinkAndStretchConfig> setupConfig() {
		AutoConfig.register(DrinkAndStretchConfig.class, Toml4jConfigSerializer::new);

		var configHolder = AutoConfig.getConfigHolder(DrinkAndStretchConfig.class);

		DrinkAndStretch.config = configHolder.getConfig();
		return configHolder;
	}

	public static void onConfigSave(DrinkAndStretchConfig config) {
		DrinkAndStretch.config = config;
		DrinkAndStretch.reminderService.onConfigReload(config);
	}

	public static ResourceLocation resourceLocation(String path) {
		return ResourceLocation.fromNamespaceAndPath(DrinkAndStretch.MOD_ID, path);
	}

	public static void startPeriodicReminders() {
		DrinkAndStretch.reminderService.startOrRestart(DrinkAndStretch.config);
	}

	public static void stopPeriodicReminders() {
		DrinkAndStretch.reminderService.stop();
	}
}
