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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
    private Spinner spinner;
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
    private static int starthour;
    private static int startminute;
    private static int endhour;
    private static int endminute;
    private static String eventTitle;
    private static int min = 30;
    private static int DOM;
    private static int mon;
    private static int eDay;
    private static int eMon;
    private static int eDOM;
    private static int eYear;
    private static int yr;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        spinner = (Spinner) findViewById(R.id.spinner1);
        mEventModels = new ArrayList<>();


        findViewById(R.id.plusButton1).setOnClickListener(new View.OnClickListener() {
                           @Override
                            public void onClick(View v) {

                               CreateCalendarEvent hello2 = new CreateCalendarEvent();
                               mon = hello2.getMonth();
                               //Toast.makeText(getApplicationContext(), "Month is" + mon, Toast.LENGTH_SHORT).show();
                               DOM = hello2.getDay();
                               //Toast.makeText(getApplicationContext(), "Day is" + DOM, Toast.LENGTH_SHORT).show();
                               yr = hello2.getYear();
                               //Toast.makeText(getApplicationContext(), "Year is" + DOM, Toast.LENGTH_SHORT).show();
                               eventTitle = hello2.getEventTitle();
                               //Toast.makeText(getApplicationContext(), "String is" + eventTitle, Toast.LENGTH_SHORT).show();
                               starthour = hello2.getStartHour();
                               startminute = hello2.getStartMinute();
                               endhour = hello2.getEndHour();
                               endminute = hello2.getEndMinute();

                               eMon = hello2.geteMonth();
                               eDOM = hello2.geteDay();
                               eYear = hello2.geteYear();

                               mStartTime = Calendar.getInstance();
                               mStartTime.set(Calendar.HOUR_OF_DAY, starthour);
                               mStartTime.set(Calendar.DAY_OF_MONTH,DOM);
                               mStartTime.set(Calendar.MINUTE, startminute);
                               mStartTime.set(Calendar.MONTH, mon );
                               mStartTime.set(Calendar.YEAR, yr);
                               mEndTime = Calendar.getInstance();
                               mEndTime.set(Calendar.HOUR_OF_DAY, endhour);
                               mEndTime.set(Calendar.DAY_OF_MONTH,eDOM);
                               mEndTime.set(Calendar.MINUTE, endhour);
                               mEndTime.set(Calendar.MONTH, eMon);
                               mEndTime.set(Calendar.YEAR, eYear);
                               WeekViewEvent weekEvent = new WeekViewEvent(1,eventTitle, mStartTime, mEndTime);
                               //mEventModels.clear();
                               mEventModels.add(weekEvent);
                               mWeekView.notifyDatasetChanged();

                           }
        });




        findViewById(R.id.floatingButtonCal1).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                addCalEvent();


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

        mWeekView.setNumberOfVisibleDays(1);

    }



    public void spinner(){



    }



    private void addCalEvent()
    {
        Intent intent = new Intent(this, CreateCalendarEvent.class);
        startActivity(intent);

    }



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
                case R.id.Three:
                    mWeekView.setNumberOfVisibleDays(3);
                    return true;
                case R.id.Daily:
                    mWeekView.setNumberOfVisibleDays(1);
                    return true;
                case R.id.Seven:
                    mWeekView.setNumberOfVisibleDays(7);
                    return true;




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

