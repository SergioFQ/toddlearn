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
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ChildProgressActivity extends AppCompatActivity {
    private Realm DB;
    private UsersDB currentUser;
    private ChildsDB currentChild;
    private TextView childName;
    private long totalTimeGame1;
    private long totalTimeGame2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_progress);

        Bundle extras = getIntent().getExtras();
        long userID = extras.getLong("userID");
        totalTimeGame1 = extras.getLong("totalTimeGame1")/60000;
        totalTimeGame2 = extras.getLong("totalTimeGame2")/60000;

        childName = findViewById(R.id.childNameTextView);
        BarChart chartTotalTime = findViewById(R.id.barChar_playingTime);
        BarChart chartGame1Time = findViewById(R.id.barChar_game1);
        BarChart chartGame2Time = findViewById(R.id.barChar_game2);


        DB = Realm.getDefaultInstance();

        currentUser = DB.where(UsersDB.class).equalTo("id", userID).findFirst();
        currentChild = currentUser.getChild();

        childName.setText(currentChild.getName());

        createChart(chartTotalTime,chartGame1Time,chartGame2Time);



        //for testing
        Log.i("CURRENT USER", currentUser.toString());

    }

    private void createChart(BarChart chartTotalTime, BarChart chartGame1Time,
                             BarChart chartGame2Time) {
        chartTotalTime.setDrawBarShadow(true);
        chartGame1Time.setDrawBarShadow(true);
        chartGame2Time.setDrawBarShadow(true);

        chartTotalTime.setDrawValueAboveBar(true);
        chartGame1Time.setDrawValueAboveBar(true);
        chartGame2Time.setDrawValueAboveBar(true);

        chartTotalTime.setMaxVisibleValueCount(currentUser.getMaxTime());
        chartGame1Time.setMaxVisibleValueCount(currentUser.getMaxTime()+currentUser.getMaxTime());
        chartGame2Time.setMaxVisibleValueCount(currentUser.getMaxTime());

        chartTotalTime.setPinchZoom(true);
        chartGame1Time.setPinchZoom(true);
        chartGame2Time.setPinchZoom(true);

        chartTotalTime.setDrawGridBackground(true);
        chartGame1Time.setDrawGridBackground(true);
        chartGame2Time.setDrawGridBackground(true);

        enterDataChar1(chartTotalTime);
        enterDataChar2(chartGame1Time);
        enterDataChar3(chartGame2Time);


    }

    private void enterDataChar3(BarChart chartGame2Time) {
        ArrayList<BarEntry> barEntries = new ArrayList<>();

        barEntries.add(new BarEntry(1, currentChild.getTimeGame2()));
        barEntries.add(new BarEntry(2, totalTimeGame2));
        barEntries.add(new BarEntry(3, 15));
        barEntries.add(new BarEntry(4, 20));
        barEntries.add(new BarEntry(5, 5));

        BarDataSet dataSet = new BarDataSet(barEntries, "game 2 times");
        dataSet.setColors(ColorTemplate.LIBERTY_COLORS);

        BarData data = new BarData(dataSet);

        chartGame2Time.setData(data);
    }

    private void enterDataChar2(BarChart chartGame1Time) {
        ArrayList<BarEntry> barEntries = new ArrayList<>();

        barEntries.add(new BarEntry(1, currentChild.getTimeGame1()));
        barEntries.add(new BarEntry(2, totalTimeGame1));
        barEntries.add(new BarEntry(3, 15));
        barEntries.add(new BarEntry(4, 10));
        barEntries.add(new BarEntry(5, 5));

        BarDataSet dataSet = new BarDataSet(barEntries, "game 1 times");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        BarData data = new BarData(dataSet);

        chartGame1Time.setData(data);
    }

    private void enterDataChar1(BarChart chartTotalTime) {
        ArrayList<BarEntry> barEntries = new ArrayList<>();

        barEntries.add(new BarEntry(1, currentChild.getTimeBoth()));
        barEntries.add(new BarEntry(2, 30));
        barEntries.add(new BarEntry(3, 40));
        barEntries.add(new BarEntry(4, 50));

        BarDataSet dataSet = new BarDataSet(barEntries, "times");
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        BarData data = new BarData(dataSet);

        chartTotalTime.setData(data);


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
                .withSubject("Report about your child progress")
                .withBody("<h1>"+currentChild.getName()+"'s Report</h1><p><i>current progress</i><p/>"+
                        "<p><b>- Total time played: <b/>"+
                        String.valueOf(totalTimeGame1+totalTimeGame2)+"<p/>"+
                        "<p><b>- Total time played game 1: <b/>"
                        + String.valueOf(totalTimeGame1)+"<p/>"+
                        "<p><b>- Total time played game2: <b/>"+String.valueOf(totalTimeGame2)+"<p/>" +
                        "<a href=\"https://ibb.co/kBKpCHk\"><img src=\"https://i.ibb.co/bm1x9Wq/logo-app.png\" " +
                        "alt=\"logo-app\" border=\"0\"></a>")
                .withOnSuccessCallback(new BackgroundMail.OnSuccessCallback() {public void onSuccess() { }}).send();
    }
    catch (Exception e)
    {
        System.out.println(e.getMessage());
        Toast.makeText(this, "Error sending the email", Toast.LENGTH_SHORT);
    }
    }
}
