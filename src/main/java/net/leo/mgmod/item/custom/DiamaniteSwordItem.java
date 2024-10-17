package net.leo.mgmod.item.custom;

import net.leo.mgmod.item.ModToolMaterials;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;

import static net.leo.mgmod.components.aura_player.MyComponents.AURA_COMPONENT;

public class DiamaniteSwordItem extends SwordItem {
    public DiamaniteSwordItem(ModToolMaterials toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        if(attacker instanceof PlayerEntity player_attacker) {
            if(player_attacker.getStackInHand(Hand.MAIN_HAND).equals(stack)) {
                float bonus_damage = (float) calculateBonusDamage(player_attacker);
                target.damage(player_attacker.getDamageSources().playerAttack(player_attacker), bonus_damage);

                player_attacker.sendMessage (Text.literal("Dano adicional causado: " + bonus_damage + " ao alvo: " + target.getName().getString()));

            }
        }
        return super.postHit(stack, target, attacker);
    }

    private double calculateBonusDamage(PlayerEntity attacker) {

        double attacker_current_aura;
        double bonus_attack_damage = 0;

        attacker_current_aura = (double) attacker.getComponent(AURA_COMPONENT).getCurrentAura();

        if(attacker_current_aura <= 12 && attacker_current_aura > 0) {
            bonus_attack_damage = Math.pow(1.18, attacker_current_aura);
        }

        if(attacker_current_aura > 12) {
            bonus_attack_damage = Math.log(attacker_current_aura)/Math.log(1.3);
        }

        return bonus_attack_damage;
    }

}
