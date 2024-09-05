package net.leo.mgmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.leo.mgmod.MineguerraMod;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final ItemGroup MG_INGOTS_TAB = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(MineguerraMod.MOD_ID, "mg_ingots_tab"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.V1N3X))
                    .displayName(Text.translatable("itemgroup.mgmod.mg_ingots_tab"))
                    .entries((displayContext, entries) -> {   //ADD ITEMS HERE TO REGISTER IN THE TAB
                        entries.add(ModItems.DIAMANITE);
                        entries.add(ModItems.NETHERDIAMOND);
                    })
                    .build());

    public static void registerItemsGroups () {
        MineguerraMod.LOGGER.info("registrando grupos de itens!");
    }
}
