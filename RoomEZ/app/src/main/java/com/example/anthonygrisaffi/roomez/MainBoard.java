package com.example.anthonygrisaffi.roomez;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;

import me.drakeet.materialdialog.MaterialDialog;


public class MainBoard extends ActionBarActivity
{

    ImageButton plusButton;
    private BootstrapButton addSticky;
    private BootstrapButton groupChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_board);
        GridView gridView1 =(GridView)findViewById(R.id.gridView);
        gridView1.setAdapter(new ImageAdaptor(this));

        plusButton = (ImageButton)findViewById(R.id.plusButton);
        plusButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Toast.makeText(MainBoard.this, "Anthony is sexy", Toast.LENGTH_SHORT).show();
                popUpOptions();
            }
        });


        //button.setColorNormalResId(R.color.MaterialBlue);
        //calling the Endless Scroll class on the gridview layout
        GridView gridView = (GridView)findViewById(R.id.gridView);

        gridView.setOnScrollListener(new EndlessScrollListener()
        {
            @Override
            public void onLoadMore(int page, int totalItemsCount)
            {
              // Triggered only when new data needs to be appended to the list
              // Add whatever code is needed to append new items to your AdapterView
              customLoadMoreDataFromApi(page);
              // or customLoadMoreDataFromApi(totalItemsCount);
            }
        });



    }

    private void popUpOptions()
    {
        Toast.makeText(MainBoard.this, "Anthony is sexy", Toast.LENGTH_SHORT).show();
        //Define a custom layout
        LayoutInflater factory = LayoutInflater.from(this);
        //Creates a view and adds buttons (such as Edit Text) from a customized layout
        View myView = factory.inflate(R.layout.plus_button_options, null);
        //Linear layout is created
        LinearLayout hello = new LinearLayout(this);
        //Adds the view to the linear layout
        hello.addView(myView);
        //Initializes the Edit Text buttons
        addSticky = (BootstrapButton) myView.findViewById(R.id.add_sticky_button);
        groupChat = (BootstrapButton) myView.findViewById(R.id.start_group_chat);

        //Responsible for the dialog
        final MaterialDialog mMaterialDialog = new MaterialDialog(this);

        //This line handles whenever the LOGIN button is clicked in the dialog
        /*final MaterialDialog materialDialog = mMaterialDialog.setPositiveButton("LOGIN", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //login(); //Makes a call to the login function associated with Parse


            }
        });*/

        addSticky.setOnClickListener((new View.OnClickListener() {
            public void onClick(View view) {

                Toast.makeText(MainBoard.this, "Making a new sticky", Toast.LENGTH_SHORT).show();
                create_a_sticky();
            }
        }));

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

    private void create_a_sticky()
    {
        //FragmentActivity fragmentActivity =
    }

    private void customLoadMoreDataFromApi(int page)
    {
        // This method probably sends out a network request and appends new data items to your adapter.
        // Use the offset value and add it as a parameter to your API request to retrieve paginated data.
        // Deserialize API response and then construct new objects to append to the adapter

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_board, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //int id = item.getItemId();


        switch (item.getItemId()) {

            case R.id.action_settings:
                // action_settings
                return true;
            case R.id.action_gm:
                // location found
                Intent i = new Intent(this, GroupMessage.class);
                startActivity(i);
                finish();
                // refresh
                return true;
            case R.id.action_cal:
                Intent b = new Intent(this, CalendarActivity.class);
                startActivity(b);
                finish();
                return true;
            case R.id.action_sticky:
                Intent c = new Intent(this,MainBoard.class);
                startActivity(c);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


        //noinspection SimplifiableIfStatement

        /*
        These if conditions check to see if the action bar buttons are pressed.
        Their id's are located in menu_main_board. The sticky and the gm one should be working.
        We need to investigate destroying the activities so they don't stay active the whole time.
         */



        /*if (id == R.id.action_cal)
        {
            startActivity(new Intent(this,));
        }*/
}
