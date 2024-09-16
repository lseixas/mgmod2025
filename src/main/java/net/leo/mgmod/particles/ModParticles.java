package net.leo.mgmod.particles;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.leo.mgmod.MineguerraMod;
import net.minecraft.client.particle.Particle;
import net.minecraft.item.ItemGroups;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModParticles {

    public static final SimpleParticleType AURA_PARTICLE = FabricParticleTypes.simple();


    public static void registerModParticles() {
        MineguerraMod.LOGGER.info("registrando particulas, espera ae tio!");

        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(MineguerraMod.MOD_ID, "aura_particle"), AURA_PARTICLE);
    }

}
