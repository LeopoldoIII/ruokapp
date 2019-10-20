package com.ruokapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.ruokapp.R;
import com.ruokapp.core.Session;

public class WelcomeActivity extends AppCompatActivity {

    private Handler hold = new Handler();
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        hold.postDelayed(new Runnable() {
            @Override
            public void run() {
                intent = new Intent(getApplicationContext(),LoginActivity.class);
                try {
                    if(Session.getInstance(getApplicationContext()).getUserSession() != null){
                        intent = new Intent(getApplicationContext(), HomeActivity.class);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                } finally {
                    startActivity(intent);
                    finish();
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
