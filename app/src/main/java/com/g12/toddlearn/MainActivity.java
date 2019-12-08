package com.g12.toddlearn;

import androidx.appcompat.app.AppCompatActivity;
import io.realm.Realm;
import io.realm.RealmResults;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.g12.toddlearn.app.ChildsDB;
import com.g12.toddlearn.app.UsersDB;

public class MainActivity extends AppCompatActivity {
    private Dialog myDialog; //for the pop-up
    private Realm realm;
    private TextView email;
    private TextView password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDialog = new Dialog(this);

        realm = Realm.getDefaultInstance();





    }

    public void goToSelGame(View view) {
        //Intent selection = new Intent(this,GameSelectionActivity.class);
        startActivity(new Intent(this, GameSelectionActivity.class));
    }

    public void showPopUp(View view){

        myDialog.setContentView(R.layout.login_pop_up);
        Button close_button = myDialog.findViewById(R.id.close_button);
        myDialog.show();
    }

    public void closePopUp(View view){ myDialog.dismiss(); }

    public void goToRegister(View view) {
        startActivity(new Intent(this,RegistrationActivity.class));
    }

    public void enterSettings(View view){
        final RealmResults<UsersDB> users = realm.where(UsersDB.class).findAll();
        final RealmResults<ChildsDB> childs = realm.where(ChildsDB.class).findAll();



    }
}
