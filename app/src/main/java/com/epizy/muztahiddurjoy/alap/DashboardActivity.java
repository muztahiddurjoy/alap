package com.epizy.muztahiddurjoy.alap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class DashboardActivity extends AppCompatActivity {
private EditText meet;
private Button join,share;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        meet = (EditText) findViewById(R.id.meetingId);
        join = (Button) findViewById(R.id.join);
        share = (Button) findViewById(R.id.share);

        URL serverUrl;

        try {
            serverUrl = new URL("https://meet.jit.si");
            JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                    .setServerURL(serverUrl)
                    .setWelcomePageEnabled(false)
                    .build();
            JitsiMeet.setDefaultConferenceOptions(options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                            .setRoom(meet.getText().toString())
                            .setWelcomePageEnabled(false)
                            .build();
                JitsiMeetActivity.launch(DashboardActivity.this,options);
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share = new Intent();
                share.setAction(Intent.ACTION_SEND);
                share.putExtra(Intent.EXTRA_TEXT,"Join Us on Alap \n Meeting id :"+meet.getText().toString());
                share.setType("text/plain");
                Intent shareIntent = Intent.createChooser(share,null);
                startActivity(shareIntent);
            }
        });
    }
}