package net.leo.mgmod.util;

import net.leo.mgmod.MineguerraMod;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {

    public static class Blocks {
        public static final TagKey<Block> NEEDS_DIAMANITE_TOOL = createTag("needs_diamanite_tool");
        public static final TagKey<Block> INCORRECT_FOR_DIAMANITE_TOOL_KEY = createTag("incorrect_for_diamanite_tool");

        public static final TagKey<Block> INCORRECT_FOR_NETHER_DIAMOND_TOOL_KEY = createTag("incorrect_for_diamanite_tool");
    }

    private static TagKey<Block> createTag(String name) {
        return TagKey.of(RegistryKeys.BLOCK, Identifier.of(MineguerraMod.MOD_ID, name));
    }


}
