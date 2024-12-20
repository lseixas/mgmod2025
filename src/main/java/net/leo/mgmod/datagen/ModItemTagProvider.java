package net.leo.mgmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.leo.mgmod.item.ModItems;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.DIAMANITE_CHESTPLATE)
                .add(ModItems.DIAMANITE_HELMET)
                .add(ModItems.DIAMANITE_BOOTS)
                .add(ModItems.DIAMANITE_LEGGINGS)

                .add(ModItems.NETHER_DIAMOND_CHESTPLATE)
                .add(ModItems.NETHER_DIAMOND_HELMET)
                .add(ModItems.NETHER_DIAMOND_BOOTS)
                .add(ModItems.NETHER_DIAMOND_LEGGINGS);

    }

}
