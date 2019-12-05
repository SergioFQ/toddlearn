package com.g12.toddlearn;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Dialog myDialog; //for the pop-up
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDialog = new Dialog(this);
    }

    public void goToSelGame(View view) {
        //Intent selection = new Intent(this,GameSelectionActivity.class);
        startActivity(new Intent(this, GameSelectionActivity.class));
    }

    public void showPopUp(View view){

        myDialog.setContentView(R.layout.login_pop_up);
        Button close_button = myDialog.findViewById(R.id.close_button);
        myDialog.show();
    }

    public void closePopUp(View view){ myDialog.dismiss(); }

}
