package net.leo.mgmod.components.aura_as;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.Box;


import java.util.List;

import static net.leo.mgmod.components.aura_as.MyComponents.AURA_COMPONENT_AS;

public class AuraTickHandler {
    public static void register() {
        // This runs on every server tick
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            // Iterate through all worlds on the server
            for (ServerWorld world : server.getWorlds()) {

                // Iterate through all players in the world
                for (ServerPlayerEntity player : world.getPlayers()) {

                    Box box = new Box(player.getX() - 10, player.getY() - 10, player.getZ() - 10,
                            player.getX() + 10, player.getY() + 10, player.getZ() + 10);

                    List<ArmorStandEntity> nearAS = world.getEntitiesByClass(ArmorStandEntity.class, box, armorStand -> true);

                    if(nearAS.isEmpty()) {
                        float auraValue = player.getComponent(AURA_COMPONENT_AS).calculateTrueAura(world, player);
                        player.getComponent(AURA_COMPONENT_AS).updateCurrentAura(auraValue);
                    }

                    for(ArmorStandEntity fakePlayer : world.getEntitiesByClass(ArmorStandEntity.class, box, armorStand -> true)) {
                        // Perform the aura calculation and update the player's aura component

                        float currentAuraValue = player.getComponent(AURA_COMPONENT_AS).calculateTrueAura(world, player);
                        player.getComponent(AURA_COMPONENT_AS).updateTrueAura(currentAuraValue);

                        float auraValue = player.getComponent(AURA_COMPONENT_AS).calculateCurrentAura(world, player, fakePlayer);
                        player.getComponent(AURA_COMPONENT_AS).updateCurrentAura(auraValue); //auraValue

                        // Sync the component to ensure the aura value is updated on the client
                        MyComponents.AURA_COMPONENT_AS.sync(player);
                    }
                }
            }
        });
    }
}
