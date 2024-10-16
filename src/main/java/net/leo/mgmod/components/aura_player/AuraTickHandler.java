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

                    float player_true_aura = player.getComponent(AURA_COMPONENT).getTrueAura();
                    float detection_radius = 8f;

                    Box near_player_box = new Box(player.getX() - detection_radius, player.getY() - detection_radius, player.getZ() - detection_radius,
                            player.getX() + detection_radius, player.getY() + detection_radius, player.getZ() + detection_radius);

                    List<PlayerEntity> near_players = world.getEntitiesByClass(PlayerEntity.class, near_player_box, player_arr -> false);
                    near_players.remove(player);

                    if(near_players.isEmpty()) {
                        player.getComponent(AURA_COMPONENT).setCurrentAura(player_true_aura);
                    }

                    float outside_aura = 0f;

                    for(PlayerEntity outsider : near_players) {

                        float outsider_true_aura = outsider.getComponent(AURA_COMPONENT).getTrueAura();
                        float outsider_effect_radius = outsider_true_aura;

                        if(outsider_effect_radius > player.distanceTo(outsider)) {
                            outside_aura += outsider_true_aura;
                        }
                        else {
                            player.getComponent(AURA_COMPONENT).setCurrentAura(player_true_aura);
                        }
                    }
                    player.getComponent(AURA_COMPONENT).updateCurrentAura(outside_aura);
                    AURA_COMPONENT.sync(player);


                }
            }
        });
    }
}
