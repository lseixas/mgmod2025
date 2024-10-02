package net.leo.mgmod.components.aura_as;

import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.util.Identifier;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistryV3;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;
import org.ladysnake.cca.api.v3.world.WorldComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.world.WorldComponentInitializer;

public class MyComponents implements EntityComponentInitializer, WorldComponentInitializer {
    public static final ComponentKey<AuraComponentAS> AURA_COMPONENT_AS =
            ComponentRegistryV3.INSTANCE.getOrCreate(Identifier.of("mgmod:aura_component_as"), AuraComponentAS.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(AURA_COMPONENT_AS, player -> new TotalAuraComponentAS(), RespawnCopyStrategy.NEVER_COPY);
        registry.registerFor(ArmorStandEntity.class, AURA_COMPONENT_AS, armorStand -> new TotalAuraComponentAS());
    }

    @Override
    public void registerWorldComponentFactories(WorldComponentFactoryRegistry registry) {
        registry.register(AURA_COMPONENT_AS, world -> new TotalAuraComponentAS());
    }
}
