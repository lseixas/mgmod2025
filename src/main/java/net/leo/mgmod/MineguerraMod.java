package net.leo.mgmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.leo.mgmod.item.ModItemGroups;
import net.leo.mgmod.item.ModItems;
import net.leo.mgmod.item.custom.AuraDetectorItem;
import net.leo.mgmod.particles.ModParticles;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.*;
import net.minecraft.client.util.BufferAllocator;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.leo.mgmod.aura.auraComponent;
import net.leo.mgmod.aura.auraComponentRegister;
import net.leo.mgmod.aura.auraComponentInitializer;

public class MineguerraMod implements ModInitializer {
	public static final String MOD_ID = "mgmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemsGroups();
		ModItems.registerModItems();
		ModParticles.registerModParticles();

		ServerTickEvents.END_SERVER_TICK.register(server -> {
			for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
				auraComponent aura = auraComponentRegister.AURA_COMPONENT.get(player);
				aura.setAuraModifier(aura.getAuraModifier() + 0.01f); // Example: Increase the modifier over time
				System.out.println(aura);
			}
		});

	}

}