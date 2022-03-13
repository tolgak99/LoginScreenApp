package com.example.loginscreenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    boolean mailValid;
    boolean passValid;

    EditText userNameInput;
    EditText passInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideDeafultAppBar();
        setContentView(R.layout.activity_main);

        userNameInput = (EditText) findViewById(R.id.userNameInput);
        passInput = (EditText) findViewById(R.id.passwordInput);

        Button loginButton = (Button) findViewById(R.id.loginButton);
        TextView txtRegister = (TextView) findViewById(R.id.createAccountText);

        ImageView instaButton = (ImageView) findViewById(R.id.instaImage);
        ImageView faceButton = (ImageView) findViewById(R.id.faceImage);
        ImageView googleButton = (ImageView) findViewById(R.id.googleImage);

        userNameInput.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_action_user, 0, 0, 0);
        passInput.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_action_pass, 0, 0, 0);

        userNameInput.addTextChangedListener(new TextValidator(userNameInput) {
            @Override public void validate(TextView textView, String text) {
               mailValid = isMailValid(text);

               if (!mailValid)
               {
                   userNameInput.setTextColor(Color.parseColor("Red"));
                   userNameInput.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_action_user, 0, R.drawable.ic_action_error, 0);
               }
               else
               {
                   userNameInput.setTextColor(Color.parseColor("White"));
                   userNameInput.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_action_pass, 0, 0, 0);
               }
            }
        });

        passInput.addTextChangedListener(new TextValidator(passInput) {
            @Override public void validate(TextView textView, String text) {
                passValid = isPasswordValid(text);

                if (!passValid)
                {
                    passInput.setTextColor(Color.parseColor("Red"));
                    passInput.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_action_user, 0, R.drawable.ic_action_error, 0);
                }
                else
                {
                    passInput.setTextColor(Color.parseColor("White"));
                    passInput.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_action_pass, 0, 0, 0);
                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (passValid && mailValid)
               {
                   // Direct Page
               }
            }
        });

        txtRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Register Page
            }
        });

        faceButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Uri uri = Uri.parse("http://www.facebook.com");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        instaButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Uri uri = Uri.parse("http://www.instagram.com");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        googleButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Uri uri = Uri.parse("http://www.google.com");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

    }

    void hideDeafultAppBar()
    {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
    }

    boolean isMailValid(String mail)
    {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches();
    }

    boolean isPasswordValid(String pass)
    {
        Matcher matcher = Pattern.compile("((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{4,20})").matcher(pass);
        return matcher.matches();
    }
}