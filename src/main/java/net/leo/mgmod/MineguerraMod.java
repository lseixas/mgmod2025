package net.leo.mgmod;

import net.fabricmc.api.ModInitializer;

import net.leo.mgmod.item.ModItemGroups;
import net.leo.mgmod.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MineguerraMod implements ModInitializer {
	public static final String MOD_ID = "mgmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemsGroups();
		ModItems.registerModItems();
	}
}