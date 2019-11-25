package com.ruokapp.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ruokapp.R;
import com.ruokapp.core.recipe.Recipe;
import com.ruokapp.core.session.Session;
import com.ruokapp.core.user.User;
import com.ruokapp.core.db.DBUtils;
import com.ruokapp.core.db.SQLiteHandler;
import com.ruokapp.core.helper.ErrorMessage;
import com.ruokapp.core.service.ServiceHandle;
import com.ruokapp.core.util.ImageHandler;
import com.ruokapp.core.util.StringParser;

public class DiscoverActivity extends AppCompatActivity {

    private ImageView imageRef;
    private TextView titleRecipe;
    private TextView timePreparation;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);

        imageRef = findViewById(R.id.image_reference);
        titleRecipe = findViewById(R.id.food_name);
        timePreparation = findViewById(R.id.time);

        loadRecipe();
    }

    public void like(View v){
        if(insertFoodRef()){
            Toast.makeText(getApplicationContext(), "Added to Matches list", Toast.LENGTH_LONG).show();
            loadRecipe();
        }
    }

    protected boolean insertFoodRef(){
        ContentValues foodRef = new ContentValues();
        foodRef.put(DBUtils.ID_USER, User.getInstanceUser().getId());
        foodRef.put(DBUtils.ID_FOOD_REF, Recipe.getInstance().getId());
        try {
            SQLiteHandler.insertFoodRef(this,foodRef);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, ErrorMessage.GENERAL_ERROR,Toast.LENGTH_LONG).show();
        }
        return false;
    }

    public void notLike(View v){
        loadRecipe();
    }

    private void loadRecipe(){
        if(User.getInstanceUser().getPreference().hasPreferences()){
            ServiceHandle.getInstance().getRecipe(User.getInstanceUser().getPreference().getPreferences());
        } else {
            ServiceHandle.getInstance().getRecipe();
        }

        if(Recipe.getInstance().getTitle() == null){
            Toast.makeText(this, ErrorMessage.MAX_REQUEST_AVAILABLE_ERROR,Toast.LENGTH_LONG).show();
        }
        titleRecipe.setText(StringParser.getRecipeTitleToDiscover(Recipe.getInstance().getTitle()));
        timePreparation.setText(Recipe.getInstance().getReadyInMinutes());
        imageRef.setImageBitmap(ImageHandler.getImageFromUrl(Recipe.getInstance().getImage()));
        ServiceHandle.getInstance().closeConnection();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_discover,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()){
            case R.id.action_settings:
                intent = new Intent(this, PreferenceActivity.class);
                startActivity(intent);
                break;
            case R.id.action_fav:
                intent = new Intent(this,FavActivity.class);
                startActivity(intent);
                break;
            case  R.id.action_logout:
                Session.getInstance(this).closeSession();
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
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

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
