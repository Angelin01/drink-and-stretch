package com.angelin01.drinkandstretch.neoforge.events;

import com.angelin01.drinkandstretch.DrinkAndStretch;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;

public class EventHandler {
	@SubscribeEvent
	public void onClientLogin(ClientPlayerNetworkEvent.LoggingIn event) {
		DrinkAndStretch.startPeriodicReminders();
	}

	@SubscribeEvent
	public void onClientLogout(ClientPlayerNetworkEvent.LoggingOut event) {
		DrinkAndStretch.stopPeriodicReminders();
	}
}
