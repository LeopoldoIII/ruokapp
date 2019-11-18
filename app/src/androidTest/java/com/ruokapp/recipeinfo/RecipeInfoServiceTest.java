package com.ruokapp.recipeinfo;

import com.ruokapp.core.recipe.RecipeInfo;
import com.ruokapp.core.service.ServiceHandle;

import org.junit.Assert;
import org.junit.Test;

public class RecipeInfoServiceTest {

    @Test
    public void getRecipeInfoTest(){
        String ids = "54321";
        String imageUrl = "https://spoonacular.com/recipeImages/54321-556x370.jpg";
        String title = "Morning Glory Muffins";
        String preparation = "45";
        ServiceHandle.getInstance().getRecipeInfo(ids);
        Assert.assertEquals(ids, RecipeInfo.getInstance().getId());
        Assert.assertEquals(imageUrl, RecipeInfo.getInstance().getImageUrl());
        Assert.assertEquals(title, RecipeInfo.getInstance().getTitle());
        Assert.assertEquals(preparation, RecipeInfo.getInstance().getPreparationInMinutes());
        Assert.assertFalse(RecipeInfo.getInstance().getInstructions().isEmpty());
        Assert.assertFalse(RecipeInfo.getInstance().getRecipeIngredients().isEmpty());
    }
}
