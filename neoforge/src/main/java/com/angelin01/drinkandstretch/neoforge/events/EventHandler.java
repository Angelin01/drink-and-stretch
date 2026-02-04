package com.angelin01.drinkandstretch.neoforge.events;

import com.angelin01.drinkandstretch.DrinkAndStretch;

import net.minecraft.client.player.LocalPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

public class EventHandler {
	@SubscribeEvent
	public void onEntityJoinLevel(EntityJoinLevelEvent event) {
		if ((event.getEntity() instanceof LocalPlayer)) {
			DrinkAndStretch.showToast();
		}
	}
}
