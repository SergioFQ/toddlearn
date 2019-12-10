package com.g12.toddlearn;

import androidx.appcompat.app.AppCompatActivity;
import io.realm.Realm;
import io.realm.RealmResults;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.g12.toddlearn.app.UsersDB;

public class SettingMenuActivity extends AppCompatActivity {
    private TextView limitTime;
    private Realm DB;
    private UsersDB currentUser;
    private long totalTimeGame1;
    private long totalTimeGame2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_menu);
         limitTime = findViewById(R.id.limit_time_input);

         //to get the information from the past activity
         Bundle extras = getIntent().getExtras();
         long userID = extras.getLong("userID");
         totalTimeGame1 = extras.getLong("totalTimeGame1");
         totalTimeGame2 = extras.getLong("totalTimeGame2");


         DB = Realm.getDefaultInstance();

         //for testing
         Log.i("CURRENT USER ID", String.valueOf(userID));

        currentUser = DB.where(UsersDB.class).equalTo("id", userID).findFirst();

        limitTime.setText(String.valueOf(currentUser.getMaxTime()));

        //for testing
        Log.i("CURRENT USER", currentUser.toString());




    }

    public void goToChangePersonalData(View view) {
        Intent i = new Intent(this,DataChangeActivity.class);
        i.putExtra("userID", currentUser.getId());
        startActivity(i);
    }

    public void goToChildProgress(View view) {
        Intent i = new Intent(this,ChildProgressActivity.class);
        i.putExtra("userID", currentUser.getId());
        i.putExtra("totalTimeGame1", totalTimeGame1);
        i.putExtra("totalTimeGame2", totalTimeGame2);
        startActivity(i);
    }

    public void goBack(View view) {
        onBackPressed();

        //for testing
        RealmResults<UsersDB> userList = DB.where(UsersDB.class).findAll();
        for(UsersDB u: userList){
            Log.i("DB", u.toString());
        }

    }

    public void increaseTime(View view) {
        int time = Integer.parseInt(limitTime.getText().toString());
        if(time<60) {
            time = time + 5;
        }
        limitTime.setText(String.valueOf(time));

        //update the db
        final int finalTime = time;
        DB.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                currentUser.setMaxTime(finalTime);
                realm.copyToRealmOrUpdate(currentUser);
            }
        });
    }

    public void decreaseTime(View view) {
        int time = Integer.parseInt(limitTime.getText().toString());
        if(time>5){
            time=time-5;
        }

        limitTime.setText(String.valueOf(time));

        //update the db
        final int finalTime = time;
        DB.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                currentUser.setMaxTime(finalTime);
                realm.copyToRealmOrUpdate(currentUser);
            }
        });
    }
}
