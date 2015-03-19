package com.example.anthonygrisaffi.roomez;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import me.drakeet.materialdialog.MaterialDialog;


public class CreateCalendarEvent extends ActionBarActivity {

    DatePicker datePicker;
    DatePicker datePicker2;
    private TimePicker timePicker;
    private TimePicker timePicker2;
    private static int year;
    private static int month;
    private static int day;
    private static int eMonth;
    private static int eDay;
    private static int eYear;
    private static int currHour;
    private static int currMinute;
    private static int shour;
    private static int ehour;
    private static int sminute;
    private static int eminute;
    private static String eventTitle;
    private EditText eventTitleButton;
    private Button currTime;
    private Button currDate;
    private Button endDate;
    private Button endTime;
    private Button doneDate;
    private Button doneDate2;
    private Button doneTime;
    private Button doneTime2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_calendar_event);

        Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR); // get the current year
        month = cal.get(Calendar.MONTH); // month...
        day = cal.get(Calendar.DAY_OF_MONTH); // current day in the month
        currHour = cal.get(Calendar.HOUR_OF_DAY);
        currMinute = cal.get(Calendar.MINUTE);


        currDate = (Button) findViewById(R.id.currentDate);
        endDate = (Button) findViewById(R.id.endDate);


        endDate.setText("Ending Date");
        currDate.setText("Starting Date");


        currTime = (Button) findViewById(R.id.currentTime);
        endTime = (Button) findViewById(R.id.endTime);

        endTime.setHint(currHour + ":" + currMinute);
        currTime.setHint(currHour + ":" + currMinute);



        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Define a custom layout
                LayoutInflater factory = LayoutInflater.from(CreateCalendarEvent.this);
                //Creates a view and adds buttons (such as Edit Text) from a customized layout
                final View myView2 = factory.inflate(R.layout.date_pop2, null);
                //Linear layout is created
                RelativeLayout hello2 = new RelativeLayout(CreateCalendarEvent.this);
                //Adds the view to the linear layout
                hello2.addView(myView2);
                //        addListenerOnButton(myView);
                //Responsible for the dialog
                AlertDialog.Builder mAlertDialog = new AlertDialog.Builder(CreateCalendarEvent.this);
                final AlertDialog alert2 = mAlertDialog.create();
                //
                // currDate.setHint(month + "/" + day + "/" + year);

                // final MaterialDialog mAlertDialog = new MaterialDialog(CreateCalendarEvent.this);




                doneDate2 = (Button) myView2.findViewById(R.id.doneDate2);
                //Button variable used for onClick for the Date
                datePicker2 = (DatePicker) myView2.findViewById(R.id.date2);

                //Used to finish the Starting Date Picker Activity
                doneDate2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        eMonth= datePicker2.getMonth();
                        eDay = datePicker2.getDayOfMonth();
                        eYear = datePicker2.getYear();
                        endDate.setText(month + 1 + "/" + day + "/" + year);


                        CalendarActivity dateSetting2 = new CalendarActivity();
                        dateSetting2.setDatePicker(eMonth, eDay,eYear);
                        //Toast.makeText(getApplicationContext(), "Date is " + month + "/" + day + "/" + year, Toast.LENGTH_SHORT).show();

                        alert2.dismiss();
                        seteDay(eDay);
                        seteMonth(eMonth);
                        seteYear(eYear);
                        finish();
                    }
                });


                // mAlertDialog.setCanceledOnTouchOutside(true);
                //Sets the Linear Layout
                alert2.setView(hello2);
                //Show dialog
                alert2.show();


            }
        });


        currDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Define a custom layout
                LayoutInflater factory = LayoutInflater.from(CreateCalendarEvent.this);
                //Creates a view and adds buttons (such as Edit Text) from a customized layout
                final View myView = factory.inflate(R.layout.date_pop, null);
                //Linear layout is created
                RelativeLayout hello = new RelativeLayout(CreateCalendarEvent.this);
                //Adds the view to the linear layout
                hello.addView(myView);
                //        addListenerOnButton(myView);
                //Responsible for the dialog
                AlertDialog.Builder mAlertDialog = new AlertDialog.Builder(CreateCalendarEvent.this);
                final AlertDialog alert = mAlertDialog.create();
                //
                // currDate.setHint(month + "/" + day + "/" + year);

                // final MaterialDialog mAlertDialog = new MaterialDialog(CreateCalendarEvent.this);


                doneDate = (Button) myView.findViewById(R.id.doneDate);
                //Button variable used for onClick for the Date
                datePicker = (DatePicker) myView.findViewById(R.id.date1);

                //Used to finish the Starting Date Picker Activity
                doneDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        month = datePicker.getMonth();
                        day = datePicker.getDayOfMonth();
                        year = datePicker.getYear();
                        currDate.setText(month + 1 + "/" + day + "/" + year);


                        CalendarActivity dateSetting = new CalendarActivity();
                        dateSetting.setDatePicker(month, day, year);
                        //Toast.makeText(getApplicationContext(), "Date is " + month + "/" + day + "/" + year, Toast.LENGTH_SHORT).show();

                        alert.dismiss();
                        setMonth(month);
                        setDay(day);
                        setYear(year);


                    }
                });


                // mAlertDialog.setCanceledOnTouchOutside(true);
                //Sets the Linear Layout
                alert.setView(hello);
                //Show dialog
                alert.show();

            }

        });



        //Toast.makeText(getApplicationContext(), "Month is" + getMonth(), Toast.LENGTH_SHORT).show();


        currTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Define a custom layout
                LayoutInflater factory = LayoutInflater.from(CreateCalendarEvent.this);
                //Creates a view and adds buttons (such as Edit Text) from a customized layout
                final View myView = factory.inflate(R.layout.time_pop, null);
                //Linear layout is created
                RelativeLayout hello = new RelativeLayout(CreateCalendarEvent.this);
                //Adds the view to the linear layout
                hello.addView(myView);
                //addListenerOnButton(myView);
                //Responsible for the dialog

                AlertDialog.Builder mAlertDialog = new AlertDialog.Builder(CreateCalendarEvent.this);
                final AlertDialog alert = mAlertDialog.create();

                doneTime = (Button) myView.findViewById(R.id.doneTime);
                //Button variable used for onClick for the Date
                timePicker = (TimePicker) myView.findViewById(R.id.time1);

                doneTime.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        shour = timePicker.getCurrentHour();
                        sminute = timePicker.getCurrentMinute();
                        if(shour > 12) {
                           shour = shour - 12;
                        }
                        currTime.setHint((shour) + ":" + sminute);
                        setStartHour(shour);
                        setStartMinute(sminute);
                        //Toast.makeText(getApplicationContext(), "Date is " + month + "/" + day + "/" + year, Toast.LENGTH_SHORT).show();
                        alert.dismiss();
                    }
                });


               // alert.setCanceledOnTouchOutside(true);
                //Sets the Linear Layout
                alert.setView(hello);
                //Show dialog
                alert.show();

            }
        });

        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Define a custom layout
                LayoutInflater factory = LayoutInflater.from(CreateCalendarEvent.this);
                //Creates a view and adds buttons (such as Edit Text) from a customized layout
                final View myView = factory.inflate(R.layout.time_pop2, null);
                //Linear layout is created
                RelativeLayout hello = new RelativeLayout(CreateCalendarEvent.this);
                //Adds the view to the linear layout
                hello.addView(myView);
                //addListenerOnButton(myView);
                //Responsible for the dialog

                AlertDialog.Builder mAlertDialog = new AlertDialog.Builder(CreateCalendarEvent.this);
                final AlertDialog alert = mAlertDialog.create();

                doneTime2 = (Button) myView.findViewById(R.id.doneTime2);
                //Button variable used for onClick for the Date
                timePicker2 = (TimePicker) myView.findViewById(R.id.time2);

                doneTime2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ehour = timePicker2.getCurrentHour();
                        Toast.makeText(getApplicationContext(), "Ehour is " + ehour, Toast.LENGTH_SHORT).show();
                        eminute = timePicker2.getCurrentMinute();
                        setEndHour(ehour);
                        setEndMinute(eminute);
                        if(ehour > 12) {
                            ehour = ehour - 12;
                        }
                        endTime.setHint(ehour + ":" + eminute);
                        //Toast.makeText(getApplicationContext(), "Date is " + month + "/" + day + "/" + year, Toast.LENGTH_SHORT).show();
                        alert.dismiss();
                    }
                });


                // alert.setCanceledOnTouchOutside(true);
                //Sets the Linear Layout
                alert.setView(hello);
                //Show dialog
                alert.show();

            }
        });




        //Button listener for the Event Title

                eventTitleButton = (EditText) findViewById(R.id.eventTitle);
                final InputMethodManager imm = (InputMethodManager) getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                eventTitleButton.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_DONE) {
                            eventTitle = eventTitleButton.getText().toString().trim();
                            setEventTitle(eventTitle);
                            imm.hideSoftInputFromWindow(eventTitleButton.getWindowToken(), 0);
                            return true;

                        }
                        return false;
                    }
                });


            }


    public void seteMonth(int eMonth){
        this.eMonth = eMonth;
    }

    public int geteMonth(){
        return eMonth;
    }

    public void seteDay(int eDay){

        this.eDay = eDay;

    }

    public int geteDay(){

        return eDay;

    }
    public void setStartHour(int shour){
        this.shour = shour;
    }

    public int getStartHour(){
        return shour;
    }

    public void setStartMinute(int sminute){
        this.sminute = sminute;
    }

    public int getStartMinute(){
        return sminute;
    }

    public void setEndHour(int ehour){
        this.ehour = ehour;
    }

    public int getEndHour(){
        return ehour;
    }

    public void setEndMinute(int eminute){
        this.eminute = eminute;
    }

    public int getEndMinute(){
        return eminute;
    }

    public void seteYear(int eYear){
        this.eYear = eYear;
    }

    public int geteYear()
    {
        return eYear;
    }




    public void setMonth(int month) {
        this.month = month;
    }

    public int getMonth() {
        return month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getDay() {
        return day;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setEventTitle(String eventTitle){
        this.eventTitle = eventTitle;
    }

    public String getEventTitle(){
        return eventTitle;
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_calendar_event, menu);
        return true;
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

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop()
    {
        super.onStop();


    }
}
