package com.lowdragmc.lowdraglib;

import com.lowdragmc.lowdraglib.async.AsyncThreadData;
import com.lowdragmc.lowdraglib.questing.Quest;
import com.lowdragmc.lowdraglib.questing.QuestDatabase;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.LevelEvent;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;
import net.neoforged.neoforge.event.server.ServerStoppedEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

import java.util.List;

/**
 * @author KilaBash
 * @date 2022/11/27
 * @implNote CommonListeners
 */
@EventBusSubscriber(modid = LDLib.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class CommonListeners {

    @SubscribeEvent
    public static void onWorldUnLoad(LevelEvent.Unload event) {
        LevelAccessor world = event.getLevel();
        if (!world.isClientSide() && world instanceof ServerLevel serverLevel) {
            AsyncThreadData.getOrCreate(serverLevel).releaseExecutorService();
        }
    }

    @SubscribeEvent
    public static void onServerAboutToStart(ServerAboutToStartEvent event) {
        Platform.FROZEN_REGISTRY_ACCESS = event.getServer().registryAccess();
    }

    @SubscribeEvent
    public static void onServerStopped(ServerStoppedEvent event) {
        Platform.FROZEN_REGISTRY_ACCESS = null;
    }

    @SubscribeEvent
    public static void onLivingUpdate(EntityTickEvent event) {
        if(event.getEntity() instanceof ServerPlayer player && event.getEntity().tickCount % 20 == 0) {
            List<Quest> questsToCheck = QuestDatabase.INSTANCE.quests.stream().filter(quest -> quest.getProgress() == Quest.Progress.NOT_COMPLETED).toList();

            for (Quest questToCheck : questsToCheck) {
                for (ItemStack objective : questToCheck.getObjectives()) {
                    if(player.getInventory().contains(objective)) {
                        questToCheck.markAsComplete(player);
                        break;
                    }
                }
            }
        }
    }
}
