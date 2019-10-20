package com.ruokapp.views;

import androidx.appcompat.app.AppCompatActivity;

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
import com.ruokapp.core.Session;
import com.ruokapp.core.User;
import com.ruokapp.core.db.DBUtils;
import com.ruokapp.core.db.SQLiteHandler;
import com.ruokapp.core.helper.ErrorMessage;

public class LoginActivity extends AppCompatActivity {

    EditText inputEmail;
    EditText inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputEmail      = findViewById(R.id.input_email);
        inputPassword   = findViewById(R.id.input_password);

        TextView registerOption = (TextView) findViewById(R.id.link_register);

        registerOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    public void login(View v){
        if (validateForm() && validateUserExist()) {
            Session.getInstance(this).createSession(
                    inputEmail.getText().toString(),
                    inputPassword.getText().toString());
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, ErrorMessage.LOGIN_ERROR,Toast.LENGTH_LONG).show();
        }
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

    private boolean validateForm(){
        return validateInputEmail() && validatePassword();
    }

    private boolean validateUserExist(){
        String[] fields = {DBUtils.ID_USER, DBUtils.USER_NAME, DBUtils.USER_EMAIL};
        String[] params = {
                inputEmail.getText().toString(),
                inputPassword.getText().toString()};
        Cursor cursor = SQLiteHandler.searchUserFromLogin(this,fields,params);
        if(cursor.getCount()!=0 && cursor!= null){
            cursor.moveToFirst();
            User.getInstanceUser()
                    .setUser(cursor.getInt(0)
                            ,cursor.getString(1)
                            ,cursor.getString(2));
            return true;
        }
        return false;
    }
}
