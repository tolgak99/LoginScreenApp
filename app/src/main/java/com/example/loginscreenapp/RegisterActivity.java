package com.example.loginscreenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    boolean mailValid;
    boolean passValid;
    boolean passConfirmed;

    EditText mailInput;
    EditText passInput;
    EditText rePassInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideDeafultAppBar();
        setContentView(R.layout.activity_register);

        mailInput = (EditText) findViewById(R.id.registerUserNameInput);
        passInput = (EditText) findViewById(R.id.registerPasswordInput);
        rePassInput = (EditText) findViewById(R.id.rePasswordInput);

        Button registerButton = (Button) findViewById(R.id.registerButton);
        TextView txtRegister = (TextView) findViewById(R.id.loginAccountText);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passValid && mailValid && passConfirmed) {
                    Intent toMainIntent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(toMainIntent);
                }
            }
        });

        mailInput.addTextChangedListener(new TextValidator(mailInput) {
            @Override public void validate(TextView textView, String text) {
                mailValid = Validation.mailValidation(text);

                if (!mailValid)
                {
                    mailInput.setTextColor(Color.parseColor("Red"));
                    mailInput.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_action_user, 0, R.drawable.ic_action_error, 0);
                }
                else
                {
                    mailInput.setTextColor(Color.parseColor("White"));
                    mailInput.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_action_user, 0, 0, 0);
                }
            }
        });

        passInput.addTextChangedListener(new TextValidator(passInput) {
            @Override public void validate(TextView textView, String text) {
                passValid =  Validation.passwordValidation(text);

                if (!passValid)
                {
                    passInput.setTextColor(Color.parseColor("Red"));
                    passInput.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_action_pass, 0, R.drawable.ic_action_error, 0);
                }
                else
                {
                    passInput.setTextColor(Color.parseColor("White"));
                    passInput.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_action_pass, 0, 0, 0);
                }
            }
        });

        rePassInput.addTextChangedListener(new TextValidator(rePassInput) {
            @Override public void validate(TextView textView, String text) {
                passConfirmed =  Validation.passwordConfirmation(text,passInput.getText().toString());

                if (!passConfirmed)
                {
                    rePassInput.setTextColor(Color.parseColor("Red"));
                    rePassInput.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_action_pass, 0, R.drawable.ic_action_error, 0);
                }
                else
                {
                    rePassInput.setTextColor(Color.parseColor("White"));
                    rePassInput.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_action_pass, 0, 0, 0);
                }
            }
        });

        txtRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent toRegisterIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(toRegisterIntent);
            }
        });
    }

    void hideDeafultAppBar()
    {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
    }

}