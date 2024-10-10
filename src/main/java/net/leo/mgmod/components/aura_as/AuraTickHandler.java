package net.leo.mgmod.components.aura_as;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.Box;


import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static net.leo.mgmod.components.aura_as.MyComponents.AURA_COMPONENT_AS;
import static net.leo.mgmod.components.aura_player.MyComponents.AURA_COMPONENT;

public class AuraTickHandler {
    public static void onEquipmentChange(LivingEntity entity, EquipmentSlot slot, ItemStack oldStack, ItemStack newStack) {

        System.out.println("Armor Changed");
        if (entity instanceof ArmorStandEntity) {
            if (slot.getType() == EquipmentSlot.Type.HUMANOID_ARMOR) {
                if(!oldStack.isEmpty() && newStack.isEmpty()) {
                    float true_aura = entity.getComponent(AURA_COMPONENT_AS).calculateTrueAuraAS((ArmorStandEntity) entity);
                    entity.getComponent(AURA_COMPONENT_AS).updateTrueAura(true_aura);
                    System.out.println("Updated unequip");
                }
                else if (oldStack.isEmpty() && !newStack.isEmpty()) {
                    float true_aura = entity.getComponent(AURA_COMPONENT_AS).calculateTrueAuraAS((ArmorStandEntity) entity);
                    entity.getComponent(AURA_COMPONENT_AS).updateTrueAura(true_aura);
                    System.out.println("Updated unequip");
                }
            }
            net.leo.mgmod.components.aura_as.MyComponents.AURA_COMPONENT_AS.sync(entity);
        }

    }
    public static void onServerTick() {
        // This runs on every server tick
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            // Iterate through all worlds on the server
            for (ServerWorld world : server.getWorlds()) {

                Box detection_box = new Box(1000, 10, 1000, -1000, -10, -1000);

                List<ArmorStandEntity> world_armorstand_entities = world.getEntitiesByClass(ArmorStandEntity.class, detection_box, armorStand -> true);

                // Iterate through all armorstands in the world (radius)
                for (ArmorStandEntity fake_player : world_armorstand_entities) {

                    float true_aura_value = fake_player.getComponent(AURA_COMPONENT_AS).calculateTrueAuraAS(fake_player);
                    fake_player.getComponent(AURA_COMPONENT_AS).updateTrueAura(true_aura_value);

                    float effect_radius = true_aura_value;

                    List<ServerPlayerEntity> nearPlayer = world.getPlayers();

                    if(nearPlayer.isEmpty()) {
                        System.out.println("Not detecting_AS");
                        fake_player.getComponent(AURA_COMPONENT_AS).updateCurrentAura(true_aura_value);
                    }

                    for(ServerPlayerEntity outsider : nearPlayer) {
                        System.out.println("Detecting_AS");
                        // Perform the aura calculation and update the player's aura component

                        if(fake_player.getComponent(AURA_COMPONENT_AS).getTrueAura() < outsider.distanceTo(outsider)) {
                            System.out.println("Not reaching_AS");
                            float auraValue = fake_player.getComponent(AURA_COMPONENT_AS).getTrueAura();
                            fake_player.getComponent(AURA_COMPONENT_AS).updateCurrentAura(auraValue);
                        }

                        else {
                            System.out.println("Reaching");

                            float auraValue = fake_player.getComponent(AURA_COMPONENT_AS).calculateCurrentAuraAS(outsider);
                            fake_player.getComponent(AURA_COMPONENT_AS).updateCurrentAura(auraValue); //auraValue
                        }

                        // Sync the component to ensure the aura value is updated on the client
                        MyComponents.AURA_COMPONENT_AS.sync(fake_player);
                    }
                }
            }
        });
    }
}
