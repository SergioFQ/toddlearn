package com.g12.toddlearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SettingMenuActivity extends AppCompatActivity {
    TextView limitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_menu);
         limitTime = findViewById(R.id.limit_time_input);
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

    public void increaseTime(View view) {
        int time = Integer.parseInt(limitTime.getText().toString());
        if(time<60) {
            time = time + 5;
        }
        limitTime.setText(String.valueOf(time));
    }

    public void decreaseTime(View view) {
        int time = Integer.parseInt(limitTime.getText().toString());
        if(time>5){
            time=time-5;
        }
        limitTime.setText(String.valueOf(time));
    }
}
