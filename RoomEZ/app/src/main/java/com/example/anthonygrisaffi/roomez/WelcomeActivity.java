package com.example.anthonygrisaffi.roomez;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import me.drakeet.materialdialog.MaterialDialog;


public class WelcomeActivity extends Activity {

    //Text field used for login username
    private EditText loginUsername;

    //Text field used for login password
    private EditText loginPassword;

    //Text field used for sign up username
    private EditText signupUsername;

    //Text field used for sign up password
    private EditText signupPassword;

    //Text field used for sign up password (Entering again)
    private EditText signupPassword2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        BootstrapButton logininButton = (BootstrapButton) findViewById(R.id.welcomelogin);
        BootstrapButton signupButton = (BootstrapButton) findViewById(R.id.welcomesignup);
        signupButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                signupPopup();
            }
        });
        logininButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                loginPopup();

            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
        return true;
    }

    public void signupPopup(){
        LayoutInflater factory = LayoutInflater.from(this);
        View myView = factory.inflate(R.layout.activity_login_pop_up, null);
        LinearLayout hello = new LinearLayout(this);
        signupUsername = (EditText) myView.findViewById(R.id.popupemail);
        signupPassword = (EditText) myView.findViewById(R.id.popuppassword);
        signupPassword2 = (EditText) myView.findViewById(R.id.popuppasswordagain);
        hello.addView(myView);



        final MaterialDialog mMaterialDialog = new MaterialDialog(this);
        mMaterialDialog.setTitle("MaterialDialog");
        //mMaterialDialog.setMessage("Hello world!");
        final MaterialDialog materialDialog = mMaterialDialog.setPositiveButton("SIGN UP", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
                mMaterialDialog.dismiss();

            }
        });
        mMaterialDialog.setNegativeButton("CANCEL", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMaterialDialog.dismiss();

            }
        });
        mMaterialDialog.setCanceledOnTouchOutside(true);
        mMaterialDialog.setView(hello);
        mMaterialDialog.show();
    }

    public void loginPopup(){
        LayoutInflater factory = LayoutInflater.from(this);
        View myView = factory.inflate(R.layout.activity_signup_pop_up, null);
        LinearLayout hello = new LinearLayout(this);
        loginUsername = (EditText) myView.findViewById(R.id.popupemail2);
        loginPassword = (EditText) myView.findViewById(R.id.popuppassword2);
        hello.addView(myView);
        //hello.addView(passwordBox);

        final MaterialDialog mMaterialDialog = new MaterialDialog(this);
        mMaterialDialog.setTitle("MaterialDialog");
        //mMaterialDialog.setMessage("Hello world!");
        final MaterialDialog materialDialog = mMaterialDialog.setPositiveButton("LOGIN", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //LoginActivity other = new LoginActivity();
                login();


            }
        });
        mMaterialDialog.setNegativeButton("CANCEL", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMaterialDialog.dismiss();

            }
        });
        mMaterialDialog.setCanceledOnTouchOutside(true);
        mMaterialDialog.setView(hello);
        mMaterialDialog.show();

    }






    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void signup() {

        String username = signupUsername.getText().toString().trim();
        String password = signupPassword.getText().toString().trim();
        String passwordAgain = signupPassword2.getText().toString().trim();

        // Validate the sign up data
        boolean validationError = false;
        StringBuilder validationErrorMessage = new StringBuilder(getString(R.string.error_intro));
        if (username.length() == 0) {
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_blank_email));
        }
        if (password.length() == 0) {
            if (validationError) {
                validationErrorMessage.append(getString(R.string.error_join));
            }
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_blank_password));
        }
        if (!password.equals(passwordAgain)) {
            if (validationError) {
                validationErrorMessage.append(getString(R.string.error_join));
            }
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_mismatched_passwords));
        }
        validationErrorMessage.append(getString(R.string.error_end));

        // If there is a validation error, display the error
        if (validationError) {
            Toast.makeText(WelcomeActivity.this, validationErrorMessage.toString(), Toast.LENGTH_LONG)
                    .show();
            return;
        }

        // Set up a progress dialog
        final ProgressDialog dialog = new ProgressDialog(WelcomeActivity.this);
        dialog.setMessage(getString(R.string.progress_signup));
        dialog.show();

        // Set up a new Parse user
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);

        // Call the Parse signup method
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                dialog.dismiss();
                if (e != null) {
                    // Show the error message
                    Toast.makeText(WelcomeActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    // Start an intent for the dispatch activity
                    Intent intent = new Intent(WelcomeActivity.this, DispatchActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });
    }



    private void login() {

        String username = loginUsername.getText().toString().trim();
        String password = loginPassword.getText().toString().trim();

        // Validate the log in data
        boolean validationError = false;
        StringBuilder validationErrorMessage = new StringBuilder(getString(R.string.error_intro));
        if (username.length() == 0) {
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_blank_email));
        }
        if (password.length() == 0) {
            if (validationError) {
                validationErrorMessage.append(getString(R.string.error_join));
            }
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_blank_password));
        }
        validationErrorMessage.append(getString(R.string.error_end));

        // If there is a validation error, display the error
        if (validationError) {
            Toast.makeText(WelcomeActivity.this, validationErrorMessage.toString(), Toast.LENGTH_LONG)
                    .show();
            return;
        }

        // Set up a progress dialog
        final ProgressDialog dialog = new ProgressDialog(WelcomeActivity.this);
        dialog.setMessage(getString(R.string.progress_login));
        dialog.show();
        // Call the Parse login method
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                dialog.dismiss();
                if (e != null) {
                    // Show the error message
                    Toast.makeText(WelcomeActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    // Start an intent for the dispatch activity
                    Intent intent = new Intent(WelcomeActivity.this, DispatchActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });
    }








}
