package net.leo.mgmod.item;

import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.leo.mgmod.MineguerraMod;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final ItemGroup MG_ITEMS_TAB_KEY = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(MineguerraMod.MOD_ID, "mg_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.V1N3X))
                    .displayName(Text.translatable("itemgroup.mgmod.mg_ingots_tab"))
                    .entries((displayContext, entries) -> {   //ADD ITEMS HERE TO REGISTER IN THE TAB
                        entries.add(ModItems.DIAMANITE_BULLION);
                        entries.add(ModItems.NETHERDIAMOND);
                        entries.add(ModItems.V1N3X);
                    })
                    .build());

    public static final ItemGroup MG_AMOR_TAB_KEY = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(MineguerraMod.MOD_ID, "mg_armor"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.DIAMANITE_CHESTPLATE))
                    .displayName(Text.translatable("itemgroup.mgmod.mg_armor_tab"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.DIAMANITE_HELMET);
                        entries.add(ModItems.DIAMANITE_CHESTPLATE);
                        entries.add(ModItems.DIAMANITE_LEGGINGS);
                        entries.add(ModItems.DIAMANITE_BOOTS);

                        entries.add(ModItems.NETHER_DIAMOND_HELMET);
                        entries.add(ModItems.NETHER_DIAMOND_CHESTPLATE);
                        entries.add(ModItems.NETHER_DIAMOND_LEGGINGS);
                        entries.add(ModItems.NETHER_DIAMOND_BOOTS);

                    })
                    .build());

    public static final ItemGroup MG_TOOLS_TAB_KEY = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(MineguerraMod.MOD_ID, "mg_tools"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.NETHER_DIAMOND_SWORD))
                    .displayName(Text.translatable("itemgroup.mgmod.mg_tools_tab"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.AURA_DETECTOR);

                        entries.add(ModItems.DIAMANITE_SWORD);
                        entries.add(ModItems.DIAMANITE_PICKAXE);
                        entries.add(ModItems.DIAMANITE_AXE);
                        entries.add(ModItems.DIAMANITE_SHOVEL);
                        entries.add(ModItems.DIAMANITE_HOE);

                        entries.add(ModItems.NETHER_DIAMOND_SWORD);
                        entries.add(ModItems.NETHER_DIAMOND_PICKAXE);
                        entries.add(ModItems.NETHER_DIAMOND_AXE);
                        entries.add(ModItems.NETHER_DIAMOND_SHOVEL);
                        entries.add(ModItems.NETHER_DIAMOND_HOE);

                    })
                    .build());

    public static void registerItemsGroups () {
        MineguerraMod.LOGGER.info("registrando grupos de itens!");
    }
}
