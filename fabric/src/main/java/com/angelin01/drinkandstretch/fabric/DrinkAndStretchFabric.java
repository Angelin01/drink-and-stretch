package com.angelin01.drinkandstretch.fabric;

import com.angelin01.drinkandstretch.config.DrinkAndStretchConfig;
import com.angelin01.drinkandstretch.fabric.events.EventHandler;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;

import com.angelin01.drinkandstretch.DrinkAndStretch;
import net.minecraft.world.InteractionResult;

public final class DrinkAndStretchFabric implements ModInitializer {

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		DrinkAndStretch.init();
		System.out.println("Hello from fabric!");

		AutoConfig.register(DrinkAndStretchConfig.class, Toml4jConfigSerializer::new);

		var configHolder = AutoConfig.getConfigHolder(DrinkAndStretchConfig.class);

		DrinkAndStretch.setConfig(configHolder.getConfig());

		configHolder.registerSaveListener((holder, config) -> {
			DrinkAndStretch.onConfigSave(config);
			return InteractionResult.SUCCESS;
		});

		new EventHandler().register();
	}
}
