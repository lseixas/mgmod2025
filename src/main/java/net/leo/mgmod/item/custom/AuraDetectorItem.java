package net.leo.mgmod.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import static net.leo.mgmod.components.aura_as.MyComponents.AURA_COMPONENT_AS;

public class AuraDetectorItem extends Item {
    public AuraDetectorItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (!world.isClient()) {
            player.sendMessage(Text.literal("You used the item!"), false);

            player.sendMessage(Text.literal("True Aura: " + player.getComponent(AURA_COMPONENT_AS).getTrueAura()));

            player.sendMessage(Text.literal("Current Aura: " + player.getComponent(AURA_COMPONENT_AS).getCurrentAura()));
        }

        return TypedActionResult.success(itemStack);
    }

}
