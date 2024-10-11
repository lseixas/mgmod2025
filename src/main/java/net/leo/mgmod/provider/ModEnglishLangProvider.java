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

        translationBuilder.addEnchantment(ModEnchantments.ECSTASY_KEY, "Ecstasy");
        translationBuilder.add(ModItems.DIAMANITE, "Diamanite");
        translationBuilder.add(ModItems.V1N3X, "V1N3X");
        translationBuilder.add(ModItems.NETHERDIAMOND, "Nether Diamond");

        translationBuilder.add(ModItems.DIAMANITE_HELMET, "Diamanite Helmet");
        translationBuilder.add(ModItems.DIAMANITE_CHESTPLATE, "Diamanite Chestplate");
        translationBuilder.add(ModItems.DIAMANITE_LEGGINGS, "Diamanite Leggings");
        translationBuilder.add(ModItems.DIAMANITE_BOOTS, "Diamanite Boots");

        addText(translationBuilder, ModItemGroups.MG_EQUIP_TAB.getDisplayName(), "EQUIPAMENTOS");
        addText(translationBuilder, ModItemGroups.MG_INGOTS_TAB.getDisplayName(), "MATERIAIS");

    }
}
