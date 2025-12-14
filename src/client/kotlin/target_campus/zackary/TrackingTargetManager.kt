package target_campus.zackary

import net.minecraft.client.MinecraftClient
import net.minecraft.util.math.Vec3d
import java.util.UUID

object TrackingTargetManager {
    private var targetPlayerUuid: UUID? = null
    var targetPos: Vec3d? = null

    fun trackPlayer(uuid: UUID) {
        targetPlayerUuid = uuid
    }

    fun trackPosition(pos: Vec3d) {
        targetPos = pos
    }

    fun clearTracking() {
        targetPlayerUuid = null
        targetPos = null
    }
}