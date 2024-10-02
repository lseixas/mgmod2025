package net.leo.mgmod.components.aura_player;

import net.minecraft.util.Identifier;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistryV3;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;
import org.ladysnake.cca.api.v3.world.WorldComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.world.WorldComponentInitializer;

public class MyComponents implements EntityComponentInitializer, WorldComponentInitializer {
    public static final ComponentKey<AuraComponent> AURA_COMPONENT =
            ComponentRegistryV3.INSTANCE.getOrCreate(Identifier.of("mgmod:aura_component"), AuraComponent.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(AURA_COMPONENT, player -> new TotalAuraComponent(), RespawnCopyStrategy.NEVER_COPY);
    }

    @Override
    public void registerWorldComponentFactories(WorldComponentFactoryRegistry registry) {
        registry.register(AURA_COMPONENT, world -> new TotalAuraComponent());
    }
}
