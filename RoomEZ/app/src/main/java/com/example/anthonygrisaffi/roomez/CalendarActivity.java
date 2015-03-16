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
import android.view.View.OnClickListener;
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


public class CalendarActivity extends ActionBarActivity implements
        WeekView.EventClickListener, WeekView.EventLongPressListener {

    DatePicker datePicker;
    TimePicker timePicker;
    public String hourSelected;
    public String minSelected;
    public int dayNum;
    public int yearNum;
    public int monthNumValue;
    private WeekView mWeekView;
    private Calendar mStartTime;
    private Calendar mEndTime;
    private WeekView.EventClickListener mEventClickListener;
    private WeekView.EventLongPressListener mEventLongPressListener;
    ParseObject event;
    private static List<WeekViewEvent> mEventModels;
    private static int hour = 14;
    private static String eventTitle;
    private static int min = 30;
    private static int DOM;
    private static int mon;
    private static int eDay;
    private static int eMon;
    private static int eDOM;
    private static int eYear;
    private static int yr;


    int hourend = 16;
    int endmonth = 2;
    int endmonthday = 4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        mEventModels = new ArrayList<>();


        findViewById(R.id.plusButton1).setOnClickListener(new View.OnClickListener() {
                           @Override
                            public void onClick(View v) {
                            addCalEvent();


                        }
          });



        findViewById(R.id.floatingButtonCal1).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                CreateCalendarEvent hello2 = new CreateCalendarEvent();
                mon = hello2.getMonth();
                Toast.makeText(getApplicationContext(), "Month is" + mon, Toast.LENGTH_SHORT).show();
                DOM = hello2.getDay();
                Toast.makeText(getApplicationContext(), "Day is" + DOM, Toast.LENGTH_SHORT).show();
                yr = hello2.getYear();
                Toast.makeText(getApplicationContext(), "Year is" + DOM, Toast.LENGTH_SHORT).show();
                eventTitle = hello2.getEventTitle();
                Toast.makeText(getApplicationContext(), "String is" + eventTitle, Toast.LENGTH_SHORT).show();
                eMon = hello2.geteMonth();
                eDOM = hello2.geteDay();
                eYear = hello2.geteYear();

                mStartTime = Calendar.getInstance();
                mStartTime.set(Calendar.HOUR_OF_DAY, 14);
                mStartTime.set(Calendar.DAY_OF_MONTH,DOM);
                mStartTime.set(Calendar.MINUTE, 30);
                mStartTime.set(Calendar.MONTH, mon );
                mStartTime.set(Calendar.YEAR, yr);
                mEndTime = Calendar.getInstance();
                mEndTime.set(Calendar.HOUR_OF_DAY, 16);
                mEndTime.set(Calendar.DAY_OF_MONTH,eDOM);
                mEndTime.set(Calendar.MINUTE, 30);
                mEndTime.set(Calendar.MONTH, eMon);
                mEndTime.set(Calendar.YEAR, eYear);
                WeekViewEvent weekEvent = new WeekViewEvent(1,eventTitle, mStartTime, mEndTime);
                //mEventModels.clear();
                mEventModels.add(weekEvent);
                mWeekView.notifyDatasetChanged();

            }
        });


        //CreateCalendarEvent hello = new CreateCalendarEvent();
        //mon = hello.getMonth();
        //Toast.makeText(getApplicationContext(), "Month is" + mon, Toast.LENGTH_SHORT).show();

        // Get a reference for the week view in the layout.
        mWeekView = (WeekView) findViewById(R.id.weekView);

        // Set an action when any event is clicked.
        mWeekView.setOnEventClickListener(mEventClickListener);


        // Show a toast message about the touched event.
        mWeekView.setOnEventClickListener(this);

        // The week view has infinite scrolling horizontally. We have to provide the events of a
        // month every time the month changes on the week view.
         mWeekView.setMonthChangeListener(new WeekView.MonthChangeListener() {
              @Override
           public List<WeekViewEvent> onMonthChange(int newYear, int newMonth) {
                  List<WeekViewEvent> weekviewEvents = new ArrayList<WeekViewEvent>();
                  for (WeekViewEvent event : mEventModels) {
                      if (event.getStartTime().get(Calendar.MONTH) + 1 == newMonth && event.getStartTime().get(Calendar.YEAR) == newYear) {
                          weekviewEvents.add(event);
                      }
                  }
                      return weekviewEvents;
                  }
              });




        // Set long press listener for events.
        mWeekView.setEventLongPressListener(this);

        mWeekView.setNumberOfVisibleDays(3);

    }




    private void addCalEvent()
    {
        Intent intent = new Intent(this, CreateCalendarEvent.class);
        startActivity(intent);

    }



