// main/kotlin/target_campus/zackary/network/TrackingSyncPacket.kt

package target_campus.zackary.network

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.util.math.Vec3d
import java.util.*

object TrackingSyncPacket {
    fun send(player: ServerPlayerEntity, targetUuid: UUID?, targetPos: Vec3d?) {
        val payload = TrackingSyncPayload(targetUuid, targetPos)
        ServerPlayNetworking.send(player, payload) // ✅ 注意：这里是 payload 对象，不再是 ID + buf
    }
}
