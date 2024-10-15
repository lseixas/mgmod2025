package net.leo.mgmod.components.aura_player;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.ComponentV3;

import static net.leo.mgmod.components.aura_player.MyComponents.AURA_COMPONENT;
import net.leo.mgmod.item.ModArmorMaterials;

public interface AuraComponent extends ComponentV3 {

    void incrementTrueAura();

    float getTrueAura();

    float getCurrentAura();

    void updateTrueAura(float value);

    void updateCurrentAura(float value);

    float calculateTrueAura(PlayerEntity player);

    float calculateCurrentAura(PlayerEntity outsider);

}

class TotalAuraComponent implements AuraComponent {

    private float true_aura = 0;
    private float current_aura = 0;

    @Override public void incrementTrueAura() { this.true_aura++; }

    @Override public float getTrueAura() { return true_aura; }

    @Override public float getCurrentAura() { return current_aura; }

    @Override public void updateTrueAura(float value) { this.true_aura = value; }

    @Override public void updateCurrentAura(float value) { this.current_aura = value; }

    @Override
    public float calculateTrueAura(PlayerEntity player) {
        float total = 0.0f;
        for (ItemStack armorStack : player.getArmorItems()) {
            if (armorStack.getItem() instanceof ArmorItem armorItem) {
                if (armorItem.getMaterial() == ModArmorMaterials.DIAMANITE_ARMOR_MATERIAL) { //diamanite
                    // If it's diamond armor, increment the aura counter
                    total += 1.5f;
                }
                if (armorItem.getMaterial() == ModArmorMaterials.NETHER_DIAMOND_ARMOR_MATERIAL) { //netherdiamond
                    total -= 1.5f;
                }
            }
        }
        return total;
    }

    @Override
    public float calculateCurrentAura(PlayerEntity outsider) {

        return this.getTrueAura() + outsider.getComponent(AURA_COMPONENT).getTrueAura();

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

