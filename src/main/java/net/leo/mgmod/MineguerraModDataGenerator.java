package net.leo.mgmod;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.leo.mgmod.datagen.ModEnchantmentGenerator;
import net.leo.mgmod.provider.ModEnglishLangProvider;

public class MineguerraModDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(ModEnchantmentGenerator::new);
		pack.addProvider(ModEnglishLangProvider::new);
	}
}
