package com.angelin01.drinkandstretch.toasts;

import java.util.List;

public final class ToastVariants {

	public static final List<ToastText> DRINK = List.of(
		new ToastText("Drink water", "Do it now!"),
		new ToastText("Hydration check", "Your kidneys will thank you"),
		new ToastText("Ingest some H2O", "It probably won't explode"),
		new ToastText("Hydrate yourself", "Brains need water too"),
		new ToastText("Water break", "Dehydration slows focus"),
		new ToastText("Drink water now", "IRL, not in game"),
		new ToastText("IRL clear potion", "Restores stamina"),
		new ToastText("Clear fluids time", "Human bodies are mostly water!"),
		new ToastText("The water is not a lie", "At least, I don't think it is"),
		new ToastText("Water fun fact", "Astronauts recycle their water"),
		new ToastText("Why did the water cross the road?", "Redstone accident")
	);
	public static final List<ToastText> STRETCH = List.of(
		new ToastText("Stretch", "You are not a shrimp"),
		new ToastText("Stretch time", "Stand up, please?"),
		new ToastText("Get up and move!", "It's healthy in small doses"),
		new ToastText("Stretch break", "Joints like movement"),
		new ToastText("Time to stretch", "Muscles hate being idle"),
		new ToastText("Stand up IRL", "Just a lil' bit"),
		new ToastText("Loosen those joints", "Blood flow matters"),
		new ToastText("Uncrouch yourself", "You don't need to sneak... Do you?"),
		new ToastText("Walk around!", "Maybe go get more water"),
		new ToastText("Stretch fun fact", "You are bendier when warm"),
		new ToastText("Stretch warning", "Crunchy noises are mostly normal")
	);

	private ToastVariants() {
	}
}
