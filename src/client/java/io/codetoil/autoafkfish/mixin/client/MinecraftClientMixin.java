/**
 * A mod that lets you AFK Fish in the background when used with an auto
 * fishing setup. Default Keybind is F12.
 * Copyright (C) 2020-2025 Anthony Michalek/Angelina Michalek (Codetoil)
 * <br>
 * This file is part of AutoAFKFish
 * <br>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This file is additionally subject
 * to the GPL Exception for Minecraft listed at the end of the given License file.
 * <br>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <br>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 * Additionally, a copy of the GPL Exception for Minecraft can be found here:
 * <https://gist.github.com/triphora/588f353802a3b0ea649e4fc85f75e583>
 */

package io.codetoil.autoafkfish.mixin.client;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static io.codetoil.autoafkfish.AutoAFKFish.*;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
    @Invoker("doItemUse")
    protected abstract void invokeDoItemUse();

    @Accessor("itemUseCooldown")
    protected abstract int getItemUseCooldown();

    @Inject(at = @At("HEAD"), method = "tick")
    private void tick(CallbackInfo info) {
        lastPressedToggleKey--;
        if (lastPressedToggleKey < 0) {
            lastPressedToggleKey = 0;
        }
        if (isAFKOn && this.getItemUseCooldown() <= 0) {
            try {
                invokeDoItemUse();
            } catch (Throwable t) {
                isAFKOn = false;
            }

        }
    }

    @Inject(at = @At("HEAD"), method = "handleInputEvents")
    private void handleInputEvents(CallbackInfo info) {
        if (bind.isPressed() && lastPressedToggleKey <= 0) {
            System.out.println("User has pressed the toggle key, " + (isAFKOn ? "disabling" : "enabling") + " afk fishing.");
            isAFKOn = !isAFKOn;
            lastPressedToggleKey += 30;
        }
    }
}
