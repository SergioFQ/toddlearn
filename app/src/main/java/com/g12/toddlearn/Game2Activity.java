package com.g12.toddlearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

import static java.lang.String.valueOf;

public class Game2Activity extends AppCompatActivity {


    ImageView ImageObj1,ImageObj2,ImageObj3,ImageObj4,ImageObj5,ImageBoxBlue,ImageBoxYellow,ImageBoxRed;

     Integer[] imagesBlue={
             R.drawable.ball_blue,
             R.drawable.car_blue,
             R.drawable.bear_blue,
             R.drawable.tv_blue,
     };
    Integer[] imagesRed={
            R.drawable.ball_red,
            R.drawable.car_red,
            R.drawable.bear_red,
            R.drawable.tv_red,
    };
    Integer[] imagesYellow={
            R.drawable.ball_yellow,
            R.drawable.car_yellow,
            R.drawable.bear_yellow,
            R.drawable.tv_yellow,
    };


    Random r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);

        layoutDefinition();//connect the ImageView with the xml ImageViews

        ImageView[] objGame={
                ImageObj1,
                ImageObj2,
                ImageObj3,
                ImageObj4,
                ImageObj5
        };

        r = new Random();
        imageGeneration(objGame);
        for(int i=0;i<5;i++){
            objGame[i].setOnTouchListener(touchListener);
        }

        ImageBoxBlue.setOnDragListener(dragListener);
        ImageBoxYellow.setOnDragListener(dragListener);
        ImageBoxRed.setOnDragListener(dragListener);
    }

    View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            ClipData data = ClipData.newPlainText("","");
            View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(data,myShadowBuilder,v,0);
            v.setVisibility(View.INVISIBLE);
            return true;
        }
    };

    View.OnDragListener dragListener = new View.OnDragListener(){
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int dragEvent = event.getAction();
            final View dragObject = (View) event.getLocalState();
            switch (dragEvent) {
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:

                    if((v.getId()==ImageBoxBlue.getId()) &&((int)dragObject.getTag()==0)){
                        Toast.makeText(Game2Activity.this, "Correcto! Azul!", Toast.LENGTH_SHORT).show();
                    }else if((v.getId()==ImageBoxYellow.getId())&&((int)dragObject.getTag()==1)){
                        Toast.makeText(Game2Activity.this, "Correcto! Amarillo!", Toast.LENGTH_SHORT).show();
                    }else if((v.getId()==ImageBoxRed.getId())&&((int)dragObject.getTag()==2)){
                        Toast.makeText(Game2Activity.this, "Correcto! Rojo!", Toast.LENGTH_SHORT).show();
                    }

                    break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        dragObject.setVisibility(View.VISIBLE);
            }
            return true;
        }
    };

    public void layoutDefinition(){
        ImageObj1 = (ImageView) findViewById(R.id.dragObject1);
        ImageObj2 = (ImageView) findViewById(R.id.dragObject2);
        ImageObj3 = (ImageView) findViewById(R.id.dragObject3);
        ImageObj4 = (ImageView) findViewById(R.id.dragObject4);
        ImageObj5 = (ImageView) findViewById(R.id.dragObject5);

        ImageBoxBlue = (ImageView) findViewById(R.id.imageView_blueBox);
        ImageBoxYellow = (ImageView) findViewById(R.id.imageView_yellowBox);
        ImageBoxRed = (ImageView) findViewById(R.id.imageView_redBox);
    }

    public void imageGeneration(ImageView[] objGame){
        int auxRandom=0;
        for(int i=0;i<5;i++){

            auxRandom=(int)(Math.random()*3);

            switch (auxRandom){
                case 0: //blue
                    objGame[i].setImageResource(imagesBlue[r.nextInt(imagesBlue.length)]);
                    break;
                case 1: //yellow
                    objGame[i].setImageResource(imagesYellow[r.nextInt(imagesYellow.length)]);
                    break;
                case 2: //red
                    objGame[i].setImageResource(imagesRed[r.nextInt(imagesRed.length)]);
                    break;
            }
            objGame[i].setTag(auxRandom);
        }
    }
}
