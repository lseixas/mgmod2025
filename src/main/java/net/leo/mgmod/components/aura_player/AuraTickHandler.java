package net.leo.mgmod.components.aura_player;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import static net.leo.mgmod.components.aura_player.MyComponents.AURA_COMPONENT;

public class AuraTickHandler {
    public static void register() {
        // This runs on every server tick
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            // Iterate through all worlds on the server
            for (ServerWorld world : server.getWorlds()) {
                // Iterate through all players in the world
                for (ServerPlayerEntity player : world.getPlayers()) {
                    // Perform the aura calculation and update the player's aura component
                    float auraValue = player.getComponent(AURA_COMPONENT).calculateAura(world, player);
                    player.getComponent(AURA_COMPONENT).updateTrueAura(auraValue); //auraValue

                    // Sync the component to ensure the aura value is updated on the client
                    MyComponents.AURA_COMPONENT.sync(player);
                }
            }
        });
    }
}
