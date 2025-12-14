package target_campus.zackary

import net.fabricmc.api.ClientModInitializer
import target_campus.zackary.network.ClientNetworking
import target_campus.zackary.render.ArrowHudRenderer

object TargetCampusClient : ClientModInitializer {
    override fun onInitializeClient() {
        ClientNetworking.register()
        ArrowHudRenderer.register()
    }
}
