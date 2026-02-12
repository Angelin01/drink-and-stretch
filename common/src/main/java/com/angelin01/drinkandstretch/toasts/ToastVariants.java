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
	public static final List<ToastText> DRINK_INSULTS = List.of(
		new ToastText("Still no water?", "I've seen cacti more hydrated than you"),
		new ToastText("Drink water, genius", "Your brain is 73% water, not dirt"),
		new ToastText("Hydration status: pathetic", "Even the Nether mobs are concerned"),
		new ToastText("Water. Now.", "Or keep your raisin cosplay, your choice"),
		new ToastText("Is your mouth a desert?", "Because it should be an ocean"),
		new ToastText("Congratulations", "You've achieved mummy-level dehydration"),
		new ToastText("Your cells called", "They want their water back"),
		new ToastText("Drink something", "Coffee doesn't count, try again"),
		new ToastText("Achievement unlocked", "Drought Simulator Expert"),
		new ToastText("Water is free", "Unlike the brain cells you're losing"),
		new ToastText("Fun fact: dehydration makes you dumber", "Explains a lot"),
		new ToastText("DRINK SOMETHING!", "NOW! NOOOOOOOOOOOW!"),
		new ToastText("You either drink some water...", "Or /kill @e[type=minecraft:wolf]")
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
	public static final List<ToastText> STRETCH_INSULTS = List.of(
		new ToastText("You good down there?", "Or has rigor mortis set in?"),
		new ToastText("Stretch, statue", "Pigeons are about to roost on you"),
		new ToastText("Move those limbs", "They're supposed to bend, not fossilize"),
		new ToastText("Stand up challenge", "Difficulty: your spine"),
		new ToastText("Posture check failed", "Shrimp mode: still engaged"),
		new ToastText("Are you furniture now?", "Because you haven't moved in hours"),
		new ToastText("Stretch break", "Before you become chair-shaped"),
		new ToastText("Your back hates you", "And it's about to file a complaint"),
		new ToastText("Motion detected: none", "Are you speedrunning scoliosis?"),
		new ToastText("Stand up", "Or join the the decorative block list"),
		new ToastText("Achievement unlocked", "Human Pretzel (not the good kind)"),
		new ToastText("Let your chair breathe!", "The smell is suffocating it"),
		new ToastText("Something is going to run: you,", "or /kill @e[type=minecraft:cat]")
	);

	private ToastVariants() {
	}
}
