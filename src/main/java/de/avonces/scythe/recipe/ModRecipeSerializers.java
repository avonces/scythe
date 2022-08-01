package de.avonces.scythe.recipe;

import de.avonces.scythe.Scythe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipeSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Scythe.MOD_ID);

    public static final RegistryObject<RecipeSerializer<ScytheTableRecipe>> SCYTHE_ASSEMBLY_RECIPE_SERIALIZER =
            RECIPE_SERIALIZERS.register("scythe_assembly", () -> ScytheTableRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        RECIPE_SERIALIZERS.register(eventBus);
    }
}
