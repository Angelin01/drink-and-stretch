package com.angelin01.drinkandstretch.forge.events;

import com.angelin01.drinkandstretch.DrinkAndStretch;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;

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
