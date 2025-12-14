package target_campus.zackary.broadcast

import com.mojang.brigadier.arguments.StringArgumentType
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.minecraft.server.command.CommandManager
import net.minecraft.text.Text
import target_campus.zackary.LocationBroadcaster

object BroadcastCommand {
    fun register() {
        CommandRegistrationCallback.EVENT.register { dispatcher, _, _ ->
            dispatcher.register(
                CommandManager.literal("broadcast")
                    .then(
                        CommandManager.literal("location")
                        .then(
                            CommandManager.argument("target", StringArgumentType.word())
                            .executes {
                                val tracker = it.source.player ?: return@executes 0
                                val targetName = StringArgumentType.getString(it, "target")
                                val target = tracker.server.playerManager.getPlayer(targetName)

                                if (target != null) {
                                    LocationBroadcaster.startShowPos(tracker, target)
                                } else {
                                    tracker.sendMessage(Text.literal("❌ 找不到玩家 $targetName"), false)
                                }
                                1
                            }
                        )
                    )
                    .then(
                        CommandManager.literal("stop")
                        .executes {
                            val tracker = it.source.player ?: return@executes 0
                            LocationBroadcaster.cancelShowPos(tracker)
                            1
                        }
                    )
            )
        }
    }
}