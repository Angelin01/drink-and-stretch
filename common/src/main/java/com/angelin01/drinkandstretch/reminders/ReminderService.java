package com.angelin01.drinkandstretch.reminders;

import com.angelin01.drinkandstretch.config.DrinkAndStretchConfig;
import com.angelin01.drinkandstretch.toasts.DrinkAndStretchToast;
import com.angelin01.drinkandstretch.toasts.ToastDispatcher;
import com.angelin01.drinkandstretch.toasts.ToastText;
import com.angelin01.drinkandstretch.toasts.ToastVariants;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReminderService {
	private final ReminderScheduler scheduler;
	private boolean running;

	public ReminderService(ReminderScheduler scheduler) {
		this.scheduler = scheduler;
	}

	public void startOrRestart(DrinkAndStretchConfig config) {
		this.scheduler.cancelAll();

		if (config.isDrinkReminderEnabled()) {
			List<ToastText> drinkMessages = config.isInsultsEnabled()
				? Stream.concat(ToastVariants.DRINK.stream(), ToastVariants.DRINK_INSULTS.stream())
				.collect(Collectors.toList())
				: ToastVariants.DRINK;

			this.scheduler.schedule(
				config.getDrinkReminderInterval(),
				ToastDispatcher.show(
					DrinkAndStretchToast.DrinkAndStretchToastId.DRINK,
					drinkMessages
				)
			);
		}

		if (config.isStretchReminderEnabled()) {
			List<ToastText> stretchMessages = config.isInsultsEnabled()
				? Stream.concat(ToastVariants.STRETCH.stream(), ToastVariants.STRETCH_INSULTS.stream())
				.collect(Collectors.toList())
				: ToastVariants.STRETCH;

			this.scheduler.schedule(
				config.getStretchReminderInterval(),
				ToastDispatcher.show(
					DrinkAndStretchToast.DrinkAndStretchToastId.STRETCH,
					stretchMessages
				)
			);
		}

		this.running = true;
	}

	public void stop() {
		this.scheduler.cancelAll();
		this.running = false;
	}

	public void onConfigReload(DrinkAndStretchConfig config) {
		if (this.running) {
			this.startOrRestart(config);
		}
	}
}
