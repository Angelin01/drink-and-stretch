package com.angelin01.drinkandstretch.config;

import com.angelin01.drinkandstretch.DrinkAndStretch;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
@Config(name = DrinkAndStretch.MOD_ID)
public class DrinkAndStretchConfig implements ConfigData {
	@ConfigEntry.Gui.PrefixText
	boolean enableDrinkReminder = true;
	int drinkReminderInterval = 45;

	@ConfigEntry.Gui.PrefixText
	boolean enableStretchReminder = true;
	int stretchReminderInterval = 120;

	@ConfigEntry.Gui.PrefixText
	boolean enableInsults = false;
	@ConfigEntry.Gui.Tooltip
	boolean enableDeferInCombat = true;

	@Override
	public void validatePostLoad() {
		if (this.drinkReminderInterval <= 0) {
			this.drinkReminderInterval = 1;
		}
		if (this.stretchReminderInterval <= 0) {
			this.stretchReminderInterval = 1;
		}
	}

	public boolean isDrinkReminderEnabled() {
		return this.enableDrinkReminder;
	}

	public int getDrinkReminderInterval() {
		return this.drinkReminderInterval;
	}

	public boolean isStretchReminderEnabled() {
		return this.enableStretchReminder;
	}

	public int getStretchReminderInterval() {
		return this.stretchReminderInterval;
	}

	public boolean isInsultsEnabled() {
		return this.enableInsults;
	}

	public boolean isDeferInCombatEnabled() {
		return this.enableDeferInCombat;
	}
}
