package com.g12.toddlearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DataChangeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_change);
    }

    public void goBack(View view) {
        onBackPressed();
    }

    public void goToChangePassword(View view) {
        startActivity(new Intent(this,NewPasswordActivity.class));
    }
}
