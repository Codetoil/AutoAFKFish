package io.github.codetoil.autoafkfisher.mixin;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static io.github.codetoil.autoafkfisher.AutoAfkFisher.*;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin
{
	@Shadow protected abstract void doItemUse();

	@Shadow private int itemUseCooldown;

	@Inject(at = @At("HEAD"), method = "tick")
	private void tick(CallbackInfo info)
	{
		lastPressedToggleKey--;
		if (lastPressedToggleKey < 0) {
			lastPressedToggleKey = 0;
		}
		if (isafkon && this.itemUseCooldown <= 0)
		{
			try {
				doItemUse();
			} catch (Throwable t)
			{
				//t.printStackTrace();
				isafkon = false;
			}

		}
	}

	@Inject(at = @At("HEAD"), method = "handleInputEvents")
	private void handleInputEvents(CallbackInfo info)
	{
		if (bind.isPressed() && lastPressedToggleKey <= 0) {
			System.out.println("User has pressed the toggle key, " + (isafkon ? "disabling" : "enabling") + " afk fishing.");
			isafkon = !isafkon;
			lastPressedToggleKey += 30;
		}
	}
}
