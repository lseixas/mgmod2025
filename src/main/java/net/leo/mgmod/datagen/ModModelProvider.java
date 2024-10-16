package net.leo.mgmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.leo.mgmod.item.ModItems;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.item.ArmorItem;

public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.DIAMANITE_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.DIAMANITE_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.DIAMANITE_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.DIAMANITE_BOOTS));

        itemModelGenerator.registerArmor(((ArmorItem) ModItems.NETHER_DIAMOND_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.NETHER_DIAMOND_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.NETHER_DIAMOND_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.NETHER_DIAMOND_BOOTS));

    }

}
