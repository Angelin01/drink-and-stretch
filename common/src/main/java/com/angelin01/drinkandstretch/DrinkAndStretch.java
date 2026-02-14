package com.angelin01.drinkandstretch;

import com.angelin01.drinkandstretch.config.DrinkAndStretchConfig;
import com.angelin01.drinkandstretch.reminders.CombatTracker;
import com.angelin01.drinkandstretch.reminders.ReminderScheduler;
import com.angelin01.drinkandstretch.reminders.ReminderService;
import com.mojang.logging.LogUtils;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Environment(EnvType.CLIENT)
public final class DrinkAndStretch {
	public static final String MOD_ID = "drinkandstretch";
	private static final Logger LOGGER = LogUtils.getLogger();
	private static ReminderService reminderService;
	private static DrinkAndStretchConfig config;
	private static CombatTracker combatTracker;

	public static void init() {
		DrinkAndStretch.LOGGER.info("Drink and Stretch getting its water bottle filled up");
		final ScheduledExecutorService executor = DrinkAndStretch.buildExecutor();
		DrinkAndStretch.combatTracker = new CombatTracker(executor);
		DrinkAndStretch.reminderService = new ReminderService(new ReminderScheduler(executor), DrinkAndStretch.combatTracker);
	}

	public static @NotNull ConfigHolder<DrinkAndStretchConfig> setupConfig() {
		AutoConfig.register(DrinkAndStretchConfig.class, Toml4jConfigSerializer::new);

		var configHolder = AutoConfig.getConfigHolder(DrinkAndStretchConfig.class);

		DrinkAndStretch.config = configHolder.getConfig();
		return configHolder;
	}

	public static void onConfigSave(DrinkAndStretchConfig config) {
		DrinkAndStretch.reminderService.onConfigReload(config);
	}

	public static void onPlayerAttack(LocalPlayer player, Entity entity) {
		DrinkAndStretch.LOGGER.debug("Player {} attacked entity {}", player, entity);
		DrinkAndStretch.combatTracker.onPlayerAttack(player, entity);
	}

	public static void onPlayerReceiveDamage(LocalPlayer player, DamageSource source) {
		DrinkAndStretch.LOGGER.info("Player {} received damage with source={}, entity={}, directEntitiy={}", player, source, source.getEntity(), source.getDirectEntity());
		DrinkAndStretch.combatTracker.onPlayerReceiveDamage(player, source);
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

	private static @NotNull ScheduledExecutorService buildExecutor() {
		return Executors.newSingleThreadScheduledExecutor(r -> {
			Thread t = new Thread(r);
			t.setName(DrinkAndStretch.MOD_ID + "-worker");
			t.setDaemon(true);
			t.setPriority(Thread.MIN_PRIORITY);
			return t;
		});
	}
}
