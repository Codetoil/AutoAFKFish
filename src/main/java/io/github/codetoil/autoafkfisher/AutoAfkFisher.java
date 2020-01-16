package io.github.codetoil.autoafkfisher;

import net.fabricmc.api.ModInitializer;

public class AutoAfkFisher implements ModInitializer
{
	public static boolean isafkon = false;
	public static int lastPressedF12 = 0;
	public static final String VERSION = "{mod=(1.0.0.1), mappings=(1.14.4+build.9), loader=(0.4.8+build.159), fabric=(0.3.1+build.208)}";

	@Override
	public void onInitialize()
	{
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		System.out.println("Initializing AutoAFK Fisher!");
	}
}
