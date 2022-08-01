package de.avonces.scythe.recipe;

import de.avonces.scythe.Scythe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipeTypes {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
            DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, Scythe.MOD_ID);

    public static final RegistryObject<RecipeType<ScytheTableRecipe>> SCYTHE_ASSEMBLY_RECIPE_TYPE =
            RECIPE_TYPES.register("scythe_assembly", () -> ScytheTableRecipe.Type.INSTANCE);

    public static void register(IEventBus eventBus) {
        RECIPE_TYPES.register(eventBus);
    }
}
