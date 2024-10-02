package net.leo.mgmod.components.aura_as;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.ladysnake.cca.api.v3.component.ComponentV3;

import static net.leo.mgmod.components.aura_player.MyComponents.AURA_COMPONENT;

public interface AuraComponent extends ComponentV3 {

    void incrementTrueAura();

    float getTrueAura();

    float getCurrentAura();

    void updateTrueAura(float value);

    void updateCurrentAura(float value);

    float calculateAura(World world, PlayerEntity player);
}

class TotalAuraComponent implements AuraComponent {

    private float true_aura = 0;
    private float current_aura = 0;

    @Override public void incrementTrueAura() { this.true_aura++; }

    @Override public float getTrueAura() { return true_aura; }

    @Override public float getCurrentAura() { return current_aura; }

    @Override public void updateTrueAura(float value) { this.true_aura = value; }

    @Override public void updateCurrentAura(float value) { this.current_aura = value; }

    @Override public float calculateAura(World world, PlayerEntity player) {

        if (world instanceof ServerWorld serverWorld) {
            float outside_aura = 0.0f;
            for (PlayerEntity otherPlayer : serverWorld.getPlayers()) {

                if (player.distanceTo(otherPlayer) < 3) { // < otherPlayer.maxAuraRadius
                    outside_aura += otherPlayer.getComponent(AURA_COMPONENT).getTrueAura();
                }

            }
            return player.getComponent(AURA_COMPONENT).getTrueAura() - outside_aura;
        }
        return player.getComponent(AURA_COMPONENT).getTrueAura();
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

