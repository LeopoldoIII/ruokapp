package com.ruokapp.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ruokapp.R;
import com.ruokapp.core.helper.ErrorMessage;
import com.ruokapp.core.recipe.RecipeInfo;
import com.ruokapp.core.session.Session;
import com.ruokapp.core.util.ImageHandler;
import com.ruokapp.core.util.StringParser;

public class RecipeInfoActivity extends AppCompatActivity {

    ImageView imageRecipe;
    TextView titleRecipe;
    TextView preparationTime;
    TextView steps;
    TextView ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        imageRecipe = findViewById(R.id.main_image_food);
        titleRecipe = findViewById(R.id.main_title_food);
        preparationTime = findViewById(R.id.time);
        steps = findViewById(R.id.preparation_steps);
        ingredients = findViewById(R.id.ingredients);
        loadRecipeInfo();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()){
            case R.id.action_logout:
                Session.getInstance(this).closeSession();
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.action_settings:
                intent = new Intent(this, PreferenceActivity.class);
                startActivity(intent);
                break;
            case  R.id.action_about_us:
                intent = new Intent(this, AboutUsActivity.class);
                startActivity(intent);
                break;
            default:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadRecipeInfo(){
        if(RecipeInfo.getInstance().getId()== null){
            Toast.makeText(this, ErrorMessage.MAX_REQUEST_AVAILABLE_ERROR, Toast.LENGTH_LONG).show();
        }
        imageRecipe.setImageBitmap(ImageHandler.getImageFromUrl(RecipeInfo.getInstance().getImageUrl()));
        titleRecipe.setText(StringParser.getRecipeTitleToFav(RecipeInfo.getInstance().getTitle()));
        preparationTime.setText(RecipeInfo.getInstance().getPreparation());

    }
}
