package com.example.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class Login extends AppCompatActivity {
    EditText e1,e2;
    Button loginbtn;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])"+         // at least 1 lower case character
                    "(?=.*[a-z])"+         // at least 1 int number character
                    "(?=.*[A-Z])"+         // at least 1 upper case character
                    "(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{4,}" +                // at least 4 characters
                    "$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar( ).setTitle("Login Screen");
//        getSupportActionBar( ).setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar( ).setDisplayShowHomeEnabled(true);
        e1=findViewById(R.id.etEmailLogin);
        e2=findViewById(R.id.etpasswordLogin);
        loginbtn=findViewById(R.id.btnLogin);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loginValidate( ) != 9) {
                    Toast.makeText(Login.this, "Please Valid Email or password", Toast.LENGTH_SHORT).show( );
                } else {

                    startActivity(new Intent(Login.this, MainActivity.class));
                }
            }
        });

    }

    private int loginValidate() {

        // Extract input from EditText
        String emailInput = e1.getText().toString().trim();
        String passwordInput = e2.getText().toString().trim();

        // if the email input field is empty
        if (TextUtils.isEmpty(emailInput)) {
            e1.setError("Field can not be empty");
            return 1;
        }

        // Matching the input email to a predefined email pattern
         if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            e1.setError("Please enter a valid email address");
            return 2;
        }
        //for password..................
         if (TextUtils.isEmpty(passwordInput)) {
            e2.setError("Field can not be empty");
            return 6;
       }
        if (passwordInput.length()<6)
        {
            e2.requestFocus();
            e2.setError("Enter minimum 6 character");
            return 7;
        }
        if (passwordInput.length()>15)
        {
            e2.requestFocus();
            e2.setError("Enter maximum 15 character");
            return 3;
        }
         if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            e2.setError("Password is too weak");
            return 8;
        }
        else {

            return 9 ;
        }
    }

}