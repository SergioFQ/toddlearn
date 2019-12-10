package com.g12.toddlearn;

import androidx.appcompat.app.AppCompatActivity;
import io.realm.Realm;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.g12.toddlearn.app.UsersDB;

public class NewPasswordActivity extends AppCompatActivity {

    private Realm DB;
    private UsersDB currentUser;
    private TextView currentPassword;
    private TextView newPassword;
    private TextView confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);

        currentPassword = findViewById(R.id.current_password_input);
        newPassword = findViewById(R.id.new_password_input);
        confirmPassword = findViewById(R.id.confirm_new_password_input);

        Bundle extras = getIntent().getExtras();
        long userID = extras.getLong("userID");


        DB = Realm.getDefaultInstance();

        currentUser = DB.where(UsersDB.class).equalTo("id", userID).findFirst();
    }


    public void goBack(View view) {
        onBackPressed();
    }

    public void saveNewPassword(View view) {
        final String currentPasswordString = currentPassword.getText().toString();
        final String newPasswordString = newPassword.getText().toString();
        final String confirmPasswordString = confirmPassword.getText().toString();

        boolean checkCurrentPassword = currentPasswordString.equals(currentUser.getPassword());
        boolean checkNewPasswordMatches = newPasswordString.equals(confirmPasswordString);
        if(checkCurrentPassword){
            if(checkNewPasswordMatches){
                DB.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        currentUser.setPassword(newPasswordString);

                        realm.copyToRealmOrUpdate(currentUser);
                    }
                });

                Toast.makeText(getApplicationContext(), "Password changed correctly",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        " The new password doesn't match the confirmation",
                        Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(getApplicationContext(),
                    " Wrong current password",
                    Toast.LENGTH_LONG).show();
        }

    }
}
