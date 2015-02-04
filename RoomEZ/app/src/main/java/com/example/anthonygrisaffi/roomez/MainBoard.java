package com.example.anthonygrisaffi.roomez;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;


public class MainBoard extends ActionBarActivity
{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_board);
        //The app segfaults when I call the main activity layout.
        //setContentView(R.layout.activity_main_board);
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        /*
        These if conditions check to see if the action bar buttons are pressed.
        Their id's are located in menu_main_board. The sticky and the gm one should be working.
        We need to investigate destroying the activities so they don't stay active the whole time.
         */
        if (id == R.id.action_sticky)
        {
            startActivity(new Intent(this, MainBoard.class));
        }
        if (id == R.id.action_gm)
        {
            //startActivity(new Intent(this, GroupMessage.class));
        }

        /*if (id == R.id.action_cal)
        {
            startActivity(new Intent(this,));
        }*/

        return super.onOptionsItemSelected(item);
    }
}
