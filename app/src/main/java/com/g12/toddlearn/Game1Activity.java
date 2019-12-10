package com.g12.toddlearn;

import androidx.appcompat.app.AppCompatActivity;
import io.realm.Realm;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateUtils;
import android.util.Log;
import android.widget.ImageView;
import android.os.Vibrator;


import com.g12.toddlearn.app.ChildsDB;
import com.g12.toddlearn.app.UsersDB;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Game1Activity extends AppCompatActivity implements SensorEventListener
{

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private ImageView image;
    private float xValue;
    private int randomInt = 0;
    private boolean goBack = true;
    private Random randomGenerator = new Random();
    private boolean firstTime = true;
    private Realm DB;
    private UsersDB currentUser;
    private ChildsDB currentChild;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1);
        image = (ImageView) findViewById(R.id.signal_left);
        Log.d("Game1Activity","accelerometer start");
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(Game1Activity.this, accelerometer, 3000);


        Log.d("Game1Activity","accelerometer created");

        Bundle extras = getIntent().getExtras();
        long userID = extras.getLong("userID");

        DB = Realm.getDefaultInstance();

        currentUser = DB.where(UsersDB.class).equalTo("id", userID).findFirst();
        currentChild = currentUser.getChild();


    }

    @Override
    protected void onStart() {
        super.onStart();

        timer = new Timer();
        final long startTime = System.currentTimeMillis();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                long finalTime = System.currentTimeMillis();
                final long totalTime = startTime - finalTime;
                /*DB.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        currentChild.setTimeGame2(totalTime);

                        realm.copyToRealmOrUpdate(currentChild);
                    }
                });*/
                Intent i = new Intent (Game1Activity.this, MainActivity.class);
                i.putExtra("totalTimeGame1", totalTime);
                startActivity(i);
                finish();
            }
        },/*currentUser.getMaxTime()*60000*/ 30000);
    }


    public void onAccuracyChanged(Sensor sensor, int i){

    }
    public void onSensorChanged(SensorEvent sensorEvent)
    {
        //Log.d("Game1Activity","onSensorChanged X:" + sensorEvent.values[0] + " Y: " + sensorEvent.values[1] + " Z: " + sensorEvent.values[2]);
        xValue = sensorEvent.values[0];
        if (xValue > 5 && randomInt == 0 && goBack == true)
        {
            image.setImageResource(R.drawable.todd_wee);
            Log.d("Game1Activity", "IZQUIERDA");
            goBack = false;
            firstTime = false;
        }
        if (xValue < -5 && randomInt == 1 && goBack == true)
        {
            image.setImageResource(R.drawable.todd_wee);
            Log.d("Game1Activity", "DERECHA");
            goBack = false;
            firstTime = false;
        }
        if (xValue < 2 && xValue > -2 && goBack == false)
        {
            if (firstTime == false)
            {
                randomInt = randomGenerator.nextInt(2);
                if (randomInt == 0) //izq
                {
                    Log.d("Game1Activity", "flecha izq");
                    image.setImageResource(R.drawable.signal_left);
                }
                if (randomInt == 1) //der
                {
                    Log.d("Game1Activity", "flecha der");
                    image.setImageResource(R.drawable.signal_right);
                }
                goBack = true;
            }
        }
    }

}
