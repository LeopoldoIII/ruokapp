package com.ruokapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.ruokapp.R;
import com.ruokapp.core.db.DBUtils;
import com.ruokapp.core.db.SQLiteHandler;
import com.ruokapp.core.helper.ErrorMessage;
import com.ruokapp.core.recipe.RecipeRef;
import com.ruokapp.core.service.ServiceHandle;
import com.ruokapp.core.session.Session;
import com.ruokapp.core.user.User;

import java.util.ArrayList;

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
                        setUser();
                        setRecipesRef();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                } finally {
                    startActivity(intent);
                    finish();
                }
            }
        }, 500);

    }

    private void setUser(){
        String[] fields = {DBUtils.ID_USER, DBUtils.USER_NAME, DBUtils.USER_EMAIL};
        String[] params = {
                Session.getInstance(this).getUserSession()[0],
                Session.getInstance(this).getUserSession()[1]};
        Cursor cursor = SQLiteHandler.searchUserFromLogin(this,fields,params);
        cursor.moveToFirst();
        User.getInstanceUser()
                .setUser(cursor.getInt(0)
                        ,cursor.getString(1)
                        ,cursor.getString(2));
    }

    private void setRecipesRef() {
        String[] idRecipesRefDB = {DBUtils.ID_FOOD_REF};
        String[] userId = {String.format("%s", User.getInstanceUser().getId())};
        Cursor idsRecipesRef = SQLiteHandler.getRecipesFromUser(this,idRecipesRefDB,userId);
        String ids = "";
        while(idsRecipesRef.moveToNext()){
            String id = idsRecipesRef.getString(0);
            ids += id+",";
        }
        ArrayList<RecipeRef> recipeRefs = ServiceHandle.getInstance().getFavoritesRecipes(ids);
        if (recipeRefs == null){
            Toast.makeText(this, ErrorMessage.MAX_REQUEST_AVAILABLE_ERROR,Toast.LENGTH_LONG).show();
        } else {
            User.getInstanceUser().setRecipeRefs(recipeRefs);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
