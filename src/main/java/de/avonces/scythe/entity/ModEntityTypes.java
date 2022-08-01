package de.avonces.scythe.entity;

import de.avonces.scythe.Scythe;
import de.avonces.scythe.entity.projectile.custom.BloodStreamEntity;
import de.avonces.scythe.entity.projectile.custom.TetheringFireEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Scythe.MOD_ID);

    public static final RegistryObject<EntityType<BloodStreamEntity>> BLOOD_STREAM_ENTITY = ENTITY_TYPES.register("blood_stream_entity", () ->
            EntityType.Builder.<BloodStreamEntity>of(BloodStreamEntity::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10)
                    .build(new ResourceLocation(Scythe.MOD_ID, "blood_stream_entity").toString()));
    public static final RegistryObject<EntityType<TetheringFireEntity>> TETHERING_FIRE_ENTITY = ENTITY_TYPES.register("tethering_fire_entity", () ->
            EntityType.Builder.<TetheringFireEntity>of(TetheringFireEntity::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10)
                    .build(new ResourceLocation(Scythe.MOD_ID, "tethering_fire_entity").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
