package com.lowdragmc.lowdraglib.networking.c2s;

import com.lowdragmc.lowdraglib.LDLib;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.NoArgsConstructor;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;

@NoArgsConstructor
public class CPacketCompassReload implements CustomPacketPayload {
    public static final ResourceLocation ID = LDLib.location("compass_reload");
    public static final CustomPacketPayload.Type<CPacketCompassReload> TYPE = new CustomPacketPayload.Type<>(ID);
    public static final StreamCodec<RegistryFriendlyByteBuf, CPacketCompassReload> CODEC = StreamCodec.ofMember(CPacketCompassReload::write, CPacketCompassReload::decode);
    public boolean doReload;
    public RegistryFriendlyByteBuf updateData;

    public CPacketCompassReload(boolean doReload, RegistryFriendlyByteBuf updateData) {
        this.doReload = doReload;
        this.updateData = updateData;
    }

    public void write(@NotNull RegistryFriendlyByteBuf buf) {
        buf.writeVarInt(updateData.readableBytes());
        buf.writeBytes(updateData);

        buf.writeBoolean(doReload);

        updateData.readerIndex(0);
    }

    public static CPacketCompassReload decode(RegistryFriendlyByteBuf buf) {
        ByteBuf directSliceBuffer = buf.readBytes(buf.readVarInt());
        ByteBuf copiedDataBuffer = Unpooled.copiedBuffer(directSliceBuffer);
        directSliceBuffer.release();
        RegistryFriendlyByteBuf updateData = new RegistryFriendlyByteBuf(copiedDataBuffer, buf.registryAccess());

        boolean doReload = buf.readBoolean();
        return new CPacketCompassReload(doReload, updateData);
    }

    public static void execute(CPacketCompassReload packet, IPayloadContext context) {
        //reload server compass manager
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
