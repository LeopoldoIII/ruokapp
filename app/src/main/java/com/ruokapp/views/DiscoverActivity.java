package com.ruokapp.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.ruokapp.R;
import com.ruokapp.core.Session;

public class DiscoverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);

        ImageView like = (ImageView) findViewById(R.id.icon_like);
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Added to Matches list", Toast.LENGTH_LONG).show();
            }
        });
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
                intent = new Intent(this,SettingsActivity.class);
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
