package target_campus.zackary.command

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.DoubleArgumentType.doubleArg
import net.minecraft.command.argument.EntityArgumentType
import net.minecraft.server.command.CommandManager.argument
import net.minecraft.server.command.CommandManager.literal
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.util.math.Vec3d
import target_campus.zackary.LocationBroadcaster

object TrackCommand {
    fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        dispatcher.register(
            literal("track")
                .then(
                    literal("start")
                        .then(
                            argument("player", EntityArgumentType.player())
                                .executes { ctx ->
                                    val player = EntityArgumentType.getPlayer(ctx, "player")
                                    val tracker = ctx.source.player ?: return@executes 0
                                    LocationBroadcaster.trackPlayer(tracker, player)
                                    ctx.source.sendFeedback(
                                        { net.minecraft.text.Text.literal("Tracking started on ${player.name.string}") },
                                        false
                                    )
                                    1
                                }
                        )
                )
                .then(
                    literal("clear")
                        .executes { ctx ->
                            val tracker = ctx.source.player ?: return@executes 0
                            LocationBroadcaster.clearTracking(tracker)
                            ctx.source.sendFeedback({
                                net.minecraft.text.Text.literal("Tracking cleared")
                            }, false)
                            1
                        }
                )
                .then(
                    argument("x", doubleArg())
                        .then(
                            argument("y", doubleArg())
                                .then(
                                    argument("z", doubleArg())
                                        .executes { ctx ->
                                            val tracker = ctx.source.player ?: return@executes 0
                                            val x =
                                                com.mojang.brigadier.arguments.DoubleArgumentType.getDouble(ctx, "x")
                                            val y =
                                                com.mojang.brigadier.arguments.DoubleArgumentType.getDouble(ctx, "y")
                                            val z =
                                                com.mojang.brigadier.arguments.DoubleArgumentType.getDouble(ctx, "z")
                                            LocationBroadcaster.trackPosition(tracker, Vec3d(x, y, z))
                                            ctx.source.sendFeedback({
                                                net.minecraft.text.Text.literal("Started tracking position: ($x, $y, $z)")
                                            }, false)
                                            1
                                        }
                                )
                        )
                )
        )
    }
}
