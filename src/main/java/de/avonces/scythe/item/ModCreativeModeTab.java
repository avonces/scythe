package de.avonces.scythe.item;

import de.avonces.scythe.block.ModBlocks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab TAB_SCYTHE_SCYTHES = new CreativeModeTab("scythe_scythes_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.SCYTHE.get());
        }
    };
    public static final CreativeModeTab TAB_SCYTHE_ACCESSORIES = new CreativeModeTab("scythe_accessories_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.SCYTHE_TABLE.get());
        }
    };
    public static final CreativeModeTab TAB_SCYTHE_PROJECTILES = new CreativeModeTab("scythe_projectiles_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.TETHERING_FIRE_ITEM.get());
        }
    };
}
