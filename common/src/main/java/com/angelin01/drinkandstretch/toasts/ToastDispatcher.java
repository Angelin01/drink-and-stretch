package com.angelin01.drinkandstretch.toasts;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;

import java.util.List;
import java.util.Random;

@Environment(EnvType.CLIENT)
public class ToastDispatcher {
	private static final Random RANDOM = new Random();

	private ToastDispatcher() {
	}

	public static Runnable show(
		DrinkAndStretchToast.DrinkAndStretchToastId id,
		List<ToastText> messages
	) {
		return () -> {
			Minecraft mc = Minecraft.getInstance();
			ToastText msg = messages.get(ToastDispatcher.RANDOM.nextInt(messages.size()));

			mc.execute(() -> DrinkAndStretchToast.addOrUpdate(
				mc.getToasts(),
				id,
				msg.titleComponent(),
				msg.messageComponent()
			));
		};
	}
}
