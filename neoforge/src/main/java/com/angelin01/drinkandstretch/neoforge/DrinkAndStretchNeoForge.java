package com.angelin01.drinkandstretch.neoforge;

import com.angelin01.drinkandstretch.DrinkAndStretch;
import com.angelin01.drinkandstretch.config.DrinkAndStretchConfig;
import com.angelin01.drinkandstretch.neoforge.events.EventHandler;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.minecraft.world.InteractionResult;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;

@Mod(DrinkAndStretch.MOD_ID)
public final class DrinkAndStretchNeoForge {
	public DrinkAndStretchNeoForge(IEventBus modBus) {
		DrinkAndStretch.init();
		System.out.println("Hello from neoforge!");

		// Register AutoConfig
		AutoConfig.register(DrinkAndStretchConfig.class, Toml4jConfigSerializer::new);

		// Get the config holder
		var configHolder = AutoConfig.getConfigHolder(DrinkAndStretchConfig.class);

		// Set initial config
		DrinkAndStretch.setConfig(configHolder.getConfig());

		// Register save listener
		configHolder.registerSaveListener((holder, config) -> {
			DrinkAndStretch.onConfigSave(config);
			return InteractionResult.SUCCESS;
		});

		ModLoadingContext.get().registerExtensionPoint(
			IConfigScreenFactory.class,
			() -> (client, parent) -> AutoConfig.getConfigScreen(DrinkAndStretchConfig.class, parent).get()
		);

		NeoForge.EVENT_BUS.register(new EventHandler());
	}
}