//    private void createEvent() {
//
//        switch (monthSelected) {
//
//            case ("January"):
//                monthNumValue = 0;
//                break;
//            case ("February"):
//                monthNumValue = 1;
//                break;
//            case ("March"):
//                monthNumValue = 2;
//                break;
//            case ("April"):
//                monthNumValue = 3;
//                break;
//            case ("May"):
//                monthNumValue = 4;
//                break;
//            case ("June"):
//                monthNumValue = 5;
//                break;
//            case ("July"):
//                monthNumValue = 6;
//                break;
//            case ("August"):
//                monthNumValue = 7;
//                break;
//            case ("September"):
//                monthNumValue = 8;
//                break;
//            case ("October"):
//                monthNumValue = 9;
//                break;
//            case ("November"):
//                monthNumValue = 10;
//                break;
//            case ("December"):
//                monthNumValue = 11;
//                break;
//
//        }
//        int start = Integer.parseInt(startSelected);
//        int end = Integer.parseInt(endSelected);
//        if (ampmSelected == "pm") {
//            start += 12;
//            if ((end - start) < 0) {
//                end += 12;
//            }
//
//            if (start == 24 || end == 24) {
//                start = 0;
//                end = 0;
//            }
//        }
//
//     List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();
//
//        Calendar startTime = Calendar.getInstance();
//        startTime.set(Calendar.HOUR_OF_DAY, start);
//        startTime.set(Calendar.MINUTE, Integer.parseInt(minSelected));
//        startTime.set(Calendar.MONTH, monthNumValue);
//        startTime.set(Calendar.YEAR, Calendar.YEAR);
//        Calendar endTime = (Calendar) startTime.clone();
//        endTime.set(Calendar.HOUR, end);
//        endTime.set(Calendar.MONTH, monthNumValue);
//        WeekViewEvent event = new WeekViewEvent(1, "Hey", startTime, endTime);
//
//
//        switch (colorSelected) {
//            case ("Black"):
//                event.setColor(getResources().getColor(R.color.black));
//                break;
//            case ("Red"):
//                event.setColor(getResources().getColor(R.color.MaterialRed));
//                break;
//            case ("Blue"):
//                event.setColor(getResources().getColor(R.color.MaterialBlue));
//                break;
//            case ("Yellow"):
//                event.setColor(getResources().getColor(R.color.yellow));
//                break;
//            case ("Pink"):
//                event.setColor(getResources().getColor(R.color.pink));
//                break;
//            case ("Gray"):
//                event.setColor(getResources().getColor(R.color.grey));
//                break;
//            case ("Teal"):
//                event.setColor(getResources().getColor(R.color.teal));
//                break;
//        }
//
//        event.setColor(getResources().getColor(R.color.teal));
//        events.add(event);
//        //  mWeekView.setMonthChangeListener(this);
//
//    }
//
//
//    private void saveToParse() {
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







/*
        @Override
        public List<WeekViewEvent> onMonthChange(int newYear, int newMonth)
        {

            List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();
            Calendar startTime = Calendar.getInstance();
            Toast.makeText(getApplicationContext(), "Date is " + monthNumValue + "/" + dayNum + "/" + yearNum, Toast.LENGTH_SHORT).show();

            startTime.set(Calendar.HOUR_OF_DAY, hour);
            //Toast.makeText(CalendarActivity.this,"Hour is" + hour,Toast.LENGTH_LONG).show();
            startTime.set(Calendar.MINUTE, min);
            startTime.set(Calendar.DAY_OF_MONTH,dayNum);
            startTime.set(Calendar.MONTH, monthNumValue);
            startTime.set(Calendar.YEAR, yearNum);
            Calendar endTime = (Calendar) startTime.clone();
            endTime.set(Calendar.HOUR_OF_DAY, hourend);
            endTime.set(Calendar.MONTH, monthNumValue);
            endTime.set(Calendar.DAY_OF_MONTH, dayNum);
            WeekViewEvent event = new WeekViewEvent(1, "Jacob", startTime, endTime);
            event.setColor(getResources().getColor(R.color.MaterialBlue));

            events.add(event);
            mWeekView.notifyDatasetChanged();

            return events;
        }*/





        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main_board, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
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
        return String.format("Event of %02d:%02d %s/%d", time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), time.get(Calendar.MONTH) + 1, time.get(Calendar.DAY_OF_MONTH));
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
    //Toast.makeText(com.example.anthonygrisaffi.roomez.CalendarActivity.this, "Date is " + monthNumValue + "/"+ dayNum + "/"+ yearNum, Toast.LENGTH_SHORT).show();

    }
}

