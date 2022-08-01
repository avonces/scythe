package de.avonces.scythe.entity.projectile.custom;

import de.avonces.scythe.entity.ModEntityTypes;
import de.avonces.scythe.item.ModItems;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

import java.util.Collection;

public class BloodStreamEntity extends ThrowableItemProjectile {
    private final LivingEntity shooter;
    private final Collection<MobEffectInstance> shooterActiveEffects;

    public BloodStreamEntity(EntityType<? extends BloodStreamEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

        // Get all effects of the projectile shooter and put them in a collection
        this.shooter = null;
        this.shooterActiveEffects = null;
    }

    public BloodStreamEntity(Level pLevel, LivingEntity pShooter) {
        super(ModEntityTypes.BLOOD_STREAM_ENTITY.get(), pShooter, pLevel);

        // Get all effects of the projectile shooter and put them in a collection
        this.shooter = pShooter;
        this.shooterActiveEffects = pShooter.getActiveEffects();
    }

    protected Item getDefaultItem() {
        // DO NOT CHANGE, THE ITEM TEXTURE WILL NOT RENDER CORRECTLY ANYMORE
        // return ModItems.BLOOD_STREAM_ITEM.get();
        return Items.SNOWBALL;
    }

    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity entity = pResult.getEntity();

        if (entity instanceof LivingEntity livingEntity && livingEntity != this.shooter) {
            // Damage hit entities
            entity.hurt(DamageSource.thrown(this, this.getOwner()), 6.0F);

            if (this.shooter != null) {
                // Apply effects of projectile shooter to hit entities
                for (MobEffectInstance activeEffect : this.shooterActiveEffects) {
                    livingEntity.addEffect(activeEffect);
                }
                // Remove all effects from the projectile shooter
                this.shooter.removeAllEffects();
            }
        }
    }

    protected void onHitBlock(BlockHitResult pResult) {
        this.kill();
    }
}
