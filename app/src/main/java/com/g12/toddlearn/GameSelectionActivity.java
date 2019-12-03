package com.g12.toddlearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_selection);
    }


    public void goBack(View v) {
       onBackPressed();
    }


    public void goToG1(View view) {
        startActivity(new Intent(this, Game1Activity.class));
    }

    public void goToG2(View view) {
        startActivity(new Intent(this, Game2Activity.class));
    }
}
