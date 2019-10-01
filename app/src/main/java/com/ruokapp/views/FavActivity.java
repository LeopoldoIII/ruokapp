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
import com.ruokapp.core.Food;
import com.ruokapp.views.adapter.Adapter;

import java.util.ArrayList;

public class FavActivity extends AppCompatActivity {

    private ArrayList<Food> foods = new ArrayList<Food>();
    private Adapter matchsAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favs);
        ListView listMatches = (ListView) findViewById(R.id.food_matches);
        foods.add(new Food("Sopa de Calabaza1", "20'"));
        foods.add(new Food("Sopa de Calabaza2", "30'"));
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
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.action_settings:
                intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
            default:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
