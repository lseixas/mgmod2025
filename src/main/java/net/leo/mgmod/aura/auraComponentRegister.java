package net.leo.mgmod.aura;

import net.leo.mgmod.MineguerraMod;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.component.ComponentRegistryV3;

public class auraComponentRegister {

    public static final ComponentKey<auraComponent> AURA_COMPONENT =
            ComponentRegistry.getOrCreate(Identifier.of("mgmod", "aura_component"), auraComponent.class);

}



