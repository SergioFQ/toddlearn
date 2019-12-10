package com.g12.toddlearn;

import androidx.appcompat.app.AppCompatActivity;
import io.realm.Realm;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.creativityapps.gmailbackgroundlibrary.BackgroundMail;
import com.g12.toddlearn.app.ChildsDB;
import com.g12.toddlearn.app.UsersDB;
import com.github.mikephil.charting.charts.BarChart;

public class ChildProgressActivity extends AppCompatActivity {
    private Realm DB;
    private UsersDB currentUser;
    private ChildsDB currentChild;
    private TextView childName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_progress);

        Bundle extras = getIntent().getExtras();
        long userID = extras.getLong("userID");

        childName = findViewById(R.id.childNameTextView);
        BarChart chartTotalTime = findViewById(R.id.barChar_playingTime);
        BarChart chartGame1Time = findViewById(R.id.barChar_game1);
        BarChart chartGame2Time = findViewById(R.id.barChar_game2);


        DB = Realm.getDefaultInstance();

        currentUser = DB.where(UsersDB.class).equalTo("id", userID).findFirst();
        currentChild = currentUser.getChild();

        childName.setText(currentChild.getName());



        //for testing
        Log.i("CURRENT USER", currentUser.toString());

    }

    public void goBack(View view) {
        onBackPressed();
    }

    public void sendReport(View view) {
    try {
        BackgroundMail.newBuilder(this)
                .withUsername("todd.learning.7@gmail.com")

                .withPassword("toddlearn1234")
                .withMailto(currentUser.getEmail())
                .withType(BackgroundMail.TYPE_HTML)
                .withSubject("this is the subject")
                .withBody("<h1>Report of your Child</h1><p>a message<p/>")
                .withOnSuccessCallback(new BackgroundMail.OnSuccessCallback() {public void onSuccess() { }}).send();
    }
    catch (Exception e)
    {
        System.out.println(e.getMessage());
        Toast.makeText(this, "Error sending the email", Toast.LENGTH_SHORT);
    }
    }
}
