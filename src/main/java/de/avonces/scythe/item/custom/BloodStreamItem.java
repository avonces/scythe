package de.avonces.scythe.item.custom;

import de.avonces.scythe.entity.projectile.custom.BloodStreamEntity;
import de.avonces.scythe.item.ModItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BloodStreamItem extends Item {
    public BloodStreamItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        BloodStreamEntity bloodStreamEntity = new BloodStreamEntity(level, player);
        bloodStreamEntity.setItem(new ItemStack(ModItems.BLOOD_STREAM_ITEM.get()));
        bloodStreamEntity.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 5.0F, 0.5F);
        level.addFreshEntity(bloodStreamEntity);

        level.playSound(player, player.getOnPos(), SoundEvents.BEE_STING, SoundSource.PLAYERS, 1F, 0F);

        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.isCreative()) {
            itemStack.shrink(1);
        }

        return InteractionResultHolder.success(itemStack);
    }
}
