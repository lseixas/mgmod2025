package net.leo.mgmod.components.aura_player;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;

import java.util.List;

import static net.leo.mgmod.components.aura_as.MyComponents.AURA_COMPONENT_AS;
import static net.leo.mgmod.components.aura_player.MyComponents.AURA_COMPONENT;

public class AuraTickHandler {
    public static void onEquipmentChange(LivingEntity entity, EquipmentSlot slot, ItemStack oldStack, ItemStack newStack) {

        System.out.println("Armor Changed");
        if (entity instanceof PlayerEntity) {
            if (slot.getType() == EquipmentSlot.Type.HUMANOID_ARMOR) {
                if(!oldStack.isEmpty() && newStack.isEmpty()) {
                    float true_aura = entity.getComponent(AURA_COMPONENT).calculateTrueAura((PlayerEntity) entity);
                    entity.getComponent(AURA_COMPONENT).updateTrueAura(true_aura);
                    System.out.println("Updated unequip");
                }
                else if (oldStack.isEmpty() && !newStack.isEmpty()) {
                    float true_aura = entity.getComponent(AURA_COMPONENT).calculateTrueAura((PlayerEntity) entity);
                    entity.getComponent(AURA_COMPONENT).updateTrueAura(true_aura);
                    System.out.println("Updated unequip");
                }
            }
            MyComponents.AURA_COMPONENT.sync(entity);
        }

    }
    public static void onServerTick() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            // Iterate through all worlds on the server
            for (ServerWorld world : server.getWorlds()) {

                // Iterate through all players in the world
                for (ServerPlayerEntity player : world.getPlayers()) {

                    float aura_radius = player.getComponent(AURA_COMPONENT).getTrueAura();
                    float detection_radius = 8;

                    Box near_player_box = new Box(player.getX() - detection_radius, player.getY() - detection_radius, player.getZ() - detection_radius,
                            player.getX() + detection_radius, player.getY() + detection_radius, player.getZ() + detection_radius);

                    List<PlayerEntity> players = world.getEntitiesByClass(PlayerEntity.class, near_player_box, armorStand -> true);

                    if(players.isEmpty()) {
                        float true_aura = player.getComponent(AURA_COMPONENT).getTrueAura();
                        player.getComponent(AURA_COMPONENT).updateCurrentAura(true_aura);
                    }

                    for(PlayerEntity outsider : players) {
                        if(outsider != player) {
                            System.out.println("Detecting");

                            if(aura_radius <= outsider.distanceTo(player)) {
                                System.out.println("Not reaching");
                                float true_aura = player.getComponent(AURA_COMPONENT).calculateTrueAura(player);
                                player.getComponent(AURA_COMPONENT).updateCurrentAura(true_aura);
                            }

                            else {
                                System.out.println("Reaching");

                                float aura_value = player.getComponent(AURA_COMPONENT).calculateCurrentAura(player, outsider);
                                player.getComponent(AURA_COMPONENT).updateCurrentAura(aura_value); //auraValue

                            }

                            // Sync the component to ensure the aura value is updated on the client
                            net.leo.mgmod.components.aura_player.MyComponents.AURA_COMPONENT.sync(player);
                        }
                    }

                }
            }
        });
    }
}
