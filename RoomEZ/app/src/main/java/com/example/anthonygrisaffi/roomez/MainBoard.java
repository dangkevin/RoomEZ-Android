package com.example.anthonygrisaffi.roomez;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;

import com.parse.ParseUser;

import java.nio.Buffer;
import java.util.ArrayList;


public class MainBoard extends ActionBarActivity
{

    ImageButton plusButton;
    private BootstrapButton addSticky;
    private BootstrapButton groupChat;
    private Intent intent;
    private Intent serviceIntent;
    private TextView textout;
    private TextView textout2;
    private TextView textout3;
    private ArrayList<String> stringArray;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_board);

        stringArray = new ArrayList<>();
        stringArray.add("Anthony");
        stringArray.add("Kevin");
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);


        // specify an adapter (see also next example)
        mAdapter = new StickyAdapter(stringArray);
        recyclerView.setAdapter(mAdapter);


        findViewById(R.id.floatingButtonSticky1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainBoard.this, "Anthony is sexy", Toast.LENGTH_SHORT).show();
                View cardLayout = findViewById(R.id.card_view);
                cardLayout.setBackgroundColor(Color.GREEN);
                TextView hello1 = (TextView) findViewById(R.id.info_text);
                hello1.setText("hi guys my name is anthuny");



            }
        });
        //CardView hello = (CardView) findViewById(R.id.card_view);


       // textout.setText("HEEEEEEY");

       // textout2 = (TextView) findViewById(R.id.info_text2);
       // textout2.setText("WOOOOO");

       // textout3 = (TextView) findViewById(R.id.info_text3);
       // textout3.setText("ANTHONY");








        //button.setColorNormalResId(R.color.MaterialBlue);
        //calling the Endless Scroll class on the gridview layout
        //  GridView gridView = (GridView) findViewById(R.id.gridView);

        /*gridView.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to your AdapterView
                customLoadMoreDataFromApi(page);
                // or customLoadMoreDataFromApi(totalItemsCount);
            }
        });

        gridView.setAdapter(new ImageAdaptor(this));

    }*/
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
                Intent j = new Intent(this, AccountSettings.class);
                startActivity(j);
                //finish();
                // action_settings
                return true;
            case R.id.action_gm:
                Intent serviceIntent = new Intent(getApplicationContext(),MessageService.class);
                Intent k = new Intent(this, GroupMessage.class);
                startActivity(k);
                startService(serviceIntent);
                finish();
                // refresh
                return true;
            case R.id.action_cal:
                Intent b = new Intent(this, CalendarActivity.class);
                startActivity(b);
                finish();
                return true;
            case R.id.action_sticky:
               // GridView gridView1 =(GridView)findViewById(R.id.gridView);
               // gridView1.setAdapter(new ImageAdaptor(this));
//                Intent c = new Intent(this,MainBoard.class);
//                startActivity(c);
//                finish();
                return true;
            case R.id.logOut:
                stopService(new Intent(getApplicationContext(), MessageService.class));
                ParseUser.logOut();
                finish();
                Intent d = new Intent(this,DispatchActivity.class);
                startActivity(d);



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
