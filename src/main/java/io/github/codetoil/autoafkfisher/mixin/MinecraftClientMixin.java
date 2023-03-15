package io.github.codetoil.autoafkfisher.mixin;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static io.github.codetoil.autoafkfisher.AutoAfkFisher.*;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin
{
	@Invoker("doItemUse") protected abstract void invokeDoItemUse();

    @Accessor("itemUseCooldown") protected abstract int getItemUseCooldown();
	
	@Inject(at = @At("HEAD"), method = "tick")
	private void tick(CallbackInfo info)
	{
		lastPressedToggleKey--;
		if (lastPressedToggleKey < 0) {
			lastPressedToggleKey = 0;
		}
		if (isafkon && this.getItemUseCooldown() <= 0)
		{
			try {
				invokeDoItemUse();
			} catch (Throwable t)
			{
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
