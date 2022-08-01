package de.avonces.scythe.block.entity;

import de.avonces.scythe.Scythe;
import de.avonces.scythe.block.ModBlocks;
import de.avonces.scythe.block.entity.custom.ScytheTableBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Scythe.MOD_ID);

    public static final RegistryObject<BlockEntityType<ScytheTableBlockEntity>> SCYTHE_TABLE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("scythe_table_block_entity", () ->
                    BlockEntityType.Builder.of(ScytheTableBlockEntity::new,
                            ModBlocks.SCYTHE_TABLE.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
