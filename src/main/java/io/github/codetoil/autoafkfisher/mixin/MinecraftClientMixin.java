package io.github.codetoil.autoafkfisher.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.util.InputUtil;
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

	@Shadow public ClientPlayerEntity player;

	@Inject(at = @At("HEAD"), method = "tick")
	private void tick(CallbackInfo info)
	{
		lastPressedF12--;
		if (lastPressedF12 < 0) {
			lastPressedF12 = 0;
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
		if (InputUtil.isKeyPressed(MinecraftClient.getInstance().window.getHandle(), InputUtil.fromName("key.keyboard.f12").getKeyCode()) && lastPressedF12 <= 0) {
			System.out.println("Pressed F12");
			isafkon = !isafkon;
			lastPressedF12 += 60;
		}
	}
}
