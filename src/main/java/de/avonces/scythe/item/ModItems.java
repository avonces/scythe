package de.avonces.scythe.item;

import de.avonces.scythe.Scythe;
import de.avonces.scythe.item.custom.BloodStreamItem;
import de.avonces.scythe.item.custom.FireScytheItem;
import de.avonces.scythe.item.custom.ScytheItem;
import de.avonces.scythe.item.custom.TetheringFireItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Scythe.MOD_ID);

    // Items for texturing custom projectiles
    public static final RegistryObject<Item> BLOOD_STREAM_ITEM = ITEMS.register("blood_stream",
            () -> new BloodStreamItem(new Item.Properties().tab(ModCreativeModeTab.TAB_SCYTHE_PROJECTILES).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> TETHERING_FIRE_ITEM = ITEMS.register("tethering_fire",
            () -> new TetheringFireItem(new Item.Properties().tab(ModCreativeModeTab.TAB_SCYTHE_PROJECTILES).rarity(Rarity.EPIC)
                    .fireResistant()));

    // Items for crafting the scythes
    public static final RegistryObject<Item> TETHERITE_INGOT = ITEMS.register("tetherite_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TAB_SCYTHE_ACCESSORIES).rarity(Rarity.EPIC)
                    .fireResistant()));

    // Scythes
    public static final RegistryObject<Item> SCYTHE = ITEMS.register("scythe",
            () -> new ScytheItem(ModTiers.SCYTHE, 11, 0.5F,
                    new Item.Properties().tab(ModCreativeModeTab.TAB_SCYTHE_SCYTHES).stacksTo(1).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> FIRE_SCYTHE = ITEMS.register("fire_scythe",
            () -> new FireScytheItem(ModTiers.SCYTHE, 15, 0.5F,
                    new Item.Properties().tab(ModCreativeModeTab.TAB_SCYTHE_SCYTHES).stacksTo(1).rarity(Rarity.EPIC)
                            .fireResistant()));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
