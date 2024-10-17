package net.leo.mgmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.leo.mgmod.MineguerraMod;
import net.leo.mgmod.item.custom.AuraDetectorItem;
import net.leo.mgmod.item.custom.DiamaniteSwordItem;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item DIAMANITE_BULLION = registerItem("diamanite_bullion", new Item(new Item.Settings()));
    public static final Item V1N3X = registerItem("v1n3x", new Item(new Item.Settings()));
    public static final Item NETHERDIAMOND = registerItem("nether_diamond", new Item(new Item.Settings()));


    //Armor

    public static final Item DIAMANITE_CHESTPLATE = registerItem("diamanite_chestplate",
            new ArmorItem(ModArmorMaterials.DIAMANITE_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings()
                    .maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(15))));

    public static final Item DIAMANITE_HELMET = registerItem("diamanite_helmet",
            new ArmorItem(ModArmorMaterials.DIAMANITE_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings()
                    .maxDamage(ArmorItem.Type.HELMET.getMaxDamage(15))));

    public static final Item DIAMANITE_LEGGINGS = registerItem("diamanite_leggings",
            new ArmorItem(ModArmorMaterials.DIAMANITE_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(15))));

    public static final Item DIAMANITE_BOOTS = registerItem("diamanite_boots",
            new ArmorItem(ModArmorMaterials.DIAMANITE_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(15))));


    public static final Item NETHER_DIAMOND_CHESTPLATE = registerItem("nether_diamond_chestplate",
            new ArmorItem(ModArmorMaterials.NETHER_DIAMOND_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings()
                    .maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(15))));

    public static final Item NETHER_DIAMOND_HELMET = registerItem("nether_diamond_helmet",
            new ArmorItem(ModArmorMaterials.NETHER_DIAMOND_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings()
                    .maxDamage(ArmorItem.Type.HELMET.getMaxDamage(15))));

    public static final Item NETHER_DIAMOND_LEGGINGS = registerItem("nether_diamond_leggings",
            new ArmorItem(ModArmorMaterials.NETHER_DIAMOND_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(15))));

    public static final Item NETHER_DIAMOND_BOOTS = registerItem("nether_diamond_boots",
            new ArmorItem(ModArmorMaterials.NETHER_DIAMOND_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(15))));
    //

    //tools

    public static final Item AURA_DETECTOR = registerItem("aura_detector", new AuraDetectorItem(new Item.Settings()));

//    public static final Item DIAMANITE_SWORD = registerItem("diamanite_sword",
//            new SwordItem(ModToolMaterials.DIAMANITE, new Item.Settings()
//                    .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.DIAMANITE, 3, -2.4f))));

    public static final Item DIAMANITE_SWORD = registerItem("diamanite_sword",
            new DiamaniteSwordItem(ModToolMaterials.DIAMANITE, new Item.Settings()
                    .attributeModifiers(DiamaniteSwordItem.createAttributeModifiers(ModToolMaterials.DIAMANITE, 3, -2.4f))));
    public static final Item DIAMANITE_PICKAXE = registerItem("diamanite_pickaxe",
            new PickaxeItem(ModToolMaterials.DIAMANITE, new Item.Settings()
                    .attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.DIAMANITE, 1.0F, -3.0f))));
    public static final Item DIAMANITE_AXE = registerItem("diamanite_axe",
            new AxeItem(ModToolMaterials.DIAMANITE, new Item.Settings()
                    .attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.DIAMANITE, 6.0F, -3.4f))));
    public static final Item DIAMANITE_SHOVEL = registerItem("diamanite_shovel",
            new ShovelItem(ModToolMaterials.DIAMANITE, new Item.Settings()
                    .attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.DIAMANITE, 1.5F, -3.0f))));
    public static final Item DIAMANITE_HOE = registerItem("diamanite_hoe",
            new HoeItem(ModToolMaterials.DIAMANITE, new Item.Settings()
                    .attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.DIAMANITE, -2.0F, -1.0f))));

    public static final Item NETHER_DIAMOND_SWORD = registerItem("nether_diamond_sword",
            new SwordItem(ModToolMaterials.NETHER_DIAMOND, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.NETHER_DIAMOND, 3, -2.45f))));
    public static final Item NETHER_DIAMOND_PICKAXE = registerItem("nether_diamond_pickaxe",
            new PickaxeItem(ModToolMaterials.NETHER_DIAMOND, new Item.Settings()
                    .attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.NETHER_DIAMOND, 1.0F, -3.05f))));
    public static final Item NETHER_DIAMOND_AXE = registerItem("nether_diamond_axe",
            new AxeItem(ModToolMaterials.NETHER_DIAMOND, new Item.Settings()
                    .attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.NETHER_DIAMOND, 6F, -3.5f))));
    public static final Item NETHER_DIAMOND_SHOVEL = registerItem("nether_diamond_shovel",
            new ShovelItem(ModToolMaterials.NETHER_DIAMOND, new Item.Settings()
                    .attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.NETHER_DIAMOND, 1.5f, -3.0f))));
    public static final Item NETHER_DIAMOND_HOE = registerItem("nether_diamond_hoe",
            new HoeItem(ModToolMaterials.NETHER_DIAMOND, new Item.Settings()
                    .attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.NETHER_DIAMOND, -2.0f, -1.0f))));

    //

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(MineguerraMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        MineguerraMod.LOGGER.info("registrando itens, espera ae tio!");

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(DIAMANITE_BULLION);
            entries.add(V1N3X);
            entries.add(NETHERDIAMOND);
            entries.add(AURA_DETECTOR);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(DIAMANITE_HELMET);
            entries.add(DIAMANITE_CHESTPLATE);
            entries.add(DIAMANITE_LEGGINGS);
            entries.add(DIAMANITE_BOOTS);

            entries.add(NETHER_DIAMOND_HELMET);
            entries.add(NETHER_DIAMOND_CHESTPLATE);
            entries.add(NETHER_DIAMOND_LEGGINGS);
            entries.add(NETHER_DIAMOND_BOOTS);
        });
    }
}
