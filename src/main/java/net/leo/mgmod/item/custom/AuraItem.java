package net.leo.mgmod.item.custom;

import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.apache.logging.log4j.core.jmx.Server;

import java.util.List;

public class AuraItem extends Item {

    public AuraItem(Settings settings) {
        super(settings);
    }

    public float AURA_VALUE_CENTER = 0;
    public float AURA_RADIUS = 0;

    public static float calculateAura(PlayerEntity player) {
        float player_aura = 0;
        float outside_aura = 0;

        for (ItemStack armorStack : player.getArmorItems()) {
            if (armorStack.getItem() instanceof ArmorItem armorItem) {
                if (armorItem.getMaterial() == ArmorMaterials.DIAMOND) {
                    // If it's diamond armor, increment the aura counter
                    player_aura++;
                }
            }
        }

        ServerWorld world = (ServerWorld) player.getWorld();
        List<ArmorStandEntity> ASs = world.getEntitiesByClass(ArmorStandEntity.class, player.getBoundingBox().expand(3), armorStand -> true);

        // Loop through all cows and check if they're within the radius
        for (ArmorStandEntity AS : ASs) {
            // Check the distance
            if (player.squaredDistanceTo(AS) <= 16) {
                outside_aura=2;
            }
        }

        return player_aura - outside_aura;
    }

//        ServerWorld world = (ServerWorld) player.getWorld();
//        List<ServerPlayerEntity> players = world.getPlayers();
//
//        for (PlayerEntity otherPlayer : players) {
//            if(otherPlayer == player) continue;
//
//            if(player.squaredDistanceTo(otherPlayer) <= 16) {
//                outside_aura=2;
//            }
//        }
//
//        return player_aura - outside_aura;
//
//    }

//    public static int getAura(PlayerEntity player) {
//        int auraCounter = 0;
//        for (ItemStack armorStack : player.getArmorItems()) {
//            // Check if the item is diamond armor
//            if (armorStack.getItem() instanceof ArmorItem) {
//                ArmorItem armorItem = (ArmorItem) armorStack.getItem();
//                if (armorItem.getMaterial() == ArmorMaterials.DIAMOND) {
//                    // If it's diamond armor, increment the aura counter
//                    auraCounter++;
//                }
//            }
//        }
//
//        // Return the total count of diamond armor pieces worn
//        return auraCounter;
//    }

}

