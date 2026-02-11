package com.angelin01.drinkandstretch.reminders;

import com.angelin01.drinkandstretch.config.DrinkAndStretchConfig;
import com.angelin01.drinkandstretch.toasts.DrinkAndStretchToast;
import com.angelin01.drinkandstretch.toasts.ToastDispatcher;
import com.angelin01.drinkandstretch.toasts.ToastVariants;

public class ReminderService {
	private final ReminderScheduler scheduler;
	private boolean running;

	public ReminderService(ReminderScheduler scheduler) {
		this.scheduler = scheduler;
	}

	public void startOrRestart(DrinkAndStretchConfig config) {
		this.scheduler.cancelAll();

		if (config.isDrinkReminderEnabled()) {
			this.scheduler.schedule(
				config.getDrinkReminderInterval(),
				ToastDispatcher.show(
					DrinkAndStretchToast.DrinkAndStretchToastId.DRINK,
					ToastVariants.DRINK
				)
			);
		}

		if (config.isStretchReminderEnabled()) {
			this.scheduler.schedule(
				config.getStretchReminderInterval(),
				ToastDispatcher.show(
					DrinkAndStretchToast.DrinkAndStretchToastId.STRETCH,
					ToastVariants.STRETCH
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
