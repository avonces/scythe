package de.avonces.scythe.item.custom;

import de.avonces.scythe.entity.projectile.custom.TetheringFireEntity;
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

public class TetheringFireItem extends Item {
    public TetheringFireItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        TetheringFireEntity tetheringFireEntity = new TetheringFireEntity(level, player);
        tetheringFireEntity.setItem(new ItemStack(ModItems.TETHERING_FIRE_ITEM.get()));
        tetheringFireEntity.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.5F, 0.5F);
        level.addFreshEntity(tetheringFireEntity);

        level.playSound(player, player.getOnPos(), SoundEvents.FIRECHARGE_USE, SoundSource.PLAYERS, 1F, 0F);

        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.isCreative()) {
            itemStack.shrink(1);
        }

        return InteractionResultHolder.success(itemStack);
    }
}
