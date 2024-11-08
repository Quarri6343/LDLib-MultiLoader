package com.lowdragmc.lowdraglib.questing;

import com.lowdragmc.lowdraglib.gui.compass.CompassNode;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.DEDICATED_SERVER)
public class QuestDatabase {
    public static final QuestDatabase INSTANCE = new QuestDatabase();

    public final List<Quest> quests = new ArrayList<>();

    public void registerQuest(CompassNode node) {
        if (node.getQuestObjectives() != null) {
            quests.add(new Quest(node));
        }
    }
}
