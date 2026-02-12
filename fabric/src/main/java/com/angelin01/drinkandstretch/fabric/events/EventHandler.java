package com.angelin01.drinkandstretch.fabric.events;

import com.angelin01.drinkandstretch.DrinkAndStretch;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.Nullable;

public final class EventHandler {
	public void register() {
		ClientPlayConnectionEvents.JOIN.register(this::onClientJoin);
		ClientPlayConnectionEvents.DISCONNECT.register(this::onClientDisconnect);
		AttackEntityCallback.EVENT.register(this::onPlayerAttack);
	}

	private void onClientJoin(ClientPacketListener clientPacketListener, PacketSender packetSender, Minecraft minecraft) {
		DrinkAndStretch.startPeriodicReminders();
	}

	private void onClientDisconnect(ClientPacketListener clientPacketListener, Minecraft minecraft) {
		DrinkAndStretch.stopPeriodicReminders();
	}

	private InteractionResult onPlayerAttack(Player player, Level level, InteractionHand interactionHand, Entity entity, @Nullable EntityHitResult _entityHitResult) {
		if (player instanceof LocalPlayer localPlayer) {
			DrinkAndStretch.onPlayerAttack(localPlayer, entity);
		}
		return InteractionResult.PASS;
	}
}
