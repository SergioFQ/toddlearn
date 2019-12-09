package com.g12.toddlearn;

import androidx.appcompat.app.AppCompatActivity;
import io.realm.Realm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.g12.toddlearn.app.ChildsDB;
import com.g12.toddlearn.app.UsersDB;

public class GameSelectionActivity extends AppCompatActivity {
    private ChildsDB currentChild;
    private Realm DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_selection);

        Bundle extras = getIntent().getExtras();
        long childID = extras.getLong("childID");

        DB = Realm.getDefaultInstance();

        currentChild = DB.where(ChildsDB.class).equalTo("id", childID).findFirst();

        //testing
        //Log.i("OUR CURRENT CHILD", currentChild.toString());
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
