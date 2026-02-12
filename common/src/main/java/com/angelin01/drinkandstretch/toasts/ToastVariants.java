package com.angelin01.drinkandstretch.toasts;

import java.util.List;

public final class ToastVariants {

	public static final List<ToastText> DRINK =
		ToastVariants.range("drinkandstretch.toast.drink", 11);
	public static final List<ToastText> DRINK_INSULTS =
		ToastVariants.range("drinkandstretch.toast.drink_insult", 13);
	public static final List<ToastText> STRETCH =
		ToastVariants.range("drinkandstretch.toast.stretch", 11);
	public static final List<ToastText> STRETCH_INSULTS =
		ToastVariants.range("drinkandstretch.toast.stretch_insult", 13);

	private ToastVariants() {
	}

	private static List<ToastText> range(String baseKey, int count) {
		return java.util.stream.IntStream.range(1, count+1)
			.mapToObj(i -> new ToastText(
				baseKey + "." + i + ".title",
				baseKey + "." + i + ".message"
			))
			.toList();
	}
}
