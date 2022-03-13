package com.example.loginscreenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
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

        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (passValid && mailValid)
               {
                   // Direct Page
               }
            }
        });

        TextView txtRegister = (TextView) findViewById(R.id.createAccountText);
        txtRegister .setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Register Page
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