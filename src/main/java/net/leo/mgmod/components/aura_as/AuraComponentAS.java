package net.leo.mgmod.components.aura_as;

import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;
import org.ladysnake.cca.api.v3.component.ComponentV3;

public interface AuraComponentAS extends ComponentV3 {

    void incrementTrueAura();

    float getTrueAura();

    float getCurrentAura();

    void updateTrueAura(float value);

    void updateCurrentAura(float value);

    float calculateTrueAuraAS(World world, ArmorStandEntity fakePlayer);

    float calculateTrueAura(World world, PlayerEntity player);

    float calculateCurrentAura(World world, PlayerEntity player, ArmorStandEntity fakePlayer);
}

class TotalAuraComponentAS implements AuraComponentAS {

    private float true_aura = 0;
    private float current_aura = 0;

    @Override public void incrementTrueAura() { this.true_aura++; }

    @Override public float getTrueAura() { return true_aura; }

    @Override public float getCurrentAura() { return current_aura; }

    @Override public void updateTrueAura(float value) { this.true_aura = value; }

    @Override public void updateCurrentAura(float value) { this.current_aura = value; }

    @Override public float calculateTrueAuraAS(World world, ArmorStandEntity fakePlayer) {
        float total = 0.0f;
        for (ItemStack armorStack : fakePlayer.getArmorItems()) {
            if (armorStack.getItem() instanceof ArmorItem armorItem) {
                if (armorItem.getMaterial() == ArmorMaterials.DIAMOND) {
                    total += 1.5f;
                }
            }
        }
        return total;
    }

    @Override public float calculateTrueAura(World world, PlayerEntity player) {
        float total = 0.0f;
        for (ItemStack armorStack : player.getArmorItems()) {
            if (armorStack.getItem() instanceof ArmorItem armorItem) {
                if (armorItem.getMaterial() == ArmorMaterials.DIAMOND) {
                    // If it's diamond armor, increment the aura counter
                    total += 1.5f;
                }
            }
        }
        return total;
    }

    @Override public float calculateCurrentAura(World world, PlayerEntity player, ArmorStandEntity fakePlayer) {

        float total = this.getTrueAura();

        for (ItemStack armorStack : fakePlayer.getArmorItems()) {
            if(armorStack.getItem() instanceof ArmorItem armorItem) {
                if(armorItem.getMaterial() == ArmorMaterials.DIAMOND) { //change to netherdiamond
                    total -= 1.5f;
                }
            }
        }

        return total;
    }

    @Override
    public void readFromNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        this.true_aura = tag.getFloat("true_aura");
        this.current_aura = tag.getFloat("current_aura");
    }

    @Override
    public void writeToNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        tag.putFloat("true_aura", this.true_aura);
        tag.putFloat("current_aura", this.current_aura);
    }

}

