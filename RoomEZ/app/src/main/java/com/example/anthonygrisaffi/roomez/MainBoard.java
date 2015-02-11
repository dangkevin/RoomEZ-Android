package com.example.anthonygrisaffi.roomez;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;


public class MainBoard extends ActionBarActivity
{

    ImageButton plusButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_board);

        plusButton = (ImageButton)findViewById(R.id.plusButton);
        plusButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                popUpOptions();
            }
        });

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

        //gridView.setAdapter(new ImageAdaptor(this));

    }

    private void popUpOptions()
    {
        Toast.makeText(MainBoard.this, "Anthony is sexy", Toast.LENGTH_SHORT).show();
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
