package com.angelin01.drinkandstretch.fabric;

import com.angelin01.drinkandstretch.config.DrinkAndStretchConfig;
import com.angelin01.drinkandstretch.fabric.events.EventHandler;
import fuzs.forgeconfigapiport.fabric.api.forge.v4.ForgeConfigRegistry;
import net.fabricmc.api.ModInitializer;

import com.angelin01.drinkandstretch.DrinkAndStretch;
import net.neoforged.fml.config.ModConfig;

public final class DrinkAndStretchFabric implements ModInitializer {

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		ForgeConfigRegistry.INSTANCE.register(DrinkAndStretch.MOD_ID, ModConfig.Type.CLIENT, DrinkAndStretchConfig.CLIENT_SPEC);

		DrinkAndStretch.init();

		new EventHandler().register();
	}
}
