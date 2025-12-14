package target_campus.zackary

import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.Text
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World
import target_campus.zackary.network.TrackingSyncPacket
import java.util.UUID

object LocationBroadcaster {

    // --- 玩家间的追踪映射 (用于聊天消息提示) ---
    private val trackingPlayerMap = mutableMapOf<UUID, UUID>() // trackerUUID -> targetUUID
    private val trackingPosMap = mutableMapOf<UUID, Vec3d>() // trackerUUID -> targetUUID
    private val showPosMap = mutableMapOf<UUID, UUID>() // trackerUUID -> targetUUID

    fun startShowPos(tracker: ServerPlayerEntity, target: ServerPlayerEntity) {
        showPosMap[tracker.uuid] = target.uuid
        tracker.sendMessage(Text.literal("已开始显示 ${target.name.string} 的位置"), false)
    }

    fun cancelShowPos(tracker: ServerPlayerEntity) {
        showPosMap.remove(tracker.uuid)
        tracker.sendMessage(Text.literal("已停止显示"), false)
    }

    fun trackPlayer(tracker: ServerPlayerEntity, target: ServerPlayerEntity) {
        trackingPlayerMap[tracker.uuid] = target.uuid
    }

    fun trackPosition(tracker: ServerPlayerEntity, pos: Vec3d) {
        trackingPosMap[tracker.uuid] = pos
    }

    fun clearTracking(tracker: ServerPlayerEntity) {
        trackingPlayerMap.remove(tracker.uuid)
    }

    // tick：每秒广播追踪消息和同步包
    fun tick(server: MinecraftServer) {
        // 每秒执行一次
        if (server.overworld.time % 20L != 0L) return

        val playerManager = server.playerManager

        for ((trackerUUID, targetUUID) in trackingPlayerMap) {
            val tracker = playerManager.getPlayer(trackerUUID)
            val target = playerManager.getPlayer(targetUUID)

            if (tracker != null && target != null) {
                // logic: send both target pos and target uuid,
                if (tracker.world == target.world) {
                    TrackingSyncPacket.send(tracker, targetUUID, target.pos)
                    // println("tracker: ${tracker.uuid} target: ${target.uuid} pos: ${target.pos}")
                } else {
                    TrackingSyncPacket.send(tracker, targetUUID, null)
                }
            }
        }
        for ((trackerUUID, targetPos) in trackingPosMap) {
            val tracker = playerManager.getPlayer(trackerUUID)

            if (tracker != null) {
                TrackingSyncPacket.send(tracker, null, targetPos)
            }
        }
        for ((trackerUUID, targetUUID) in showPosMap) {
            val tracker = playerManager.getPlayer(trackerUUID)
            val target = playerManager.getPlayer(targetUUID)

            if (tracker != null && target != null) {
                val pos = target.blockPos
                val dimension = when (target.world.registryKey) {
                    World.OVERWORLD -> "O"
                    World.NETHER -> "N"
                    World.END -> "E"
                    else -> "?"
                }
                tracker.sendMessage(
                    Text.literal("🧭 [$dimension] ${target.name.string} at (${pos.x}, ${pos.y}, ${pos.z})"), true
                )
            }
        }
    }
}
