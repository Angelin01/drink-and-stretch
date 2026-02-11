package com.angelin01.drinkandstretch.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class DrinkAndStretchConfig {
	public static final ForgeConfigSpec CLIENT_SPEC;
	public static final Client CLIENT;

	static {
		ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
		CLIENT = new Client(builder);
		CLIENT_SPEC = builder.build();
	}

	public static class Client {
		public final ForgeConfigSpec.BooleanValue enableDrinkReminder;
		public final ForgeConfigSpec.IntValue drinkReminderInterval;
		public final ForgeConfigSpec.BooleanValue enableStretchReminder;
		public final ForgeConfigSpec.IntValue stretchReminderInterval;

		public Client(ForgeConfigSpec.Builder builder) {
			builder.comment("Drink and Stretch Configuration")
				.push("reminders");

			this.enableDrinkReminder = builder
				.comment("Enable periodic drink reminders")
				.define("enableDrinkReminder", true);

			this.drinkReminderInterval = builder
				.comment("Interval between drink reminders in minutes")
				.defineInRange("drinkReminderInterval", 45, 1, 24*60);

			this.enableStretchReminder = builder
				.comment("Enable periodic stretch reminders")
				.define("enableStretchReminder", true);

			this.stretchReminderInterval = builder
				.comment("Interval between stretch reminders in minutes")
				.defineInRange("stretchReminderInterval", 2*60, 1, 24*60);

			builder.pop();
		}
	}
}
