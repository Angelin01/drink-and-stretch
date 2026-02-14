package com.angelin01.drinkandstretch.reminders;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

public final class ReminderScheduler implements AutoCloseable {
	private static final Logger LOGGER = LogUtils.getLogger();

	private final ScheduledExecutorService executor;
	private final List<ScheduledFuture<?>> reminders = new ArrayList<>();

	public ReminderScheduler(ScheduledExecutorService executor) {
		this.executor = executor;
	}

	public void schedule(Duration period, Duration deferDuration, Supplier<@NotNull Boolean> shouldDefer, Runnable task) {
		ReminderScheduler.LOGGER.info("Scheduling reminder: period={}min", period.toMinutes());
		this.scheduleNext(period, period, deferDuration, shouldDefer, task);
	}

	private void scheduleNext(Duration delay, Duration period, Duration deferDuration, Supplier<Boolean> shouldDefer, Runnable task) {
		final Runnable wrappedTask = this.wrapTask(period, deferDuration, shouldDefer, task);

		ScheduledFuture<?> future = this.executor.schedule(wrappedTask, delay.toSeconds(), TimeUnit.SECONDS);
		this.reminders.add(future);
	}

	private @NotNull Runnable wrapTask(Duration period, Duration deferDuration, Supplier<Boolean> shouldDefer, Runnable task) {
		return () -> {
			Duration nextDelay;

			if (shouldDefer.get()) {
				ReminderScheduler.LOGGER.info("Task deferred, rescheduling in {}s", deferDuration.toSeconds());
				nextDelay = deferDuration;
			} else {
				try {
					task.run();
				} catch (Exception e) {
					ReminderScheduler.LOGGER.error("Error executing reminder task", e);
				}
				nextDelay = period;
			}

			this.scheduleNext(nextDelay, period, deferDuration, shouldDefer, task);
		};
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
		this.executor.shutdownNow();
	}
}

