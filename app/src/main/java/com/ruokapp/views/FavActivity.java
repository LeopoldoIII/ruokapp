package com.ruokapp.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ruokapp.R;
import com.ruokapp.core.RecipeRef;
import com.ruokapp.core.Session;
import com.ruokapp.views.adapter.Adapter;

import java.util.ArrayList;

public class FavActivity extends AppCompatActivity {

    private ArrayList<RecipeRef> foods = new ArrayList<RecipeRef>();
    private Adapter matchsAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favs);
        ListView listMatches = (ListView) findViewById(R.id.food_matches);
        foods.add(new RecipeRef(1234,"Sopa de Calabaza1", "urlToImg","20'"));
        foods.add(new RecipeRef(4321,"Chori Pan", "urlToImg","10'"));
        matchsAdapter = new Adapter(getApplicationContext(), foods);
        listMatches.setAdapter(matchsAdapter);

        listMatches.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), FoodActivity.class);
                startActivity(intent);
            }
        });

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
                intent = new Intent(this, SettingsActivity.class);
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
