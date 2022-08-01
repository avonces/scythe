package de.avonces.scythe.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import de.avonces.scythe.Scythe;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class ScytheTableRecipe implements Recipe<SimpleContainer> {
    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;

    public ScytheTableRecipe(ResourceLocation id, ItemStack output,
                                   NonNullList<Ingredient> recipeItems) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
    }


    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        boolean hasScytheHeadIngredients =
                recipeItems.get(0).test(pContainer.getItem(0)) &&
                        recipeItems.get(1).test(pContainer.getItem(1)) &&
                        recipeItems.get(2).test(pContainer.getItem(2)) &&
                        recipeItems.get(3).test(pContainer.getItem(3)) &&
                        recipeItems.get(4).test(pContainer.getItem(4));

        boolean hasScytheStickIngredients =
                recipeItems.get(5).test(pContainer.getItem(5)) &&
                        recipeItems.get(6).test(pContainer.getItem(7)) &&
                        recipeItems.get(7).test(pContainer.getItem(9));

        return hasScytheHeadIngredients && hasScytheStickIngredients;
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer) {
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<ScytheTableRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "scythe_assembly";
    }

    public static class Serializer implements RecipeSerializer<ScytheTableRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID =
                new ResourceLocation(Scythe.MOD_ID,"scythe_assembly");

        @Override
        public ScytheTableRecipe fromJson(ResourceLocation id, JsonObject json) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));

            JsonArray ingredients = GsonHelper.getAsJsonArray(json, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(8, Ingredient.EMPTY); // 10 -result -amethyst

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new ScytheTableRecipe(id, output, inputs);
        }

        // Same order as toNetwork function
        @Override
        public ScytheTableRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buf));
            }

            ItemStack output = buf.readItem();
            return new ScytheTableRecipe(id, output, inputs);
        }

        // Same order as fromNetwork function
        @Override
        public void toNetwork(FriendlyByteBuf buf, ScytheTableRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buf);
            }
            buf.writeItemStack(recipe.getResultItem(), false);
        }

        public RecipeSerializer<?> setRegistryName(ResourceLocation name) {
            return INSTANCE;
        }

        @Nullable
        public ResourceLocation getRegistryName() {
            return ID;
        }

        public Class<RecipeSerializer<?>> getRegistryType() {
            return Serializer.castClass(RecipeSerializer.class);
        }

        @SuppressWarnings("unchecked") // We need this wrapper, because generics
        private static <G> Class<G> castClass(Class<?> cls) {
            return (Class<G>)cls;
        }
    }
}
