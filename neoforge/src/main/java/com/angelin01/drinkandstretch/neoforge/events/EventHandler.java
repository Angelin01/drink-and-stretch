package com.angelin01.drinkandstretch.neoforge.events;

import com.angelin01.drinkandstretch.DrinkAndStretch;
import net.minecraft.client.player.LocalPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;

public class EventHandler {
	@SubscribeEvent
	public void onClientLogin(ClientPlayerNetworkEvent.LoggingIn event) {
		DrinkAndStretch.startPeriodicReminders();
	}

	@SubscribeEvent
	public void onClientLogout(ClientPlayerNetworkEvent.LoggingOut event) {
		DrinkAndStretch.stopPeriodicReminders();
	}

	@SubscribeEvent
	public void onPlayerAttack(AttackEntityEvent event) {
		if (event.getEntity() instanceof LocalPlayer player) {
			DrinkAndStretch.onPlayerAttack(player, event.getTarget());
		}
	}
}
