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
import android.os.Vibrator;


import java.util.Random;

public class Game1Activity extends AppCompatActivity implements SensorEventListener
{

    SensorManager sensorManager;
    Sensor accelerometer;
    ImageView image;
    float xValue;
    Vibrator v;
    int randomInt;
    boolean goBack = true;
    Random randomGenerator = new Random();
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
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


        Log.d("Game1Activity","accelerometer created");

    }
    public void onAccuracyChanged(Sensor sensor, int i){

    }
    public void onSensorChanged(SensorEvent sensorEvent)
    {
        //Log.d("Game1Activity","onSensorChanged X:" + sensorEvent.values[0] + " Y: " + sensorEvent.values[1] + " Z: " + sensorEvent.values[2]);
        xValue = sensorEvent.values[0];
        if (xValue > 5 && randomInt == 0 && goBack == true)
        {
            image.setImageResource(R.drawable.bear_blue);
            Log.d("Game1Activity", "IZQUIERDA");
            v.vibrate(500);
            goBack = false;
        }
        if (xValue < -5 && randomInt == 1 && goBack == true)
        {
            image.setImageResource(R.drawable.bear_blue);
            Log.d("Game1Activity", "DERECHA");
            v.vibrate(500);
            goBack = false;
        }
        if (xValue < 2 && xValue > -2 && goBack == false)
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
            xValue = sensorEvent.values[0];
            goBack = true;
        }
    }

}
