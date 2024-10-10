package net.leo.mgmod.mixin;

import net.leo.mgmod.item.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ArmorStandEntity.class)
public class ExampleMixin {
	@Inject(at = @At("HEAD"), method = "interactAt", cancellable = true)
	private void init(PlayerEntity player, Vec3d hitPos, Hand hand, CallbackInfoReturnable<ActionResult> cir) {

		ItemStack held_item = player.getStackInHand(hand);

		if(held_item.isOf(ModItems.AURA_DETECTOR)) {
			held_item.useOnEntity(player, (ArmorStandEntity)(Object)this, hand);

			cir.setReturnValue(ActionResult.SUCCESS);
		}

	}
}