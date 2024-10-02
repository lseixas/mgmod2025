package net.leo.mgmod.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import static net.leo.mgmod.components.aura_player.MyComponents.AURA_COMPONENT;

public class AuraDetectorItem extends Item {
    public AuraDetectorItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (!world.isClient()) {
            player.sendMessage(Text.literal("You used the item!"), false);

            player.sendMessage(Text.literal("Your aura count currently is: " + player.getComponent(AURA_COMPONENT).getTrueAura()));

            player.sendMessage(Text.literal("nbt aura is: " + player.getComponent(AURA_COMPONENT).getTrueAura()));
        }

        return TypedActionResult.success(itemStack);
    }

}
