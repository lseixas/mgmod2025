package net.leo.mgmod.enchantment;

import com.mojang.serialization.MapCodec;
import net.leo.mgmod.MineguerraMod;
import net.leo.mgmod.enchantment.effects.LightningEnchantmentEffect;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEnchantments {

    public static final RegistryKey<Enchantment> ECSTASY_KEY = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(MineguerraMod.MOD_ID, "ecstasy"));

    public static final MapCodec<LightningEnchantmentEffect> LIGHTNING_EFFECT = register("lightning", LightningEnchantmentEffect.CODEC);

    private static <T extends EnchantmentEntityEffect>MapCodec<T> register(String name, MapCodec<T> codec) {
        return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Identifier.of(MineguerraMod.MOD_ID, name), codec);
    }

    public static void load() {
    }



}
