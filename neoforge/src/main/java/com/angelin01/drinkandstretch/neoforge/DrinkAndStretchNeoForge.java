package com.angelin01.drinkandstretch.neoforge;

import com.angelin01.drinkandstretch.DrinkAndStretch;
import com.angelin01.drinkandstretch.config.DrinkAndStretchConfig;
import com.angelin01.drinkandstretch.neoforge.events.EventHandler;

import fuzs.forgeconfigapiport.neoforge.api.forge.v4.ForgeConfigRegistry;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;

@Mod(DrinkAndStretch.MOD_ID)
public final class DrinkAndStretchNeoForge {
	public DrinkAndStretchNeoForge() {
		ForgeConfigRegistry.INSTANCE.register(DrinkAndStretch.MOD_ID, ModConfig.Type.CLIENT, DrinkAndStretchConfig.CLIENT_SPEC);

		DrinkAndStretch.init();

		NeoForge.EVENT_BUS.register(new EventHandler());
	}
}
