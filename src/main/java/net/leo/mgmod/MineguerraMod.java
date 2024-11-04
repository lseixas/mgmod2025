package net.leo.mgmod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.leo.mgmod.components.aura_player.AuraTickHandler;
import net.leo.mgmod.enchantment.ModEnchantments;
import net.leo.mgmod.item.ModItemGroups;
import net.leo.mgmod.item.ModItems;

import net.leo.mgmod.particles.ModParticles;

import net.leo.mgmod.util.DiamaniteSwordAttackEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MineguerraMod implements ModInitializer {
	public static final String MOD_ID = "mgmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemsGroups();
		ModItems.registerModItems();
		ModParticles.registerModParticles();

		ModEnchantments.load();

		AuraTickHandler.onServerTick();
		net.leo.mgmod.components.aura_as.AuraTickHandler.onServerTick();

		ServerEntityEvents.EQUIPMENT_CHANGE.register(AuraTickHandler::onEquipmentChange);
		ServerEntityEvents.EQUIPMENT_CHANGE.register(net.leo.mgmod.components.aura_as.AuraTickHandler::onEquipmentChange);

		AttackEntityCallback.EVENT.register(new DiamaniteSwordAttackEvent());
	}

}