package com.angelin01.drinkandstretch.reminders;

import com.angelin01.drinkandstretch.config.DrinkAndStretchConfig;
import com.angelin01.drinkandstretch.toasts.DrinkAndStretchToast;
import com.angelin01.drinkandstretch.toasts.ToastDispatcher;
import com.angelin01.drinkandstretch.toasts.ToastText;
import com.angelin01.drinkandstretch.toasts.ToastVariants;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReminderService {
	private static final Duration DEFER_DURATION = Duration.ofSeconds(15);

	private final ReminderScheduler scheduler;
	private final CombatTracker combatTracker;
	private boolean running;

	public ReminderService(ReminderScheduler scheduler, CombatTracker combatTracker) {
		this.scheduler = scheduler;
		this.combatTracker = combatTracker;
	}

	public void startOrRestart(DrinkAndStretchConfig config) {
		this.scheduler.cancelAll();

		if (config.isDrinkReminderEnabled()) {
			this.scheduleReminder(
				DrinkAndStretchToast.DrinkAndStretchToastId.DRINK,
				config.getDrinkReminderInterval(),
				ToastVariants.DRINK,
				ToastVariants.DRINK_INSULTS,
				config.isInsultsEnabled()
			);
		}

		if (config.isStretchReminderEnabled()) {
			this.scheduleReminder(
				DrinkAndStretchToast.DrinkAndStretchToastId.STRETCH,
				config.getStretchReminderInterval(),
				ToastVariants.STRETCH,
				ToastVariants.STRETCH_INSULTS,
				config.isInsultsEnabled()
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

	private void scheduleReminder(
		DrinkAndStretchToast.DrinkAndStretchToastId toastId,
		int intervalMinutes,
		List<ToastText> baseMessages,
		List<ToastText> insultMessages,
		boolean includeInsults
	) {
		List<ToastText> messages = includeInsults
			? Stream.concat(baseMessages.stream(), insultMessages.stream())
			.collect(Collectors.toList())
			: baseMessages;

		// TODO: change ofSeconds to ofMinutes
		this.scheduler.schedule(
			Duration.ofSeconds(intervalMinutes),
			ReminderService.DEFER_DURATION,
			this.combatTracker::wasRecentlyInCombat,
			ToastDispatcher.show(toastId, messages)
		);
	}
}
