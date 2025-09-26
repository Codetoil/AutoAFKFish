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

package io.codetoil.autoafkfish;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

import java.util.Optional;

public class AutoAFKFish implements ClientModInitializer {
    public static boolean isAFKOn = false;
    public static int lastPressedToggleKey = 0;
    public static KeyBinding bind;

    @Override
    public void onInitializeClient() {
        Optional<ModContainer> container = FabricLoader.getInstance().getModContainer("autoafkfish");
        if (container.isPresent()) {
            System.out.println("Initializing AutoAFKFish v" + container.get().getMetadata().getVersion() + "!");
        } else {
            System.out.println("Initializing AutoAFKFish of unknown version!");
        }
        bind = KeyBindingHelper.registerKeyBinding(new KeyBinding("autoafkfish:toggle_fishing",
                InputUtil.Type.KEYSYM, InputUtil.fromTranslationKey("key.keyboard.f12").getCode(),
                KeyBinding.Category.MISC));
    }
}
