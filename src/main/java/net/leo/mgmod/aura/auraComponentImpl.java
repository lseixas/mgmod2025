package net.leo.mgmod.aura;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.entity.RespawnableComponent;

public class auraComponentImpl implements auraComponent {
    private float auraModifier = 0.0f;

    @Override
    public float getAuraModifier() {
        return auraModifier;
    }

    @Override
    public void setAuraModifier(float value) {
        this.auraModifier = value;
    }

    @Override
    public void readFromNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        auraModifier = tag.getFloat("AuraModifier");
    }

    @Override
    public void writeToNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        tag.putFloat("AuraModifier", auraModifier);
    }

    @Override
    public void copyFrom(RespawnableComponent other, boolean lossless) {
        if (other instanceof auraComponentImpl) {
            this.auraModifier = ((auraComponentImpl) other).getAuraModifier();
        }
    }
}
