package de.avonces.scythe.item.custom;

import de.avonces.scythe.entity.projectile.custom.TetheringFireEntity;
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
import java.util.Random;

public class FireScytheItem extends SwordItem {
    public FireScytheItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
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

    // Add scythe's ultimate: tethering inferno
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        // Play sound
        level.playSound(player, player.getOnPos(), SoundEvents.FIRECHARGE_USE, SoundSource.PLAYERS, 1F, 0F);

        try {
            tetheringInferno(level, player);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.isCreative()) {
            itemStack.hurtAndBreak(5, player,
                    (p_40858_) -> p_40858_.broadcastBreakEvent(hand));
            player.getCooldowns().addCooldown(this, 150);
        }

        return InteractionResultHolder.success(itemStack);
    }

    private void tetheringInferno(Level level, Player player) throws InterruptedException {
        if (!level.isClientSide) {
            Random random = new Random();

            // Set projectile shooter on fire
            player.setSecondsOnFire(3 + random.nextInt(1, 5));

            // Generate and shoot the tethering fire projectiles
            for (int i = 1; i <= 360; i = i + random.nextInt(10,15)) {
                float intY = i + player.getYRot();
                if (intY > 360) {
                    intY = i;
                }

                TetheringFireEntity tetheringFireEntity = new TetheringFireEntity(level, player);
                tetheringFireEntity.setItem(new ItemStack(ModItems.TETHERING_FIRE_ITEM.get()));
                tetheringFireEntity.shootFromRotation(player, 0, intY, 0.0F, 2.5F, 0.5F);
                level.addFreshEntity(tetheringFireEntity);
            }
        }
    }

    // Add tooltip
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if(Screen.hasShiftDown()) {
            components.add(Component.m_237115_("item.scythe.fire_scythe.tooltip")
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
