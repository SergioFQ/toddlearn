package com.g12.toddlearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;


import java.util.Random;

public class Game1Activity extends AppCompatActivity implements SensorEventListener
{

    SensorManager sensorManager;
    Sensor accelerometer;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1);
        image = (ImageView) findViewById(R.id.arrow);
        Log.d("Game1Activity","accelerometer start");
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(Game1Activity.this, accelerometer, 3000);
        Log.d("Game1Activity","accelerometer created");

        new Handler().postDelayed(new Runnable() {
            boolean stop = false;
            Random randomGenerator = new Random();
            int randomInt = randomGenerator.nextInt(2);
            public void run()
            {
                Log.d("Game1Activity", "run");
                randomInt = randomGenerator.nextInt(2);
                if (randomInt == 0) //izq
                {
                    Log.d("Game1Activity", "izq");
                    image.setImageResource(R.drawable.signal_left);
                }
                if (randomInt == 1) //der
                {
                    Log.d("Game1Activity", "der");
                    image.setImageResource(R.drawable.signal_right);
                }
                new Handler().postDelayed(this, 3000);
            }
        }, 3000);
    }
    public void onAccuracyChanged(Sensor sensor, int i){

    }
    public void onSensorChanged(SensorEvent sensorEvent)
    {
//        Log.d("Game1Activity","onSensorChanged X:" + sensorEvent.values[0] + " Y: " + sensorEvent.values[1] + " Z: " + sensorEvent.values[2]);
//        final Handler handler = new Handler();
//        int updateInterval = 3000;
//
//        handler.postDelayed( new Runnable()
//        {
//
//            boolean stop = false;
//            Random randomGenerator = new Random();
//            int randomInt = randomGenerator.nextInt(2);
//
//            public void stop() {
//                this.stop = true;
//            }
//
//            public void run()
//            {
//                Log.d("Game1Activity", "run");
//                if (randomInt == 0) //izq
//                {
//                    image.setImageResource(R.drawable.signal_left);
//                }
//                if (randomInt == 1) //der
//                {
//                    image.setImageResource(R.drawable.signal_right);
//                }
//                // Any code which goes here will be executed every 'updateInterval'
//                // change your background here
//                //handler.postDelayed(this, 30000);
//            }
//        }, updateInterval);

    }

}
