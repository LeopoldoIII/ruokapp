package com.ruokapp.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ruokapp.R;
import com.ruokapp.core.db.DBUtils;
import com.ruokapp.core.db.SQLiteHandler;
import com.ruokapp.core.helper.ErrorMessage;
import com.ruokapp.core.recipe.RecipeRef;
import com.ruokapp.core.service.ServiceHandle;
import com.ruokapp.core.session.Session;
import com.ruokapp.core.user.User;
import com.ruokapp.views.adapter.Adapter;

import java.util.ArrayList;

public class FavActivity extends AppCompatActivity {

    private ArrayList<RecipeRef> foods = new ArrayList<RecipeRef>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favs);
        getRecipesRefListFromUser();
        ListView listMatches = (ListView) findViewById(R.id.food_matches);
        if(foods == null) {
            Toast.makeText(this, ErrorMessage.MAX_REQUEST_AVAILABLE_ERROR, Toast.LENGTH_LONG).show();
        } else {
            Adapter matchAdapter = new Adapter(getApplicationContext(), foods);
            listMatches.setAdapter(matchAdapter);
            listMatches.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(getApplicationContext(), RecipeInfoActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    private void getRecipesRefListFromUser() {
        String[] fields = {DBUtils.ID_FOOD_REF};
        String[] params = {String.format("%s", User.getInstanceUser().getId())};
        Cursor cursor = SQLiteHandler.getRecipesFromUser(this,fields,params);
        if(User.getInstanceUser().getRecipeRefs().size() < cursor.getCount()){
            String ids = "";
            while(cursor.moveToNext()){
                String id = cursor.getString(0);
                ids += id+",";
            }
            foods = ServiceHandle.getInstance().getFavoritesRecipes(ids);
            User.getInstanceUser().setRecipeRefs(foods);
        }else {
            foods = User.getInstanceUser().getRecipeRefs();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent ;
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
}
