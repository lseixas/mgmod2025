package net.leo.mgmod.item.custom;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.leo.mgmod.item.ModItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

import java.util.HashMap;
import java.util.Map;

public class AuraDetectorItem extends Item {
    public AuraDetectorItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (!world.isClient()) {
            player.sendMessage(Text.literal("You used the item!"), false);
            player.sendMessage(Text.literal("Your aura count currently is: " + getAura(player)), false);

        }

        return TypedActionResult.success(itemStack);
    }


    private int getAura(PlayerEntity player) {
        int auraCounter = 0;
        for (ItemStack armorStack : player.getArmorItems()) {
            // Check if the item is diamond armor
            if (armorStack.getItem() instanceof ArmorItem) {
                ArmorItem armorItem = (ArmorItem) armorStack.getItem();
                if (armorItem.getMaterial() == ArmorMaterials.DIAMOND) {
                    // If it's diamond armor, increment the aura counter
                    auraCounter++;
                }
            }
        }

        // Return the total count of diamond armor pieces worn
        return auraCounter;
    }

    public static void auraDisplayer() {

        HudRenderCallback.EVENT.register((drawContext, tickDeltaManager) -> {
            Matrix4f transformationMatrix = drawContext.getMatrices().peek().getPositionMatrix();
            Tessellator tessellator = Tessellator.getInstance();

            BufferBuilder buffer = tessellator.begin(
                    VertexFormat.DrawMode.TRIANGLE_STRIP,
                    VertexFormats.POSITION_COLOR);

            buffer.vertex(transformationMatrix, 20, 20, 5).color(0xFF414141);
            buffer.vertex(transformationMatrix, 5, 40, 5).color(0xFF000000);
            buffer.vertex(transformationMatrix, 35, 40, 5).color(0xFF000000);
            buffer.vertex(transformationMatrix, 20, 60, 5).color(0xFF414141);

            RenderSystem.setShader(GameRenderer::getPositionColorProgram);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

            BufferRenderer.drawWithGlobalProgram(buffer.end());
        });
    }


}
