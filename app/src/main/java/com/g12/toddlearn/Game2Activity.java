package com.g12.toddlearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.g12.toddlearn.app.ChildsDB;
import com.g12.toddlearn.app.UsersDB;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import io.realm.Realm;

import static java.lang.String.valueOf;

public class Game2Activity extends AppCompatActivity {
    private Realm DB;
    private ChildsDB currentChild;
    private UsersDB currentUser;


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
    RelativeLayout gameZone;
    ImageView[] objGame;

    int contDone;
    View objUsing;
    Timer timer;
    Random r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);

        Bundle extras = getIntent().getExtras();
        long userID = extras.getLong("userID");

        DB = Realm.getDefaultInstance();

        currentUser = DB.where(UsersDB.class).equalTo("id", userID).findFirst();
        currentChild = currentUser.getChild();

        layoutDefinition();//connect the ImageView with the xml ImageViews

        objGame= new ImageView[]{
                ImageObj1,
                ImageObj2,
                ImageObj3,
                ImageObj4,
                ImageObj5
        };
        gameZone = (RelativeLayout) findViewById(R.id.layoutGame2);
        contDone=0;
        r = new Random();
        startGame();
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
                Intent i = new Intent (Game2Activity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        },currentUser.getMaxTime()*60000);
    }

    private void startGame() {
        contDone=0;
        imageGeneration(objGame);
        for(int i=0;i<5;i++){
            objGame[i].setOnTouchListener(touchListener);
        }
        ImageBoxBlue.setOnDragListener(dragListener);
        ImageBoxYellow.setOnDragListener(dragListener);
        ImageBoxRed.setOnDragListener(dragListener);

        gameZone.setOnDragListener(dragListener);
    }

    private void checkStateGame(){
        if(contDone>=5){
            startGame();
        }
    }

    View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            ClipData data = ClipData.newPlainText("","");
            View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(data,myShadowBuilder,v,0);
            objUsing=v;
            objUsing.setVisibility(View.GONE);
            return true;
        }
    };

    View.OnDragListener dragListener = new View.OnDragListener(){
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int dragEvent = event.getAction();
            final View dragObject = (View) event.getLocalState();

            switch (dragEvent) {
                case DragEvent.ACTION_DRAG_STARTED:

                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:

                    if((v.getId()==ImageBoxBlue.getId()) &&((int)objUsing.getTag()==0)){
                        objUsing.setVisibility(View.GONE);
                        contDone++;
                    }else if((v.getId()==ImageBoxYellow.getId())&&((int)objUsing.getTag()==1)){
                        contDone++;
                        objUsing.setVisibility(View.GONE);

                    }else if((v.getId()==ImageBoxRed.getId())&&((int)objUsing.getTag()==2)){
                        contDone++;
                        objUsing.setVisibility(View.GONE);
                    }else{
                        objUsing.setVisibility(View.VISIBLE);
                    }

                    break;

            }

            checkStateGame();
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
            objGame[i].setVisibility(View.VISIBLE);
        }
    }
}
