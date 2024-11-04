package net.leo.mgmod.util;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.leo.mgmod.item.custom.DiamaniteSwordItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import static net.leo.mgmod.components.aura_player.MyComponents.AURA_COMPONENT;

public class DiamaniteSwordAttackEvent implements AttackEntityCallback {

    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {

        if(entity instanceof PigEntity) {
            for(ItemStack hand_items : player.getHandItems()) {
                if(hand_items.getItem() instanceof DiamaniteSwordItem) {
                    float attack_damage_from_aura = (float) calculateBonusDamage(player);

                    if(attack_damage_from_aura < -6) {
                        attack_damage_from_aura = 0;
                    }

                    applyDamage(player, entity, attack_damage_from_aura);
                }
            }
        }

        return ActionResult.PASS;
    }

    private static void applyDamage(PlayerEntity player, Entity target, float damage) {
        System.out.println("hello from applydamage!");
        target.damage(player.getDamageSources().playerAttack(player), 0);
        target.damage(player.getDamageSources().playerAttack(player), damage);
    }

    private double calculateBonusDamage(PlayerEntity attacker) {

        double attacker_current_aura;
        double bonus_attack_damage = 0;

        attacker_current_aura = (double) attacker.getComponent(AURA_COMPONENT).getCurrentAura();

        //positive aura

        if(attacker_current_aura <= 12 && attacker_current_aura > 0) {
            bonus_attack_damage = Math.pow(1.18, attacker_current_aura);
        }

        if(attacker_current_aura > 12) {
            bonus_attack_damage = Math.log(attacker_current_aura)/Math.log(1.3);
        }

        //negative aura

        if(attacker_current_aura < 0 && attacker_current_aura > -18) {
            bonus_attack_damage = (-1) * 0.6 * attacker_current_aura;
        }

        if(attacker_current_aura < -18) {
            bonus_attack_damage = (-1) * Math.log(attacker_current_aura)/Math.log(1.3);
        }

        return bonus_attack_damage;
    }

}
