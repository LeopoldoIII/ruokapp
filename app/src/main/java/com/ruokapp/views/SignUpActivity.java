package com.ruokapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ruokapp.R;
import com.ruokapp.core.User;
import com.ruokapp.core.db.DBUtils;
import com.ruokapp.core.db.SQLiteHandler;
import com.ruokapp.core.helper.ErrorMessage;
import com.ruokapp.core.helper.RegExHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    EditText inputUsername = null;
    EditText inputEmail = null;
    EditText inputPassword = null;
    EditText inputConfirmPass = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        inputUsername   = findViewById(R.id.input_user);
        inputEmail      = findViewById(R.id.input_email);
        inputPassword   = findViewById(R.id.input_password);
        inputConfirmPass= findViewById(R.id.input_password2);

        TextView linkLogin = findViewById(R.id.link_login);
        linkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    public void registerUser(View v){
        if (validateForm()) {
            if(insertUser()){
                User.getInstanceUser().getEmail();
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                intent.putExtra("from", "SingUp");
                startActivity(intent);
            }
        } else {
            Toast.makeText(this, ErrorMessage.GENERAL_ERROR,Toast.LENGTH_LONG).show();
        }
    }

    private boolean validateForm(){
        return validateInputUser() && validateInputEmail()
                && validatePassword() && validateConfirmPassword() && validateUserNotExist();
    }

    private boolean validateInputUser() {
        Pattern pattern = Pattern.compile(RegExHelper.REGEX_NOT_SPECIAL_CHARS);
        Matcher matcher = pattern.matcher(inputUsername.getText().toString());
        if (TextUtils.isEmpty(inputUsername.getText())) {
            inputUsername.setError(ErrorMessage.USERNAME_EMPTY_ERROR);
            return false;
        } else if(matcher.find()){
            inputUsername.setError(ErrorMessage.USERNAME_SPECIAL_CHARS_ERROR);
            return false;
        }
        return true;
    }

    private boolean validateInputEmail(){
        if (TextUtils.isEmpty(inputEmail.getText())) {
            inputEmail.setError(ErrorMessage.EMAIL_EMPTY_ERROR);
            return false;
        } else if(!Patterns.EMAIL_ADDRESS.matcher(inputEmail.getText().toString()).matches()) {
            inputEmail.setError(ErrorMessage.EMAIL_INVALID_ERROR);
            return false;
        }
        return true;
    }

    private boolean validatePassword(){
        if (TextUtils.isEmpty(inputPassword.getText())) {
            inputPassword.setError(ErrorMessage.PASS_EMPTY_ERROR);
            return false;
        } else if(inputPassword.getText().length() < 8) {
            inputPassword.setError(ErrorMessage.PASS_LENGTH_ERROR);
            return false;
        }
        return true;
    }

    private boolean validateConfirmPassword(){
        if (TextUtils.isEmpty(inputConfirmPass.getText())) {
            inputConfirmPass.setError(ErrorMessage.REPEAT_PASS_EMPTY_ERROR);
            return false;
        } else if(!inputConfirmPass.getText().toString().equals(inputPassword.getText().toString())){
            inputConfirmPass.setError(ErrorMessage.REPEAT_PASS_DOESNT_MATCH_ERROR);
            return false;
        }
        return true;
    }

    private boolean validateUserNotExist(){
        String[] fields = {DBUtils.ID_USER};
        String[] params = {inputEmail.getText().toString()};
        Cursor cursor = SQLiteHandler.searchUserByEmail(this,fields,params);
        if(cursor.getCount()!=0 && cursor!= null){
            cursor.moveToFirst();
            inputEmail.setError(ErrorMessage.EMAIL_REGISTERED_ERROR);
            return false;
        }
        return true;
    }

    protected boolean insertUser(){
        ContentValues user = new ContentValues();
        user.put(DBUtils.USER_NAME, inputUsername.getText().toString());
        user.put(DBUtils.USER_EMAIL, inputEmail.getText().toString());
        user.put(DBUtils.USER_PASSWORD, inputPassword.getText().toString());
        try {
            long id = SQLiteHandler.insertUser(this,user);
            User.getInstanceUser().setUser(id, inputUsername.getText().toString(),inputEmail.getText().toString());
            return true;
        } catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this,ErrorMessage.REGISTER_ERROR,Toast.LENGTH_LONG).show();
        }
        return false;
    }

}
