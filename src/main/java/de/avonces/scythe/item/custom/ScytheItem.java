package de.avonces.scythe.item.custom;

import de.avonces.scythe.entity.projectile.custom.BloodStreamEntity;
import de.avonces.scythe.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class ScytheItem extends SwordItem {
    public ScytheItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    // Add pulling effect on entity right click
    @Override
    public InteractionResult interactLivingEntity(ItemStack pStack, Player pPlayer, LivingEntity pInteractionTarget, InteractionHand pUsedHand) {
        pInteractionTarget.hurt(DamageSource.playerAttack(pPlayer), 1F);
        pInteractionTarget.knockback(
                0.35D,
                pInteractionTarget.getX() - pPlayer.getX(),
                pInteractionTarget.getZ() - pPlayer.getZ()
        );

        return InteractionResult.SUCCESS;
    }

    // Add scythe's ultimate: blood stream
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        // Play sound
        level.playSound(player, player.getOnPos(), SoundEvents.BEE_STING, SoundSource.PLAYERS, 1F, 0F);

        bloodStream(level, player);

        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.isCreative()) {
            itemStack.hurtAndBreak(5, player,
                    (p_40858_) -> p_40858_.broadcastBreakEvent(hand));
            player.getCooldowns().addCooldown(this, 150);
        }

        return InteractionResultHolder.success(itemStack);
    }

    private void bloodStream(Level level, Player player) {
        if (!level.isClientSide) {
            // Damage projectile shooter
            player.hurt(DamageSource.MAGIC, 4F);

            // Generate blood stream projectile
            BloodStreamEntity bloodStreamEntity = new BloodStreamEntity(level, player);
            bloodStreamEntity.setItem(new ItemStack(ModItems.BLOOD_STREAM_ITEM.get()));
            bloodStreamEntity.shootFromRotation(player, 0, player.getYRot(), 0.0F, 5.0F, 0.5F);
            level.addFreshEntity(bloodStreamEntity);
        }
    }

    // Add tooltip
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if(Screen.hasShiftDown()) {
            components.add(Component.m_237115_("item.scythe.scythe.tooltip")
                    .withStyle(ChatFormatting.AQUA));
            // components.add(Component.m_237113_("Tooltip Text").withStyle(ChatFormatting.AQUA));
        } else {
            components.add(Component.m_237115_("item.scythe.reveal_tooltip_tooltip")
                    .withStyle(ChatFormatting.YELLOW));
            // components.add(Component.m_237113_("Press SHIFT to reveal tooltip").withStyle(ChatFormatting.YELLOW));
        }

        super.appendHoverText(stack, level, components, flag);
    }
}
