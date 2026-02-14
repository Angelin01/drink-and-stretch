package com.angelin01.drinkandstretch.reminders;

import com.mojang.logging.LogUtils;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import org.slf4j.Logger;

import java.util.concurrent.ExecutorService;

public class CombatTracker {
	private static final Logger LOGGER = LogUtils.getLogger();
	private static final long COMBAT_DURATION_THRESHOLD_MILLIS = 30000L;

	private final ExecutorService executor;
	private volatile long lastCombatTimestamp = 0;

	public CombatTracker(ExecutorService executor) {
		this.executor = executor;
	}

	public void onPlayerReceiveDamage(LocalPlayer player, DamageSource source) {
		this.executor.submit(() -> this.handlePlayerReceiveDamage(player, source));
	}

	public void onPlayerAttack(LocalPlayer player, Entity entity) {
		this.executor.submit(() -> this.handlePlayerAttack(player, entity));
	}

	public boolean wasRecentlyInCombat() {
		final long timeSinceCombat = System.currentTimeMillis() - this.lastCombatTimestamp;
		return timeSinceCombat < CombatTracker.COMBAT_DURATION_THRESHOLD_MILLIS;
	}

	private void handlePlayerReceiveDamage(LocalPlayer player, DamageSource source) {
		if (CombatTracker.isNotSurvival(player)) {
			return;
		}

		// We unfortunately rarely have the source of the damage in the client side
		// Other times, we get spammed with one type and a few generics along the way
		// So this will have to do
		final Entity attacker = source.getEntity();
		if (attacker != null && player.is(attacker)) {
			return;
		}

		// For some reason, the client does know if it is a cactus!
		if (source.is(DamageTypes.CACTUS)) {
			return;
		}

		this.markPlayerInCombat();
	}

	private static boolean isNotSurvival(LocalPlayer player) {
		return player.isCreative() || player.isSpectator();
	}

	private void handlePlayerAttack(LocalPlayer player, Entity entity) {
		if (CombatTracker.isNotSurvival(player)) {
			return;
		}

		if (player.is(entity)) {
			return;
		}

		if (!(entity instanceof LivingEntity livingEntity)) {
			return;
		}

		if (this.isCombatTarget(livingEntity, player)) {
			CombatTracker.LOGGER.debug("Player attacked entity {}", entity);
			this.markPlayerInCombat();
		}
	}

	private boolean isCombatTarget(LivingEntity entity, LocalPlayer player) {
		if (entity instanceof Mob mob) {
			LivingEntity target = mob.getTarget();
			if (target != null && target.is(player)) {
				return true;
			}

			MobCategory category = mob.getType().getCategory();
			return category == MobCategory.MONSTER;
		}

		return true;
	}

	private void markPlayerInCombat() {
		this.lastCombatTimestamp = System.currentTimeMillis();
	}
}
