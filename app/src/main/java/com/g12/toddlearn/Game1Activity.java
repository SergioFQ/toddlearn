package com.g12.toddlearn;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Random;

public class Game1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1);
        ImageView image = (ImageView) findViewById(R.id.signal_left);
        //while (tiempo de juego < currentTime)
        //{
        while (true)
        {
            Random randomGenerator = new Random();
            int select = randomGenerator.nextInt(2);

            if (select == 0) //izq
            {
                image.setImageResource(R.drawable.signal_left);
                try {
                    //set time in mili
                    Thread.sleep(3000);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if (select == 1) //der
            {
                image.setImageResource(R.drawable.signal_right);
                try {
                    //set time in mili
                    Thread.sleep(3000);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
