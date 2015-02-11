package com.example.anthonygrisaffi.roomez;

import android.app.ActionBar;
import android.app.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;

import com.beardedhen.androidbootstrap.BootstrapButton;

import me.drakeet.materialdialog.MaterialDialog;




public class LoginPopUp extends Activity {


    private EditText usernameInput;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        mMaterialDialog.setPositiveButton("Sign Up", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMaterialDialog.dismiss();

            }
        });
        mMaterialDialog.setNegativeButton("Cancel", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMaterialDialog.dismiss();

            }
        });
        mMaterialDialog.setCanceledOnTouchOutside(true);
        mMaterialDialog.setView(hello);
        mMaterialDialog.show();
    }
}

