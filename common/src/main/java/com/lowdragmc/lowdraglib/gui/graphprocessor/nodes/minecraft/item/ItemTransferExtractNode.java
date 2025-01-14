package com.lowdragmc.lowdraglib.gui.graphprocessor.nodes.minecraft.item;

import com.lowdragmc.lowdraglib.gui.editor.annotation.Configurable;
import com.lowdragmc.lowdraglib.gui.editor.annotation.LDLRegister;
import com.lowdragmc.lowdraglib.gui.editor.configurator.ConfiguratorGroup;
import com.lowdragmc.lowdraglib.gui.editor.runtime.ConfiguratorParser;
import com.lowdragmc.lowdraglib.gui.graphprocessor.annotation.InputPort;
import com.lowdragmc.lowdraglib.gui.graphprocessor.annotation.OutputPort;
import com.lowdragmc.lowdraglib.gui.graphprocessor.data.trigger.LinearTriggerNode;
import com.lowdragmc.lowdraglib.side.item.IItemTransfer;
import net.minecraft.world.item.ItemStack;

import java.lang.reflect.Method;
import java.util.HashMap;

@LDLRegister(name = "item extract", group = "graph_processor.node.minecraft.item")
public class ItemTransferExtractNode extends LinearTriggerNode {
    @InputPort(name = "item transfer")
    public IItemTransfer itemTransfer;
    @InputPort
    public Integer amount;
    @InputPort(name = "slot index")
    public Integer slot;
    @InputPort
    public Boolean simulate;
    @OutputPort
    public ItemStack extracted;
    @Configurable(name = "amount")
    public int internalAmount;
    @Configurable(name = "slot index")
    public int internalSlot;
    @Configurable(name = "simulate")
    public boolean internalSimulate;

    @Override
    public void process() {
        extracted = null;
        if (itemTransfer != null) {
            extracted = itemTransfer.extractItem(
                    slot == null ? internalSlot : slot,
                    amount == null ? internalAmount : amount,
                    simulate == null ? internalSimulate : simulate);
        }
    }

    @Override
    public void buildConfigurator(ConfiguratorGroup father) {
        var setter = new HashMap<String, Method>();
        var clazz = getClass();
        for (var port : getInputPorts()) {
            switch (port.fieldName) {
                case "amount" -> {
                    if (port.getEdges().isEmpty()) {
                        try {
                            ConfiguratorParser.createFieldConfigurator(clazz.getField("internalAmount"), father, clazz, setter, this);
                        } catch (NoSuchFieldException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                case "slot" -> {
                    if (port.getEdges().isEmpty()) {
                        try {
                            ConfiguratorParser.createFieldConfigurator(clazz.getField("internalSlot"), father, clazz, setter, this);
                        } catch (NoSuchFieldException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                case "simulate" -> {
                    if (port.getEdges().isEmpty()) {
                        try {
                            ConfiguratorParser.createFieldConfigurator(clazz.getField("internalSimulate"), father, clazz, setter, this);
                        } catch (NoSuchFieldException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
    }
}
