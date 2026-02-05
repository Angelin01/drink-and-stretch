package com.angelin01.drinkandstretch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

public final class ReminderScheduler implements AutoCloseable {
	private static final Logger LOGGER = LogUtils.getLogger();

	private final ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor();
	private final List<ScheduledFuture<?>> reminders = new ArrayList<>();

	public void schedule(int periodMin, Runnable task) {
		ReminderScheduler.LOGGER.info(
			"Scheduling reminder: task={}, period={}min",
			task,
			periodMin
		);

		// TODO: change TimeUnit.Seconds to Minutes later
		final ScheduledFuture<?> future = this.timer.scheduleAtFixedRate(task, periodMin, periodMin, TimeUnit.SECONDS);

		this.reminders.add(future);
	}

	public void cancelAll() {
		ReminderScheduler.LOGGER.info("Canceling all reminders");

		for (ScheduledFuture<?> future : this.reminders) {
			future.cancel(true);
		}

		this.reminders.clear();
	}

	@Override
	public void close() {
		this.cancelAll();
		this.timer.shutdownNow();
	}
}

