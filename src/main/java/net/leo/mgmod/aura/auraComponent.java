package net.leo.mgmod.aura;

import net.minecraft.nbt.NbtCompound;
import org.ladysnake.cca.api.v3.component.Component;
import org.ladysnake.cca.api.v3.entity.RespawnableComponent;

public interface auraComponent extends RespawnableComponent {
    float getAuraModifier();
    void setAuraModifier(float value);

    void copyFrom(RespawnableComponent other, boolean lossless);
}