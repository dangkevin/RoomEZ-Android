package com.example.anthonygrisaffi.roomez;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.beardedhen.androidbootstrap.BootstrapButton;

import me.drakeet.materialdialog.MaterialDialog;


public class WelcomeActivity extends Activity {

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
        // hello.setOrientation(LinearLayout.VERTICAL);
        // final EditText textBoth
        // final EditText titleBox = new EditText(this);
        // final EditText passwordBox = new EditText(this);
        // titleBox.setHint("Enter your e-mail address");
        // passwordBox.setHint("Enter your password");
        hello.addView(myView);
        //hello.addView(passwordBox);

        final MaterialDialog mMaterialDialog = new MaterialDialog(this);
        mMaterialDialog.setTitle("MaterialDialog");
        //mMaterialDialog.setMessage("Hello world!");
        final MaterialDialog materialDialog = mMaterialDialog.setPositiveButton("SIGN UP", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b = new Intent(WelcomeActivity.this, MainBoard.class);
                startActivity(b);
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
        // hello.setOrientation(LinearLayout.VERTICAL);
        // final EditText textBoth
        // final EditText titleBox = new EditText(this);
        // final EditText passwordBox = new EditText(this);
        // titleBox.setHint("Enter your e-mail address");
        // passwordBox.setHint("Enter your password");
        hello.addView(myView);
        //hello.addView(passwordBox);

        final MaterialDialog mMaterialDialog = new MaterialDialog(this);
        mMaterialDialog.setTitle("MaterialDialog");
        //mMaterialDialog.setMessage("Hello world!");
        final MaterialDialog materialDialog = mMaterialDialog.setPositiveButton("LOGIN", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
}
