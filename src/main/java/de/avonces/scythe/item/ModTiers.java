package de.avonces.scythe.item;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.Tags;

public class ModTiers {
    public static final ForgeTier SCYTHE = new ForgeTier(4, 2400, 0.5F, 0, 15, Tags.Blocks.NEEDS_NETHERITE_TOOL,
            () -> Ingredient.of(Items.NETHERITE_INGOT));
}
