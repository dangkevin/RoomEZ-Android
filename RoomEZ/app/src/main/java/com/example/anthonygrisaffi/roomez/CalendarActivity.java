package com.example.anthonygrisaffi.roomez;

/**
 * Created by KD on 2/3/15.
 */

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class CalendarActivity extends ActionBarActivity implements WeekView.MonthChangeListener,
        WeekView.EventClickListener, WeekView.EventLongPressListener  {

//    public final static String DIALOG_TITLE = "dialogTitle";
//    public final static String MONTH = "month";
//    public final static String YEAR = "year";
//    public final static String SHOW_NAVIGATION_ARROWS = "showNavigationArrows";
//    public final static String DISABLE_DATES = "disableDates";
//    public final static String SELECTED_DATES = "selectedDates";
//    public final static String MIN_DATE = "minDate";
//    public final static String MAX_DATE = "maxDate";
//    public final static String ENABLE_SWIPE = "enableSwipe";
//    public final static String START_DAY_OF_WEEK = "startDayOfWeek";
//    public final static String SIX_WEEKS_IN_CALENDAR = "sixWeeksInCalendar";
//    public final static String ENABLE_CLICK_ON_DISABLED_DATES = "enableClickOnDisabledDates";
//    public final static String SQUARE_TEXT_VIEW_CELL = "squareTextViewCell";
    public String hourSelected;
    public String minSelected;
    public String daySelected;
    public String ampmSelected;
    public String monthSelected;
    public String colorSelected;
    public String eventTitleString;
    public String eventDetailsString;
    private WeekView mWeekView;
    private WeekView.EventClickListener mEventClickListener;
    private WeekView.MonthChangeListener mMonthChangeListener;
    private WeekView.EventLongPressListener mEventLongPressListener;
    ImageButton plusButton;
    Button doneButton;
    ParseObject event;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        findViewById(R.id.plusButton1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCalEvent();
            }
        });


        mWeekView = (WeekView) findViewById(R.id.weekView);

// Set an action when any event is clicked.
        //mWeekView.setOnEventClickListener(mEventClickListener);


        // Show a toast message about the touched event.
        mWeekView.setOnEventClickListener(this);

        // The week view has infinite scrolling horizontally. We have to provide the events of a
        // month every time the month changes on the week view.
        mWeekView.setMonthChangeListener(this);

        // Set long press listener for events.
        mWeekView.setEventLongPressListener(this);


        mWeekView.setMonthChangeListener(this);

