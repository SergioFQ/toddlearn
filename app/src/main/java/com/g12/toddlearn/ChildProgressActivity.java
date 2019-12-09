package com.g12.toddlearn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.creativityapps.gmailbackgroundlibrary.BackgroundMail;

public class ChildProgressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_progress);
    }

    public void goBack(View view) {
        onBackPressed();
    }

    public void sendReport(View view) {
    try {
        BackgroundMail.newBuilder(this)
                .withUsername("todd.learning.7@gmail.com")

                .withPassword("toddlearn1234")
                .withMailto("todd.learning.7@gmail.com")
                .withType(BackgroundMail.TYPE_PLAIN)
                .withSubject("this is the subject")
                .withBody("this is the body")
                .withOnSuccessCallback(new BackgroundMail.OnSuccessCallback() {
                    @Override
                    public void onSuccess() {
                        //do some magic
                    }
                })
                .withOnFailCallback(new BackgroundMail.OnFailCallback() {
                    @Override
                    public void onFail() {
                        //do some magic
                    }
                })
                .send();
                .withType(BackgroundMail.TYPE_HTML)
                .withSubject("this is the subject")
                .withBody("<h1>Report of your Child</h1><p>a message<p/>")
                .withOnSuccessCallback(new BackgroundMail.OnSuccessCallback() {public void onSuccess() { }}).send();
    }
    catch (Exception e)
    {
        System.out.println(e.getMessage());
        Toast.makeText(this, "Error sending the email", Toast.LENGTH_SHORT);
    }
    }
}
