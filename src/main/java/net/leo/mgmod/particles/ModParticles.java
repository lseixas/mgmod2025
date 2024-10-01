package net.leo.mgmod.particles;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.leo.mgmod.MineguerraMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroups;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

public class ModParticles {

    public static final SimpleParticleType AURA_PARTICLE = FabricParticleTypes.simple();


    public static void registerModParticles() {
        MineguerraMod.LOGGER.info("registrando particulas, espera ae tio!");

        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(MineguerraMod.MOD_ID, "aura_particle"), AURA_PARTICLE);
    }

    private void spawnParaboloidParticles(PlayerEntity player) {
        Vec3d playerPos = player.getPos();  // Get player's current position

        // Define the maximum radius (this determines how far particles can spread)
        double maxRadius = 2.0;  // Adjust for larger or smaller auras

        // Number of particles to spawn
        int particleCount = 2;

        for (int i = 0; i < particleCount; i++) {
            // Generate a random radius and angle for the polar coordinates
            double r = Math.random() * maxRadius;  // Random radius from 0 to maxRadius
            double theta = Math.random() * 2 * Math.PI;  // Random angle from 0 to 2π

            // Convert polar coordinates to Cartesian (x, y) around the player
            double xOffset = r * Math.cos(theta);  // X offset
            double zOffset = r * Math.sin(theta);  // Z offset (in the XZ plane)

            // Calculate the Y position (height) using the equation of the paraboloid
            double yOffset = 3.2 - (xOffset * xOffset + zOffset * zOffset);

            // Only spawn particles if the yOffset is greater than 0 (i.e., above the base)
            if (yOffset > 0) {
                // Spawn the particle at the calculated (x, y, z) position
                MinecraftClient.getInstance().world.addParticle(
                        ParticleTypes.END_ROD,  // Type of particle
                        playerPos.x + xOffset,         // X position around player
                        playerPos.y + yOffset,         // Y position based on paraboloid
                        playerPos.z + zOffset,         // Z position around player
                        0, 0, 0  // Particle velocity (set to 0 for stationary aura)
                );
            }
        }
    }
}
