package com.g12.toddlearn;

import androidx.appcompat.app.AppCompatActivity;
import io.realm.Realm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.g12.toddlearn.app.ChildsDB;
import com.g12.toddlearn.app.UsersDB;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataChangeActivity extends AppCompatActivity {
    private Realm DB;
    private UsersDB currentUser;
    private ChildsDB currentChild;
    private EditText email;
    private EditText childName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_change);

        email = findViewById(R.id.email_change_input);
        childName = findViewById(R.id.childname_change_input);

        Bundle extras = getIntent().getExtras();
        long userID = extras.getLong("userID");


        DB = Realm.getDefaultInstance();

        currentUser = DB.where(UsersDB.class).equalTo("id", userID).findFirst();
        currentChild = currentUser.getChild();

        email.setText(currentUser.getEmail());
        childName.setText(currentUser.getChild().getName());
    }

    private boolean emailsEquals(String email1,String email2) {
        Pattern address = Pattern.compile("([\\w\\.]+)@([\\w\\.]+\\.\\w+)");
        Matcher match1 = address.matcher(email1);
        Matcher match2 = address.matcher(email2);
        if (!match1.find() || !match2.find()) return false; //Not an e-mail address? Already false
        if (!match1.group(2).equalsIgnoreCase(match2.group(2)))
            return false; //Not same serve? Already false
        switch (match1.group(2).toLowerCase()) {
            case "gmail.com":
                String gmail1 = match1.group(1).replace(".", "");
                String gmail2 = match2.group(1).replace(".", "");
                return gmail1.equalsIgnoreCase(gmail2);
            default:
                return match1.group(1).equalsIgnoreCase(match2.group(1));
        }
    }

    public void goBack(View view) {
        onBackPressed();
    }

    public void goToChangePassword(View view) {
        Intent i = new Intent(this,NewPasswordActivity.class);
        i.putExtra("userID", currentUser.getId());
        startActivity(i);
    }

    public void setChanges(View view) {
        final String newEmail = email.getText().toString();
        final String newChildName = childName.getText().toString();
        if(!emailsEquals(currentUser.getEmail(), newEmail)){
            changeEmail(currentUser, newEmail);
            Toast.makeText(getApplicationContext(), "Email changed correctly",
                    Toast.LENGTH_SHORT).show();
        }

        boolean sameChild = currentUser.getChild().getName().equals(newChildName);
        Log.i("TEST", String.valueOf(sameChild));
        if(!sameChild){
            changeChildName(currentUser, newChildName);
            Toast.makeText(getApplicationContext(), "Child name changed correctly",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void changeChildName(final UsersDB currentUser, final String newChildName) {
        DB.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                currentChild.setName(newChildName);
                realm.copyToRealmOrUpdate(currentChild);


            }
        });
    }

    private void changeEmail(final UsersDB currentUser, final String newEmail) {
        DB.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                currentUser.setEmail(newEmail);
                realm.copyToRealmOrUpdate(currentUser);
            }
        });
    }
}
