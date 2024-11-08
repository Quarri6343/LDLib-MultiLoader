package com.lowdragmc.lowdraglib.questing;

import com.lowdragmc.lowdraglib.gui.compass.CompassNode;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.List;

@OnlyIn(Dist.DEDICATED_SERVER)
public class Quest {

    private final CompassNode baseNode;
    @Getter
    private final List<ItemStack> objectives;

    @Getter
    @Setter
    private Progress progress;

    public Quest(CompassNode node) {
        baseNode = node;
        progress = Progress.NOT_COMPLETED;
        objectives = node.getQuestObjectives();
    }

    public void markAsComplete(ServerPlayer player) {
        setProgress(Quest.Progress.COMPLETED);
        //TODO: refresh a page from server side
    }

    //TODO:more states like "not_unlocked"
    public enum Progress {
        NOT_COMPLETED,
        COMPLETED,
    }
}
