package target_campus.zackary.network

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry
import net.minecraft.client.MinecraftClient
import net.minecraft.network.codec.PacketCodec
import target_campus.zackary.TrackingTargetManager

object ClientNetworking {
    fun register() {
        PayloadTypeRegistry.playS2C().register(
            TrackingSyncPayload.ID,
            PacketCodec.of(
                { payload, buf -> payload.write(buf) },     // Writer
                { buf -> TrackingSyncPayload.read(buf) }    // Reader
            )
        )


        ClientPlayNetworking.registerGlobalReceiver(TrackingSyncPayload.ID) { payload, _ ->
            val client = MinecraftClient.getInstance()
            client.execute {
                val uuid = payload.targetUuid
                val pos = payload.targetPos
                if (uuid == null && pos == null) {
                    TrackingTargetManager.clearTracking()
                } else {
                    if (uuid != null) {
                        TrackingTargetManager.trackPlayer(uuid)
                    }
                    if (pos != null) {
                        // println("[ClientNetwork] Tracking target at: (${pos.x}, ${pos.y}, ${pos.z})")
                        TrackingTargetManager.trackPosition(pos)
                    }
                }
            }
        }
    }
}
