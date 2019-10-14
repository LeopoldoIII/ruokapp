package com.ruokapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.ruokapp.R;
import com.ruokapp.core.db.SQLiteHandler;

public class WelcomeActivity extends AppCompatActivity {

    private Handler hold = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        try {
            SQLiteHandler.createConnection(this);
        } catch (Exception e){
            e.printStackTrace();
            finish();
        }

        hold.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, 2000);

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
