package com.lowdragmc.lowdraglib.questing;

import com.lowdragmc.lowdraglib.gui.compass.CompassNode;
import com.lowdragmc.lowdraglib.gui.compass.CompassSection;
import net.minecraft.resources.ResourceLocation;
import org.w3c.dom.Document;

import java.util.HashMap;
import java.util.Map;

public class ServerCompassManager {
    public final static ServerCompassManager INSTANCE = new ServerCompassManager();

    public final Map<String, Map<ResourceLocation, CompassSection>> sections = new HashMap<>();
    public final Map<String, Map<ResourceLocation, CompassNode>> nodes = new HashMap<>();
    public final Map<ResourceLocation, Map<String, Document>> nodePages = new HashMap<>();

    private ServerCompassManager() {

    }

    //TODO: recieve reload packet from client
}
