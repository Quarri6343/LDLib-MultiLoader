package com.lowdragmc.lowdraglib.gui.graphprocessor.nodes.minecraft.item;

import com.lowdragmc.lowdraglib.gui.editor.annotation.LDLRegister;
import com.lowdragmc.lowdraglib.gui.graphprocessor.annotation.InputPort;
import com.lowdragmc.lowdraglib.gui.graphprocessor.annotation.OutputPort;
import com.lowdragmc.lowdraglib.gui.graphprocessor.data.BaseNode;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

@LDLRegister(name = "itemstack info", group = "graph_processor.node.minecraft.item")
public class ItemStackInfoNode extends BaseNode {
    @InputPort
    public ItemStack in = null;
    @OutputPort
    public Item out = null;
    @OutputPort
    public int count = 0;
    @OutputPort
    public CompoundTag nbt;

    @Override
    public void process() {
        out = null;
        count = 0;
        nbt = null;
        if (in != null) {
            out = in.getItem();
            count = in.getCount();
            nbt = in.getTag();
        }
    }

}
