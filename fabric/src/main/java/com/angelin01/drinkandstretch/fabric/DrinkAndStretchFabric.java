package com.angelin01.drinkandstretch.fabric;

import com.angelin01.drinkandstretch.DrinkAndStretch;
import com.angelin01.drinkandstretch.config.DrinkAndStretchConfig;
import com.angelin01.drinkandstretch.fabric.events.EventHandler;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.world.InteractionResult;
import org.jetbrains.annotations.NotNull;

public final class DrinkAndStretchFabric implements ModInitializer {

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		DrinkAndStretch.init();

		DrinkAndStretchFabric.setupConfig();

		new EventHandler().register();
	}

	private static void setupConfig() {
		var configHolder = DrinkAndStretch.setupConfig();
		configHolder.registerSaveListener((holder, config) -> {
			DrinkAndStretch.onConfigSave(config);
			return InteractionResult.SUCCESS;
		});
	}
}
