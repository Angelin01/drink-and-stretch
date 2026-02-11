package com.angelin01.drinkandstretch.neoforge;

import com.angelin01.drinkandstretch.DrinkAndStretch;
import com.angelin01.drinkandstretch.config.DrinkAndStretchConfig;
import com.angelin01.drinkandstretch.neoforge.events.EventHandler;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.world.InteractionResult;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;

@Mod(DrinkAndStretch.MOD_ID)
public final class DrinkAndStretchNeoForge {
	public DrinkAndStretchNeoForge() {
		DrinkAndStretch.init();

		DrinkAndStretchNeoForge.setupConfig();

		NeoForge.EVENT_BUS.register(new EventHandler());
	}

	private static void setupConfig() {
		var configHolder = DrinkAndStretch.setupConfig();

		// We can't set this up inside common for some broken mapping reason,
		configHolder.registerSaveListener((holder, config) -> {
			DrinkAndStretch.onConfigSave(config);
			return InteractionResult.SUCCESS;
		});

		ModLoadingContext.get().registerExtensionPoint(
			IConfigScreenFactory.class,
			() -> (client, parent) -> AutoConfig.getConfigScreen(DrinkAndStretchConfig.class, parent).get()
		);
	}
}