// Set long press listener for events.
        //mWeekView.setEventLongPressListener(mEventLongPressListener);

    }

    private void addCalEvent()
    {

        //Define a custom layout
        LayoutInflater factory = LayoutInflater.from(this);
        //Creates a view and adds buttons (such as Edit Text) from a customized layout
        final View myView = factory.inflate(R.layout.plus_button_cal_popup, null);
        //Linear layout is created
        LinearLayout hello = new LinearLayout(this);
        //Adds the view to the linear layout
        hello.addView(myView);

        addListenerOnButton(myView);

        doneButton = (Button) myView.findViewById(R.id.doneButton);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                EditText eventTitle = (EditText) myView.findViewById(R.id.eventName);
                eventTitleString = eventTitle.getText().toString();

                EditText eventDetail = (EditText) myView.findViewById(R.id.eventDetails);
                eventDetailsString = eventDetail.getText().toString();

                saveToParse();

                createEvent();


                Toast.makeText(CalendarActivity.this, "hour is " + hourSelected, Toast.LENGTH_SHORT).show();
                Toast.makeText(CalendarActivity.this, "day is " + daySelected, Toast.LENGTH_SHORT).show();
                Toast.makeText(CalendarActivity.this, "color is " + colorSelected, Toast.LENGTH_SHORT).show();

                Toast.makeText(CalendarActivity.this, "Min is " + minSelected, Toast.LENGTH_SHORT).show();
                Toast.makeText(CalendarActivity.this, "am or pm ---> " + ampmSelected, Toast.LENGTH_SHORT).show();

            }
        });

        //Responsible for the dialog
        final AlertDialog.Builder mAlertDialog = new AlertDialog.Builder(CalendarActivity.this);

        //mAlertDialog.setCanceledOnTouchOutside(true);
        //Sets the Linear Layout
        mAlertDialog.setView(hello);
        //Show dialog
        mAlertDialog.show();


    }

    private void createEvent()
    {
        int month;
        switch (monthSelected) {

            case ("January"):
                month = 0;
                break;
            case ("February"):
                month = 1;
                break;
            case ("March"):
                month = 2;
                break;
            case ("April"):
                month = 3;
                break;
            case ("May"):
                month = 4;
                break;
            case ("June"):
                month = 5;
                break;
            case ("July"):
                 month = 6;
                break;
            case ("August"):
                month = 7;
                break;
            case ("September"):
                month = 8;
                break;
            case ("October"):
                month = 9;
                break;
            case ("November"):
                month = 10;
                break;
            case ("December"):
                month = 11;
                break;

        }

//        Calendar startTime = Calendar.getInstance();
//        startTime.set(Calendar.HOUR_OF_DAY, 3);
//        startTime.set(Calendar.MINUTE, 0);
//        startTime.set(Calendar.MONTH, newMonth-1);
//        startTime.set(Calendar.YEAR, newYear);
//        Calendar endTime = (Calendar) startTime.clone();
//        endTime.add(Calendar.HOUR, 1);
//        endTime.set(Calendar.MONTH, newMonth-1);
//        WeekViewEvent event = new WeekViewEvent(1, getEventTitle(startTime), startTime, endTime);
//        event.setColor(getResources().getColor(R.color.teal));
//        events.add(event);

    }


    private void saveToParse()
    {
        event = new ParseObject("Event");
        event.put("Hour", hourSelected);
        event.put("Min", minSelected);
        event.put("Day", daySelected);
        event.put("AMPM", ampmSelected);
        event.put("Color", colorSelected);
        event.put("EventTitle", eventTitleString);
        event.put("EventDetails", eventDetailsString);
        event.saveInBackground();

    }

    // get the selected dropdown list value
    public void addListenerOnButton(View myView) {

        /*
        Make spinner objects for each spinner. Connect it to the XML in myView, otherwise
        it will look for it activity_calendar
         */
        Spinner spinnerHour = (Spinner) myView.findViewById(R.id.hourSpinner);
        Spinner spinnerMin = (Spinner) myView.findViewById(R.id.minute_spinner);
        Spinner spinnerDay = (Spinner) myView.findViewById(R.id.day_spinner);
        Spinner spinnerAmPm = (Spinner) myView.findViewById(R.id.am_pm_spinner);
        Spinner spinnerColor = (Spinner) myView.findViewById(R.id.color_spinner);
        Spinner spinnerMonth = (Spinner) myView.findViewById(R.id.month_spinner);




        //Create a listener for each spinner.

        spinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                monthSelected = parent.getSelectedItem().toString();
                Toast.makeText(CalendarActivity.this, "month is " + monthSelected, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        spinnerHour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                hourSelected = parent.getSelectedItem().toString();
                Toast.makeText(CalendarActivity.this, "hour is " + hourSelected, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        spinnerMin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                minSelected = parent.getSelectedItem().toString();
                Toast.makeText(CalendarActivity.this, "Min is " + minSelected, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        spinnerDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                daySelected = parent.getSelectedItem().toString();
                Toast.makeText(CalendarActivity.this, "day is " + daySelected, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        spinnerColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                colorSelected = parent.getSelectedItem().toString();
                Toast.makeText(CalendarActivity.this, "color is " + colorSelected, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        spinnerAmPm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ampmSelected = parent.getSelectedItem().toString();
                Toast.makeText(CalendarActivity.this, "am or pm ---> " + ampmSelected, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });






    }
    @Override
    public List<WeekViewEvent> onMonthChange(int newYear, int newMonth) {

        // Populate the week view with some events.
        List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();

        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 12);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        Calendar endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR, 4);
        endTime.set(Calendar.MONTH, newMonth-1);
        WeekViewEvent event = new WeekViewEvent(1, "Jacob's Bar Mitzvah", startTime, endTime);
        event.setColor(getResources().getColor(R.color.MaterialBlue));
        events.add(event);


        /*Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 12);
        startTime.set(Calendar.DAY_OF_MONTH,28);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        //Calendar endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR, 4);
        endTime.set(Calendar.MONTH, newMonth-1);
        event = new WeekViewEvent(1, "Kevin's dentist appointment", startTime, endTime);
        event.setColor(getResources().getColor(R.color.MaterialRed));
        events.add(event); */


        startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 14);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        startTime.set(Calendar.DAY_OF_MONTH,27);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 2);
        endTime.set(Calendar.MINUTE, 30);
        endTime.set(Calendar.MONTH, newMonth - 1);
        event = new WeekViewEvent(10, "Anthony's haircut", startTime, endTime);
        event.setColor(getResources().getColor(R.color.MaterialTeal));
        events.add(event);



        startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 14);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        startTime.set(Calendar.DAY_OF_MONTH,28);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 2);
        endTime.set(Calendar.MINUTE, 30);
        endTime.set(Calendar.MONTH, newMonth - 1);
        event = new WeekViewEvent(10, "Kevin's NBA debut", startTime, endTime);
        event.setColor(getResources().getColor(R.color.MaterialTeal));
        events.add(event);


        /*startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 4);
        startTime.set(Calendar.MINUTE, 20);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 5);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(10, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.black));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 5);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 2);
        endTime.set(Calendar.MONTH, newMonth-1);
        event = new WeekViewEvent(2, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.grey));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 5);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        startTime.add(Calendar.DATE, 1);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 3);
        endTime.set(Calendar.MONTH, newMonth - 1);
        event = new WeekViewEvent(3, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.pink));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 15);
        startTime.set(Calendar.HOUR_OF_DAY, 3);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 3);
        event = new WeekViewEvent(4, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.material_blue_500));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 1);
        startTime.set(Calendar.HOUR_OF_DAY, 3);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 3);
        event = new WeekViewEvent(5, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.yellow));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, startTime.getActualMaximum(Calendar.DAY_OF_MONTH));
        startTime.set(Calendar.HOUR_OF_DAY, 15);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 3);
        event = new WeekViewEvent(5, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.yellow));
        events.add(event);*/

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
}


