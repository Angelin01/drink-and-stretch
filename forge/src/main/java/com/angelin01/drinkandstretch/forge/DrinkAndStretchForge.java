package com.angelin01.drinkandstretch.forge;

import com.angelin01.drinkandstretch.DrinkAndStretch;
import com.angelin01.drinkandstretch.config.DrinkAndStretchConfig;
import com.angelin01.drinkandstretch.forge.events.EventHandler;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.world.InteractionResult;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;

@Mod(DrinkAndStretch.MOD_ID)
public final class DrinkAndStretchForge {
    public DrinkAndStretchForge() {
	    DrinkAndStretch.init();

	    DrinkAndStretchForge.setupConfig();

	    MinecraftForge.EVENT_BUS.register(new EventHandler());
    }

	@SuppressWarnings("removal")
	private static void setupConfig() {
		var configHolder = DrinkAndStretch.setupConfig();

		// We can't set this up inside common for some broken mapping reason,
		configHolder.registerSaveListener((holder, config) -> {
			DrinkAndStretch.onConfigSave(config);
			return InteractionResult.SUCCESS;
		});

		ModLoadingContext.get().registerExtensionPoint(
			ConfigScreenHandler.ConfigScreenFactory.class,
			() -> new ConfigScreenHandler.ConfigScreenFactory(
				(client, parent) -> AutoConfig.getConfigScreen(DrinkAndStretchConfig.class, parent).get()
			)
		);
	}
}
