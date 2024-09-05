package net.leo.mgmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.leo.mgmod.MineguerraMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item DIAMANITE = registerItem("diamanite", new Item(new Item.Settings()));
    public static final Item V1N3X = registerItem("v1n3x", new Item(new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(MineguerraMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        MineguerraMod.LOGGER.info("registrando itens, espera ae tio!");

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(DIAMANITE);
            entries.add(V1N3X);
        });
    }
}
