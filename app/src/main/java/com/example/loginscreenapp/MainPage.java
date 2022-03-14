package com.example.loginscreenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

public class MainPage extends AppCompatActivity {

    Intent loginIntent;
    TextView userNameText;

    String userName = "Ali";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideDeafultAppBar();
        setContentView(R.layout.activity_main_page);

        loginIntent = getIntent();
        userName = loginIntent.getStringExtra("username");
        userNameText = (TextView) findViewById(R.id.userNameText);

        userNameText.setText(userName);
        MediaPlayer ring= MediaPlayer.create(MainPage.this,R.raw.welcome);
        ring.start();
    }

    void hideDeafultAppBar()
    {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
    }

}