package com.lowdragmc.lowdraglib.networking;

import net.minecraft.network.RegistryFriendlyByteBuf;

public interface IPacket {

    void encode(RegistryFriendlyByteBuf buf);

    void decode(RegistryFriendlyByteBuf buf);

    default void execute(IHandlerContext handler) {
        
    }

}