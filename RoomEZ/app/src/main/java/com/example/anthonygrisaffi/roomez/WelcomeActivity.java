package com.example.anthonygrisaffi.roomez;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


//Github Library we will be using for the dialog
import me.drakeet.materialdialog.MaterialDialog;


/* This is the Welcome Activity that is seen when the app is initialized. Handles the login
    and sign up components for our apps.
 */
public class WelcomeActivity extends Activity {



    //Text field used for the username upon login
    private EditText loginUsername;

    //Text field used for password upon login
    private EditText loginPassword;

    //Text field used for the username when signing up
    private EditText signupUsername;

    //Text field used for password when signing up
    private EditText signupPassword;

    //Text field used for entering password again
    private EditText signupPassword2;

    private String currUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);





        //Initializes a customized login button from a GitHub library
        BootstrapButton logininButton = (BootstrapButton) findViewById(R.id.welcomelogin);
        //Initializes a customized signup button from a GitHub Library
        BootstrapButton signupButton = (BootstrapButton) findViewById(R.id.welcomesignup);

        //When the sign up button is clicked, a pop up sign up dialog will appear
        signupButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                signupPopup();   //This function calls the sign up dialog
            }
        });

        //When the login button is clicked, a pop up Login dialog will appear
        logininButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                loginPopup();   //This function calls the Login dialog

            }
        });
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
        return true;
    }

    /****************This method is responsible for generating the sign up pop up *****************/
    public void signupPopup(){
        //Define a custom layout
        LayoutInflater factory = LayoutInflater.from(this);
        //Creates a view and adds buttons (such as Edit Text) from a customized layout
        View myView = factory.inflate(R.layout.activity_login_pop_up, null);
        //Linear layout is created
        LinearLayout hello = new LinearLayout(this);

        //The customized layout with all the buttons are added into this Linear Layout
        hello.addView(myView);

        //The EditText buttons are initialized here referenced by their id from an XML
        signupUsername = (EditText) myView.findViewById(R.id.popupemail);
        signupPassword = (EditText) myView.findViewById(R.id.popuppassword);
        signupPassword2 = (EditText) myView.findViewById(R.id.popuppasswordagain);


       //This call is responsible for generating a custom dialog pop up
        final MaterialDialog mMaterialDialog = new MaterialDialog(this);

        //This line handles whenever the SIGN UP in the dialog is clicked
        final MaterialDialog materialDialog = mMaterialDialog.setPositiveButton("SIGN UP", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();   //Makes a call to the Sign Up function associated with Parse
                mMaterialDialog.dismiss();

            }
        });

        //This line handles whenever the CANCEL button is clicked in the dialog
        mMaterialDialog.setNegativeButton("CANCEL", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMaterialDialog.dismiss(); //Dismisses the dialog

            }
        });
        //Allows the dialog to be canceled on outside touch
        mMaterialDialog.setCanceledOnTouchOutside(true);
        //Sets the view from the linear layout to the dialog
        mMaterialDialog.setView(hello);
        //Shows the dialog
        mMaterialDialog.show();
    }

    public void setUser(String user){
        this.currUser = user;
    }

    public String getUser(){
        return this.currUser;
    }

    /****************This method is responsible for generating the Login pop up *****************/
    public void loginPopup(){
        //Define a custom layout
        LayoutInflater factory = LayoutInflater.from(this);
        //Creates a view and adds buttons (such as Edit Text) from a customized layout
        View myView = factory.inflate(R.layout.activity_signup_pop_up, null);
        //Linear layout is created
        LinearLayout hello = new LinearLayout(this);
        //Adds the view to the linear layout
        hello.addView(myView);
        //Initializes the Edit Text buttons
        loginUsername = (EditText) myView.findViewById(R.id.popupemail2);
        loginPassword = (EditText) myView.findViewById(R.id.popuppassword2);

        //Responsible for the dialog
        final MaterialDialog mMaterialDialog = new MaterialDialog(this);

        //This line handles whenever the LOGIN button is clicked in the dialog
        final MaterialDialog materialDialog = mMaterialDialog.setPositiveButton("LOGIN", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(); //Makes a call to the login function associated with Parse


            }
        });
        //This line handles whenever the CANCEL button is clicked in the dialog
        mMaterialDialog.setNegativeButton("CANCEL", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMaterialDialog.dismiss();

            }
        });

        mMaterialDialog.setCanceledOnTouchOutside(true);
        //Sets the Linear Layout
        mMaterialDialog.setView(hello);
        //Show dialog
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

/**********************This method handles the database query for the sign up*********************/
    private void signup() {

        /**Involves Parse**/

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

       /*if(username.length() < 5){
            validationError = true;
            validationErrorMessage.append(("Email invalid"));
        }*/

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


/*************************This method handles the database checks for Login***********************/

        /**Involves Parse**/

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

    @Override
    public void onDestroy() {
        stopService(new Intent(this, MessageService.class));
        super.onDestroy();
    }
}








