package com.angelin01.drinkandstretch.fabric;

import com.angelin01.drinkandstretch.fabric.events.EventHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;

import com.angelin01.drinkandstretch.DrinkAndStretch;

public final class DrinkAndStretchFabric implements ModInitializer {

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		DrinkAndStretch.init();
		System.out.println("Hello from fabric!");

		new EventHandler().register();
	}
}
