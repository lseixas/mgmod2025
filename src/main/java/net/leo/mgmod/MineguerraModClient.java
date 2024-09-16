package net.leo.mgmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.leo.mgmod.particles.ModParticles;
import net.minecraft.client.particle.EndRodParticle;

public class MineguerraModClient implements ClientModInitializer{
    @Override
    public void onInitializeClient() {
        ParticleFactoryRegistry.getInstance().register(ModParticles.AURA_PARTICLE, EndRodParticle.Factory::new);
    }
}
