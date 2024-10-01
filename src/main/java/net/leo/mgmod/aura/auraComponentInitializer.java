package net.leo.mgmod.aura;

import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;

public class auraComponentInitializer implements EntityComponentInitializer {

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(auraComponentRegister.AURA_COMPONENT, player -> new auraComponentImpl());
    }
}
