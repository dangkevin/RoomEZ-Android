package com.example.anthonygrisaffi.roomez;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.parse.ParseUser;


public class GroupMessage extends ActionBarActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_message);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_board, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        switch (item.getItemId()) {

            case R.id.action_settings:
                Intent j = new Intent(this, AccountSettings.class);
                startActivity(j);
                // action_settings
                return true;
            case R.id.action_gm:
                // location found
                /*Intent i = new Intent(this, GroupMessage.class);
                startActivity(i);
                finish();*/
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
            case R.id.logOut:
                ParseUser.logOut();
                finish();
                Intent d = new Intent(this,DispatchActivity.class);
                startActivity(d);

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
