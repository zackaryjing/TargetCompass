package target_campus.zackary

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import org.slf4j.LoggerFactory
import target_campus.zackary.broadcast.BroadcastCommand
import target_campus.zackary.command.TrackCommand

object TargetCampus : ModInitializer {
    private val logger = LoggerFactory.getLogger("targetcampus")

    override fun onInitialize() {

        BroadcastCommand.register()

        ServerTickEvents.END_SERVER_TICK.register { server ->
            LocationBroadcaster.tick(server)
        }

        CommandRegistrationCallback.EVENT.register { dispatcher, _, _ ->
            TrackCommand.register(dispatcher)
        }

        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        logger.info("Mod loaded!")
    }

}