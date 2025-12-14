package target_campus.zackary.render

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.DrawContext
import net.minecraft.util.Identifier
import org.joml.Quaternionf
import target_campus.zackary.TrackingTargetManager
import kotlin.math.atan2

object ArrowHudRenderer {
    private val ARROW_TEXTURE = Identifier.of("targetcampus", "textures/gui/arrow.png")

    fun register() {
        HudRenderCallback.EVENT.register(HudRenderCallback { context: DrawContext, _ ->
            val client = MinecraftClient.getInstance()
            val player = client.player ?: return@HudRenderCallback

            val targetPos = TrackingTargetManager.targetPos ?: return@HudRenderCallback

            // println("[ArrowHudRenderer] Tracking target at: (${targetPos.x}, ${targetPos.y}, ${targetPos.z})")

            val dx = targetPos.x - player.x
            val dz = targetPos.z - player.z
            var targetAngle = Math.toDegrees(atan2(dz, dx)) - 90.0
            var angle = targetAngle - player.yaw
            while (angle < -180) angle += 360
            while (angle > 180) angle -= 360

            val screenWidth = client.window.scaledWidth
            val screenHeight = client.window.scaledHeight

            val centerX = 20
            val centerY = screenHeight - 40

            val matrices = context.matrices
            matrices.push()
            matrices.translate(centerX.toDouble(), centerY.toDouble(), 0.0)
            matrices.multiply(Quaternionf().rotateZ(Math.toRadians(angle).toFloat()))
            matrices.translate(-8.0, -8.0, 0.0)

            context.drawTexture(
                ARROW_TEXTURE,
                0, 0,
                0f, 0f,
                16, 16,
                16, 16
            )
            matrices.pop()
        })
    }
}
