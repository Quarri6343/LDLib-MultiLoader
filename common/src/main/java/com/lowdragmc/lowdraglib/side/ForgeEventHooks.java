package com.lowdragmc.lowdraglib.side;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.PlatformOnly;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;

import static dev.architectury.injectables.annotations.PlatformOnly.FORGE;

/**
 * @author KilaBash
 * @date 2023/2/11
 * @implNote ForgeEventHooks
 */
public class ForgeEventHooks {
    @ExpectPlatform
    @PlatformOnly(FORGE)
    @Environment(EnvType.CLIENT)
    public static void postPlayerContainerEvent(Player player, AbstractContainerMenu container) {
        throw new AssertionError();
    }

    @ExpectPlatform
    @PlatformOnly(FORGE)
    public static void postBackgroundRenderedEvent(Screen screen, GuiGraphics poseStack) {
        throw new AssertionError();
    }

    @ExpectPlatform
    @PlatformOnly(FORGE)
    @Environment(EnvType.CLIENT)
    public static void postRenderBackgroundEvent(AbstractContainerScreen<?> guiContainer, GuiGraphics poseStack, int mouseX, int mouseY) {
        throw new AssertionError();
    }

    @ExpectPlatform
    @PlatformOnly(FORGE)
    @Environment(EnvType.CLIENT)
    public static void postRenderForegroundEvent(AbstractContainerScreen<?> guiContainer, GuiGraphics poseStack, int mouseX, int mouseY) {
        throw new AssertionError();
    }
}
