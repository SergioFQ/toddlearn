package com.g12.toddlearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToSelGame(View view) {
        //Intent selection = new Intent(this,GameSelectionActivity.class);
        startActivity(new Intent(this, GameSelectionActivity.class));
    }
}
