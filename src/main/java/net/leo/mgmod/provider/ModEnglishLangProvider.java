package net.leo.mgmod.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.leo.mgmod.MineguerraMod;
import net.leo.mgmod.enchantment.ModEnchantments;
import net.leo.mgmod.item.ModItemGroups;
import net.leo.mgmod.item.ModItems;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModEnglishLangProvider extends FabricLanguageProvider {


    public ModEnglishLangProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    private static void addText(@NotNull TranslationBuilder builder, @NotNull Text text, @NotNull String value) {
        if (text.getContent() instanceof TranslatableTextContent translatableTextContent) {
            builder.add(translatableTextContent.getKey(), value);
        }
        else {
            MineguerraMod.LOGGER.warn("Failed to add translation for text: {}", text.getString());
        }
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {

        //Enchantments

        translationBuilder.addEnchantment(ModEnchantments.ECSTASY_KEY, "Ecstasy");

        //Enchantments

        //Items

        translationBuilder.add(ModItems.DIAMANITE_BULLION, "Diamanite Bullion");
        translationBuilder.add(ModItems.V1N3X, "V1N3X");
        translationBuilder.add(ModItems.NETHERDIAMOND, "Nether Diamond");
        translationBuilder.add(ModItems.AURA_DETECTOR, "Aura Detector");

        //Items

        //Armor

        translationBuilder.add(ModItems.DIAMANITE_HELMET, "Diamanite Helmet");
        translationBuilder.add(ModItems.DIAMANITE_CHESTPLATE, "Diamanite Chestplate");
        translationBuilder.add(ModItems.DIAMANITE_LEGGINGS, "Diamanite Leggings");
        translationBuilder.add(ModItems.DIAMANITE_BOOTS, "Diamanite Boots");

        translationBuilder.add(ModItems.NETHER_DIAMOND_HELMET, "Nether Diamond Helmet");
        translationBuilder.add(ModItems.NETHER_DIAMOND_CHESTPLATE, "Nether Diamond Chestplate");
        translationBuilder.add(ModItems.NETHER_DIAMOND_LEGGINGS, "Nether Diamond Leggings");
        translationBuilder.add(ModItems.NETHER_DIAMOND_BOOTS, "Nether Diamond Boots");

        //Armor

        //Tools

        translationBuilder.add(ModItems.DIAMANITE_SWORD, "Diamanite Sword");
        translationBuilder.add(ModItems.DIAMANITE_PICKAXE, "Diamanite Pickaxe");
        translationBuilder.add(ModItems.DIAMANITE_AXE, "Diamanite Axe");
        translationBuilder.add(ModItems.DIAMANITE_SHOVEL, "Diamanite Shovel");
        translationBuilder.add(ModItems.DIAMANITE_HOE, "Diamanite Hoe");

        translationBuilder.add(ModItems.NETHER_DIAMOND_SWORD, "Nether Diamond Sword");
        translationBuilder.add(ModItems.NETHER_DIAMOND_PICKAXE, "Nether Diamond Pickaxe");
        translationBuilder.add(ModItems.NETHER_DIAMOND_AXE, "Nether Diamond Axe");
        translationBuilder.add(ModItems.NETHER_DIAMOND_SHOVEL, "Nether Diamond Shovel");
        translationBuilder.add(ModItems.NETHER_DIAMOND_HOE, "Nether Diamond Hoe");

        //Tools

        //Item tabs

        addText(translationBuilder, ModItemGroups.MG_ITEMS_TAB_KEY.getDisplayName(), "Mg Items");
        addText(translationBuilder, ModItemGroups.MG_AMOR_TAB_KEY.getDisplayName(), "Mg Armor");
        addText(translationBuilder, ModItemGroups.MG_TOOLS_TAB_KEY.getDisplayName(), "Mg Tools");

        //Item tabs

    }
}
