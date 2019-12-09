package com.g12.toddlearn;

import androidx.appcompat.app.AppCompatActivity;
import io.realm.Realm;
import io.realm.RealmResults;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.g12.toddlearn.app.ChildsDB;
import com.g12.toddlearn.app.UsersDB;

public class RegistrationActivity extends AppCompatActivity {

    private EditText email;
    private EditText childName;
    private EditText password;
    private EditText confirmPassword;
    private CheckBox agreeTerms;
    private Realm DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        email = findViewById(R.id.email_input);
        childName = findViewById(R.id.childname_input);
        password = findViewById(R.id.password_input);
        confirmPassword = findViewById(R.id.confirm_pass_input);
        agreeTerms = findViewById(R.id.agree_terms_checkbox);

        DB = Realm.getDefaultInstance();


    }


    public void saveRegistration(View view) {
        final String emailString = email.getText().toString();
        final String childNameString = childName.getText().toString();
        final String passwordString = password.getText().toString();
        final String confirmPasswordString = confirmPassword.getText().toString();
        if (!emailString.isEmpty() && !childNameString.isEmpty() && !passwordString.isEmpty()
                && !confirmPasswordString.isEmpty()) {
            if (passwordString.equals(confirmPasswordString)) {
                if (agreeTerms.isChecked()) {

                    DB.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                                UsersDB newUser = new UsersDB(emailString, passwordString);
                                ChildsDB newChild = new ChildsDB(childNameString);
                                newUser.setChild(newChild);

                                realm.copyToRealm(newUser);
                                realm.copyToRealm(newChild);

                        }
                    });

                    Toast.makeText(getApplicationContext(), "success registration",
                            Toast.LENGTH_SHORT).show();


                    startActivity(new Intent(this, MainActivity.class));


                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please, agree the terms", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Password confirmation doesn't match password",
                        Toast.LENGTH_SHORT).show();
                }

        } else {
            Toast.makeText(getApplicationContext(),
                    "Please, fill al the gaps", Toast.LENGTH_SHORT).show();
        }
    }

}
