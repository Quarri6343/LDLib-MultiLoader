package com.lowdragmc.lowdraglib.networking;

import com.lowdragmc.lowdraglib.LDLib;
import com.lowdragmc.lowdraglib.networking.both.PacketRPCMethodPayload;
import com.lowdragmc.lowdraglib.networking.c2s.CPacketCompassReload;
import com.lowdragmc.lowdraglib.networking.c2s.CPacketUIClientAction;
import com.lowdragmc.lowdraglib.networking.s2c.SPacketManagedPayload;
import com.lowdragmc.lowdraglib.networking.s2c.SPacketUIOpen;
import com.lowdragmc.lowdraglib.networking.s2c.SPacketUIWidgetUpdate;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

/**
 * Author: KilaBash
 * Date: 2022/04/27
 * Description:
 */
public class LDLNetworking {

    public static void registerPayloads(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registar = event.registrar(LDLib.MOD_ID);

        registar.playToClient(SPacketUIOpen.TYPE, SPacketUIOpen.CODEC, SPacketUIOpen::execute);
        registar.playToClient(SPacketUIWidgetUpdate.TYPE, SPacketUIWidgetUpdate.CODEC, SPacketUIWidgetUpdate::execute);
        registar.playToClient(SPacketManagedPayload.TYPE, SPacketManagedPayload.CODEC, SPacketManagedPayload::execute);

        registar.playToServer(CPacketUIClientAction.TYPE, CPacketUIClientAction.CODEC, CPacketUIClientAction::execute);
        registar.playToServer(CPacketCompassReload.TYPE, CPacketCompassReload.CODEC, CPacketCompassReload::execute);

        registar.playBidirectional(PacketRPCMethodPayload.TYPE, PacketRPCMethodPayload.CODEC, PacketRPCMethodPayload::execute);
    }

}
