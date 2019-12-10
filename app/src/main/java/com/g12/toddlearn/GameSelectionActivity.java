package com.g12.toddlearn;

import androidx.appcompat.app.AppCompatActivity;
import io.realm.Realm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.g12.toddlearn.app.UsersDB;

public class GameSelectionActivity extends AppCompatActivity {
    private UsersDB currentUser;
    private Realm DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_selection);

        Bundle extras = getIntent().getExtras();
        long userID = extras.getLong("userID");

        DB = Realm.getDefaultInstance();

        currentUser = DB.where(UsersDB.class).equalTo("id", userID).findFirst();

    }


    public void goBack(View v) {
       onBackPressed();
    }


    public void goToG1(View view) {
        startActivity(new Intent(this, Game1Activity.class));
    }

    public void goToG2(View view) {
        Intent intent = new Intent(this, Game2Activity.class);
        intent.putExtra("userID", currentUser.getId());
        startActivity(intent);
    }
}
