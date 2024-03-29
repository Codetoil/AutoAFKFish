package io.github.codetoil.autoafkfisher;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

import java.util.Optional;

public class AutoAfkFisher implements ModInitializer
{
	public static boolean isafkon = false;
	public static int lastPressedToggleKey = 0;
    public static KeyBinding bind;

	@Override
	public void onInitialize()
	{
		Optional<ModContainer> container = FabricLoader.getInstance().getModContainer("autoafkfisher");
		if (container.isPresent())
		{
			System.out.println("Initializing AutoAFKFisher v"+container.get().getMetadata().getVersion()+"!");
		}
		else
		{
			System.out.println("Initializing AutoAFKFisher of unknown version!");
		}
		bind = KeyBindingHelper.registerKeyBinding(new KeyBinding("autoafkfish:togglefishing", InputUtil.Type.KEYSYM, InputUtil.fromTranslationKey("key.keyboard.f12").getCode(), "key.categories.misc"));
	}
}
