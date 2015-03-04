package com.example.anthonygrisaffi.roomez;

/**
 * Created by KD on 2/3/15.
 */

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.RectF;
import android.hardware.camera2.params.BlackLevelPattern;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import me.drakeet.materialdialog.MaterialDialog;


public class CalendarActivity extends ActionBarActivity implements WeekView.MonthChangeListener,
        WeekView.EventClickListener, WeekView.EventLongPressListener  {

    DatePicker datePicker;
    TimePicker timePicker;
    public String hourSelected;
    public String minSelected;
    public int dayNum;
    public int yearNum;
    public int monthNumValue;
    private WeekView mWeekView;
    private WeekView.EventClickListener mEventClickListener;
    private WeekView.MonthChangeListener mMonthChangeListener;
    private WeekView.EventLongPressListener mEventLongPressListener;
    ParseObject event;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        findViewById(R.id.floatingButtonCal1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCalEvent();
            }
        });


        // Get a reference for the week view in the layout.


        mWeekView = (WeekView) findViewById(R.id.weekView);

// Set an action when any event is clicked.
        //mWeekView.setOnEventClickListener(mEventClickListener);


        // Show a toast message about the touched event.
        mWeekView.setOnEventClickListener(this);

        // The week view has infinite scrolling horizontally. We have to provide the events of a
        // month every time the month changes on the week view.
        //mWeekView.setMonthChangeListener(this);

        // Set long press listener for events.
        mWeekView.setEventLongPressListener(this);


        mWeekView.setMonthChangeListener(this);

// Set long press listener for events.
        //mWeekView.setEventLongPressListener(mEventLongPressListener);

    }

    private void addCalEvent()
    {
           Intent intent = new Intent(this, CreateCalendarEvent.class);
           startActivity(intent);

    }



//    private void saveToParse()
//    {
//        event = new ParseObject("Event");
//        event.put("Hour", hourSelected);
//        event.put("Min", minSelected);
//        event.put("Day", daySelected);
//        event.put("AMPM", ampmSelected);
//        event.put("Color", colorSelected);
//        event.put("EventTitle", eventTitleString);
//        event.put("EventDetails", eventDetailsString);
//        event.put("startTime", startSelected);
//        event.put("endTime", endSelected);
//        event.put("min", minSelected);
//        event.put("day", daySelected);
//        event.put("ampm", ampmSelected);
//        event.put("color", colorSelected);
//        event.put("eventName", eventTitleString);
//        event.put("eventDetails", eventDetailsString);
//        event.saveInBackground();
//
//    }


    @Override
    public List<WeekViewEvent> onMonthChange(int newYear, int newMonth) {

        List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();

        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 3);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        Calendar endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR, 1);
        endTime.set(Calendar.MONTH, newMonth-1);
        WeekViewEvent event = new WeekViewEvent(1, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.teal));
        events.add(event);



        return events;
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
                //finish();
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
                /*Intent b = new Intent(this, CalendarActivity.class);
                startActivity(b);
                finish();*/
                return true;
            case R.id.action_sticky:
                Intent c = new Intent(this, MainBoard.class);
                startActivity(c);
                finish();
                return true;
            case R.id.logOut:
                ParseUser.logOut();
                finish();
                Intent d = new Intent(this, DispatchActivity.class);
                startActivity(d);


            default:
                return super.onOptionsItemSelected(item);
        }
    }


        private String getEventTitle(Calendar time) {
            return String.format("Event of %02d:%02d %s/%d", time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), time.get(Calendar.MONTH)+1, time.get(Calendar.DAY_OF_MONTH));
        }

        @Override
        public void onEventClick(WeekViewEvent event, RectF eventRect) {
            Toast.makeText(CalendarActivity.this, "Clicked " + event.getName(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onEventLongPress(WeekViewEvent event, RectF eventRect) {
            Toast.makeText(CalendarActivity.this, "Long pressed event: " + event.getName(), Toast.LENGTH_SHORT).show();
        }
    public void setDatePicker(int month, int day, int year)
    {
        monthNumValue = month + 1;
        dayNum = day;
        yearNum = year;
       // Toast.makeText(this, "Date is " + month + "/"+ day + "/"+ year, Toast.LENGTH_SHORT).show();

    }
}


