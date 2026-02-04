package com.angelin01.drinkandstretch.neoforge;

import com.angelin01.drinkandstretch.DrinkAndStretch;
import com.angelin01.drinkandstretch.neoforge.events.EventHandler;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;

@Mod(DrinkAndStretch.MOD_ID)
public final class DrinkAndStretchNeoForge {
	public DrinkAndStretchNeoForge(IEventBus modBus) {
		DrinkAndStretch.init();
		System.out.println("Hello from neoforge!");

		NeoForge.EVENT_BUS.register(new EventHandler());
	}
}
