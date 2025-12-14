// main/kotlin/target_campus/zackary/network/TrackingSyncPayload.kt

package target_campus.zackary.network

import net.minecraft.network.PacketByteBuf
import net.minecraft.network.packet.CustomPayload
import net.minecraft.util.Identifier
import net.minecraft.util.math.Vec3d
import java.util.*

class TrackingSyncPayload(
    val targetUuid: UUID?, val targetPos: Vec3d?
) : CustomPayload {

    override fun getId(): CustomPayload.Id<out CustomPayload> = ID

    fun write(buf: PacketByteBuf) {
        if (targetUuid != null && targetPos != null) {
            buf.writeBoolean(true)
            buf.writeUuid(targetUuid)
            buf.writeDouble(targetPos.x)
            buf.writeDouble(targetPos.y)
            buf.writeDouble(targetPos.z)
        } else if (targetUuid == null && targetPos != null) {
            buf.writeBoolean(false)
            buf.writeUuid(UUID(0, 0)) // 占位
            buf.writeDouble(targetPos.x)
            buf.writeDouble(targetPos.y)
            buf.writeDouble(targetPos.z)
        } else if (targetUuid != null) {
            buf.writeBoolean(true)
            buf.writeUuid(targetUuid)
            buf.writeDouble(0.0)
            buf.writeDouble(0.0)
            buf.writeDouble(0.0)
        } else {
            buf.writeBoolean(false)
            buf.writeUuid(UUID(0, 0))
            buf.writeDouble(Double.NaN)
            buf.writeDouble(Double.NaN)
            buf.writeDouble(Double.NaN)
        }
    }

    companion object {
        val ID: CustomPayload.Id<TrackingSyncPayload> = CustomPayload.Id(Identifier.of("targetcampus", "tracking_sync"))

        fun read(buf: PacketByteBuf): TrackingSyncPayload {
            val hasUuid = buf.readBoolean()
            val uuid = buf.readUuid()
            val x = buf.readDouble()
            val y = buf.readDouble()
            val z = buf.readDouble()

            val actualUuid =
                if (hasUuid && uuid.mostSignificantBits != 0L && uuid.leastSignificantBits != 0L) uuid else null

            val actualPos = if (!x.isNaN() && !y.isNaN() && !z.isNaN()) Vec3d(x, y, z) else null

            return TrackingSyncPayload(actualUuid, actualPos)
        }
    }
}
