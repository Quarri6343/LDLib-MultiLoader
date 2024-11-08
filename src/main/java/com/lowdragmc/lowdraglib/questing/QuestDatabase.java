package com.lowdragmc.lowdraglib.questing;

import com.lowdragmc.lowdraglib.gui.compass.CompassNode;

import java.util.ArrayList;
import java.util.List;

public class QuestDatabase {
    public static final QuestDatabase INSTANCE = new QuestDatabase();

    public final List<Quest> quests = new ArrayList<>();

    public void registerQuest(CompassNode node) {
        if (node.getQuestObjectives() != null) {
            quests.add(new Quest(node));
        }
    }
}
