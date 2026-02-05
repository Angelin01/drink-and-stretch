package com.angelin01.drinkandstretch.fabric.events;

import com.angelin01.drinkandstretch.DrinkAndStretch;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;

public final class EventHandler {
	public void register() {
		ClientPlayConnectionEvents.JOIN.register(this::onClientJoin);
		ClientPlayConnectionEvents.DISCONNECT.register(this::onClientDisconnect);
	}

	private void onClientJoin(ClientPacketListener clientPacketListener, PacketSender packetSender, Minecraft minecraft) {
		DrinkAndStretch.startPeriodicReminders();
	}

	private void onClientDisconnect(ClientPacketListener clientPacketListener, Minecraft minecraft) {
		DrinkAndStretch.stopPeriodicReminders();
	}
}
