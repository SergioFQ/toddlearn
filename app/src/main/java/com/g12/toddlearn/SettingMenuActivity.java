package com.g12.toddlearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SettingMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_menu);
    }

    public void goToChangePersonalData(View view) {
        startActivity(new Intent(this,DataChangeActivity.class));
    }

    public void goToChildProgress(View view) {
        startActivity(new Intent(this,ChildProgressActivity.class));
    }

    public void goBack(View view) {
        onBackPressed();
    }
}
