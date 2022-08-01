package de.avonces.scythe.entity.projectile.custom;

import de.avonces.scythe.entity.ModEntityTypes;
import de.avonces.scythe.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

import java.util.Random;

public class TetheringFireEntity extends ThrowableItemProjectile {
    private final LivingEntity shooter;

    public TetheringFireEntity(EntityType<? extends TetheringFireEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

        this.shooter = null;
    }

    public TetheringFireEntity(Level pLevel, LivingEntity pShooter) {
        super(ModEntityTypes.TETHERING_FIRE_ENTITY.get(), pShooter, pLevel);

        this.shooter = pShooter;
    }

    private void createExplosion() {
        this.getCommandSenderWorld().explode(this, this.getX(), this.getY(), this.getZ(), 2.5F, Explosion.BlockInteraction.NONE);
    }

    // TODO: Make projectile follow entities
    public void tick() {
        super.tick();
    }

    protected Item getDefaultItem() {
        // DO NOT CHANGE, THE ITEM TEXTURE WILL NOT RENDER CORRECTLY ANYMORE
        // return ModItems.TETHERING_FIRE_ITEM.get();
        return Items.SNOWBALL;
    }

    protected void onHitEntity(EntityHitResult pResult) {
        Entity entity = pResult.getEntity();

        createExplosion();

        if (entity instanceof LivingEntity livingEntity && livingEntity != this.shooter) {
            // Damage hit entities
            entity.hurt(DamageSource.thrown(this, this.getOwner()), 6.0F);

            // Make hit entities levitate
            livingEntity.addEffect(new MobEffectInstance(MobEffects.LEVITATION, new Random().nextInt(50, 100), 1, false, false, false));

            // Set hit entities on fire
            entity.setSecondsOnFire(30);
        }
    }

    protected void onHitBlock(BlockHitResult pResult) {
        Level level = this.shooter.level;
        Direction direction = pResult.getDirection();
        BlockPos blockPos = pResult.getBlockPos();
        BlockState blockState = level.getBlockState(blockPos);

        createExplosion();

        this.kill();

        if (!CampfireBlock.canLight(blockState) && !CandleBlock.canLight(blockState) && !CandleCakeBlock.canLight(blockState)) {
            blockPos = blockPos.m_121945_(direction);
            if (BaseFireBlock.canBePlacedAt(level, blockPos, pResult.getDirection())) {
                level.setBlockAndUpdate(blockPos, BaseFireBlock.getState(level, blockPos));
                level.gameEvent(this.shooter, GameEvent.BLOCK_PLACE, blockPos);
            }
        } else {
            level.setBlockAndUpdate(blockPos, blockState.setValue(BlockStateProperties.LIT, true));
            level.gameEvent(this.shooter, GameEvent.BLOCK_CHANGE, blockPos);
        }
    }
}
