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
                }
                else if (oldStack.isEmpty() && !newStack.isEmpty()) {
                    float true_aura = entity.getComponent(AURA_COMPONENT_AS).calculateTrueAuraAS((ArmorStandEntity) entity);
                    entity.getComponent(AURA_COMPONENT_AS).updateTrueAura(true_aura);
                }
            }
            net.leo.mgmod.components.aura_as.MyComponents.AURA_COMPONENT_AS.sync(entity);
        }

    }
    public static void onServerTick() {
        // This runs on every server tick
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (ServerWorld world : server.getWorlds()) {

                Box detection_box = new Box(1000, 128, 1000, -1000, -64, -1000);

                List<ArmorStandEntity> world_armorstand_entities = world.getEntitiesByClass(ArmorStandEntity.class, detection_box, armorStand -> true);

                for (ArmorStandEntity fake_player : world_armorstand_entities) {

                    float true_aura_value = fake_player.getComponent(AURA_COMPONENT_AS).calculateTrueAuraAS(fake_player);
                    fake_player.getComponent(AURA_COMPONENT_AS).updateTrueAura(true_aura_value);

                    float detection_radius = 8f;

                    Box near_fake_player_box = new Box(fake_player.getX() - detection_radius, fake_player.getY() - detection_radius, fake_player.getZ() - detection_radius,
                            fake_player.getX() + detection_radius, fake_player.getY() + detection_radius, fake_player.getZ() + detection_radius);


                    List<ArmorStandEntity> near_as_entities = world.getEntitiesByClass(ArmorStandEntity.class, near_fake_player_box, armorStand -> true);
                    near_as_entities.remove(fake_player);

                    float outside_aura = 0f;

                    if(near_as_entities.isEmpty()) {
                        fake_player.getComponent(AURA_COMPONENT_AS).setCurrentAura(true_aura_value);
                    }

                    for(ArmorStandEntity outsider : near_as_entities) {
                        float outsider_effect_radius = outsider.getComponent(AURA_COMPONENT_AS).getTrueAura();
                        if(outsider_effect_radius > fake_player.distanceTo(outsider)) {
                            outside_aura += outsider.getComponent(AURA_COMPONENT_AS).getTrueAura();
                        }
                        else{
                            fake_player.getComponent(AURA_COMPONENT_AS).setCurrentAura(true_aura_value);
                        }
                    }
                    fake_player.getComponent(AURA_COMPONENT_AS).updateCurrentAura(outside_aura); //auraValue
                    MyComponents.AURA_COMPONENT_AS.sync(fake_player);
                }
            }
        });
    }
}
