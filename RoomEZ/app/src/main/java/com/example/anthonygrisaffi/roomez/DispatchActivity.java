package com.example.anthonygrisaffi.roomez;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.parse.ParseUser;


/**
 * Created by KD on 1/25/15.
 */
    /**
     * Activity which starts an intent for either the logged in (MainActivity) or logged out
     * (SignUpOrLoginActivity) activity.
     */
    public class DispatchActivity extends Activity {
    private String currUser;

       private Intent serviceIntent;

        public DispatchActivity() {
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            serviceIntent = new Intent(getApplicationContext(), MessageService.class);
            //ParseUser currentUser = ParseUser.getCurrentUser();
            // Check if there is current user info
            if (ParseUser.getCurrentUser() != null) {

                //setUser(currUser);
                // Start an intent for the logged in activity
                Toast.makeText(getApplicationContext(), "Called", Toast.LENGTH_LONG).show();
                //startService(serviceIntent);
                Toast.makeText(getApplicationContext(), currUser, Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "Messaging should have started", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, MainBoard.class));


            } else {
                // Start and intent for the logged out activity
                //Toast.makeText(getApplicationContext(), "Messaging should have started", Toast.LENGTH_LONG).show();
                //stopService(serviceIntent);
                startActivity(new Intent(this, WelcomeActivity.class));
            }
        }

        public void setUser(String user){
            this.currUser = user;
        }

        public String getUser()
        {
            return this.currUser;
        }


    }

