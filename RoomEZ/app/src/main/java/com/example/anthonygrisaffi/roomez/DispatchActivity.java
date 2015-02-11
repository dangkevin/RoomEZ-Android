package com.example.anthonygrisaffi.roomez;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.parse.ParseUser;


/**
 * Created by KD on 1/25/15.
 */
    /**
     * Activity which starts an intent for either the logged in (MainActivity) or logged out
     * (SignUpOrLoginActivity) activity.
     */
    public class DispatchActivity extends Activity {

        public DispatchActivity() {
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            // Check if there is current user info
            if (ParseUser.getCurrentUser() != null) {
                // Start an intent for the logged in activity
                startActivity(new Intent(this, MainBoard.class));
            } else {
                // Start and intent for the logged out activity
                startActivity(new Intent(this, WelcomeActivity.class));
            }
        }

    }

