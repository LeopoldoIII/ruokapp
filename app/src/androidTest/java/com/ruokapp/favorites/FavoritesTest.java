package com.ruokapp.favorites;

import com.ruokapp.core.recipe.RecipeRef;
import com.ruokapp.core.service.ServiceHandle;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class FavoritesTest {

    @Test
    public void getRecipedInfoFromAPI(){
        String ids = "54321";
        String imageUrl = "https://spoonacular.com/recipeImages/54321-556x370.jpg";
        String title = "Morning Glory Muffins";
        String preparation = "45";
        ArrayList<RecipeRef> recipeRefs = ServiceHandle.getInstance().getFavoritesRecipes(ids);
        Assert.assertTrue(recipeRefs.size()>0);
        RecipeRef recipe = recipeRefs.get(0);
        Assert.assertEquals(Integer.parseInt(ids),recipe.getId());
        Assert.assertEquals(imageUrl,recipe.getImage());
        Assert.assertEquals(preparation,recipe.getPreparationMinutes());
        Assert.assertEquals(title,recipe.getTitle());
    }

}
