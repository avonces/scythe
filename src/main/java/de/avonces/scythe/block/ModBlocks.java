package de.avonces.scythe.block;

import de.avonces.scythe.Scythe;
import de.avonces.scythe.block.custom.ScytheTableBlock;
import de.avonces.scythe.item.ModCreativeModeTab;
import de.avonces.scythe.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Scythe.MOD_ID);

    public static final RegistryObject<Block> SCYTHE_TABLE = registerBlock("scythe_table",
            () -> new ScytheTableBlock(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK)
                    .noOcclusion().requiresCorrectToolForDrops()), ModCreativeModeTab.TAB_SCYTHE_ACCESSORIES);


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
