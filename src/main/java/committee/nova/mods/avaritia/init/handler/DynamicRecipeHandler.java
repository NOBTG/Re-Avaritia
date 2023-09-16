package committee.nova.mods.avaritia.init.handler;

import committee.nova.mods.avaritia.Static;
import committee.nova.mods.avaritia.common.crafting.recipe.CompressorRecipe;
import committee.nova.mods.avaritia.common.crafting.recipe.ShapelessExtremeCraftingRecipe;
import committee.nova.mods.avaritia.common.item.singularity.Singularity;
import committee.nova.mods.avaritia.init.event.RegisterRecipesEvent;
import committee.nova.mods.avaritia.init.registry.ModItems;
import committee.nova.mods.avaritia.util.SingularityUtils;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/5/15 20:34
 * Version: 1.0
 */
@Mod.EventBusSubscriber
public class DynamicRecipeHandler {
    @SubscribeEvent
    public static void onRegisterRecipes(RegisterRecipesEvent event) {
        for (var singularity : SingularityRegistryHandler.getInstance().getSingularities()) {
            if (singularity.isRecipeDisabled()) {
                continue;
            }

            var compressorRecipe = makeSingularityRecipe(singularity);

            if (compressorRecipe != null)
                event.register(compressorRecipe);
        }

        //infinity_catalyst
//        NonNullList<ItemStack> catalystIngredients = NonNullList.create();
//
//        SingularityRegistryHandler.getInstance().getSingularities()
//                .stream()
//                .filter(s -> s.getIngredient() != Ingredient.EMPTY)
//                .limit(74)
//                .map(SingularityUtils::getItemForSingularity)
//                .forEach(catalystIngredients::add);
//
//        // add others
//        catalystIngredients.add(new ItemStack(Blocks.EMERALD_BLOCK));
//        catalystIngredients.add(new ItemStack(ModItems.crystal_matrix_ingot.get()));
//        catalystIngredients.add(new ItemStack(ModItems.neutron_ingot.get()));
//        catalystIngredients.add(new ItemStack(ModItems.cosmic_meatballs.get()));
//        catalystIngredients.add(new ItemStack(ModItems.ultimate_stew.get()));
//        catalystIngredients.add(new ItemStack(ModItems.endest_pearl.get()));
//        catalystIngredients.add(new ItemStack(ModItems.record_fragment.get()));
//
//        event.register(addExtremeShapelessRecipe(
//                ModItems.infinity_catalyst.get().getDefaultInstance(),
//                catalystIngredients
//        ));



    }


    private static CompressorRecipe makeSingularityRecipe(Singularity singularity) {
        var ingredient = singularity.getIngredient();
        if (ingredient == Ingredient.EMPTY)
            return null;

        var id = singularity.getId();
        var recipeId = new ResourceLocation(Static.MOD_ID, id.getPath() + "_singularity");
        var output = SingularityUtils.getItemForSingularity(singularity);
        int ingredientCount = singularity.getIngredientCount();
        int timeRequired = singularity.getTimeRequired();

        return new CompressorRecipe(recipeId, ingredient, output, ingredientCount, timeRequired);
    }

    public static ShapelessExtremeCraftingRecipe addExtremeShapelessRecipe(ItemStack result, List<ItemStack> ingredients) {
        List<ItemStack> arraylist = new ArrayList<>();

        for (ItemStack stack : ingredients) {
            if (stack != null) {
                arraylist.add(stack.copy());
            } else {
                throw new RuntimeException("Invalid shapeless recipes!");
            }
        }

        return new ShapelessExtremeCraftingRecipe(ForgeRegistries.ITEMS.getKey(result.getItem()), getList(arraylist), result);
    }

    private static NonNullList<Ingredient> getList(List<ItemStack> arrayList) {
        NonNullList<Ingredient> ingredients = NonNullList.create();
        for (ItemStack stack : arrayList) {
            ingredients.add(Ingredient.of(stack));
        }
        return ingredients;
    }
}
