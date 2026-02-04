package com.angelin01.drinkandstretch.neoforge;

import net.neoforged.fml.common.Mod;

import com.angelin01.drinkandstretch.DrinkAndStretch;

@Mod(DrinkAndStretch.MOD_ID)
public final class DrinkAndStretchNeoForge {
	public DrinkAndStretchNeoForge() {
		DrinkAndStretch.init();
		System.out.println("Hello from neoforge!");
	}
}
