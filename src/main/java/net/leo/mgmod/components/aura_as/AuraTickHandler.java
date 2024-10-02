package net.leo.mgmod.components.aura_as;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.Box;


import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static net.leo.mgmod.components.aura_as.MyComponents.AURA_COMPONENT_AS;

public class AuraTickHandler {
    public static void register() {
        // This runs on every server tick
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            // Iterate through all worlds on the server
            for (ServerWorld world : server.getWorlds()) {

                // Iterate through all players in the world
                for (ServerPlayerEntity player : world.getPlayers()) {

                    float trueAuraValue = player.getComponent(AURA_COMPONENT_AS).calculateTrueAura(world, player);
                    player.getComponent(AURA_COMPONENT_AS).updateTrueAura(trueAuraValue);

                    float max_aura_radius = 8;

                    Box near_as_box = new Box(player.getX() - max_aura_radius, player.getY() - max_aura_radius, player.getZ() - max_aura_radius,
                            player.getX() + max_aura_radius, player.getY() + max_aura_radius, player.getZ() + max_aura_radius);

                    List<ArmorStandEntity> nearAS = world.getEntitiesByClass(ArmorStandEntity.class, near_as_box, armorStand -> true);

                    if(nearAS.isEmpty()) {
                        System.out.println("Not detecting");
                        player.getComponent(AURA_COMPONENT_AS).updateCurrentAura(trueAuraValue);
                    }

                    for(ArmorStandEntity fakePlayer : nearAS) {
                        System.out.println("Detecting");
                        float fake_player_true_aura_value = fakePlayer.getComponent(AURA_COMPONENT_AS).calculateTrueAuraAS(world, fakePlayer);
                        fakePlayer.getComponent(AURA_COMPONENT_AS).updateTrueAura(fake_player_true_aura_value);
                        // Perform the aura calculation and update the player's aura component

                        if(fakePlayer.getComponent(AURA_COMPONENT_AS).getTrueAura() <= player.distanceTo(fakePlayer)) {
                            System.out.println("Not reaching");
                            float auraValue = player.getComponent(AURA_COMPONENT_AS).calculateTrueAura(world, player);
                            player.getComponent(AURA_COMPONENT_AS).updateCurrentAura(auraValue);
                        }

                        else {
                            System.out.println("Reaching");
                            float currentAuraValue = player.getComponent(AURA_COMPONENT_AS).calculateTrueAura(world, player);
                            player.getComponent(AURA_COMPONENT_AS).updateTrueAura(currentAuraValue);

                            float auraValue = player.getComponent(AURA_COMPONENT_AS).calculateCurrentAura(world, player, fakePlayer);
                            player.getComponent(AURA_COMPONENT_AS).updateCurrentAura(auraValue); //auraValue
                        }

                        // Sync the component to ensure the aura value is updated on the client
                        MyComponents.AURA_COMPONENT_AS.sync(player);
                    }
                }
            }
        });
    }
}
