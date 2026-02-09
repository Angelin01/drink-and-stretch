package com.angelin01.drinkandstretch.toasts;

import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;

public record ToastText(
	String title,
	@Nullable String message
) {
	public Component titleComponent() {
		return Component.literal(this.title);
	}

	public Component messageComponent() {
		return this.message == null ? Component.empty() : Component.literal(this.message);
	}
}
