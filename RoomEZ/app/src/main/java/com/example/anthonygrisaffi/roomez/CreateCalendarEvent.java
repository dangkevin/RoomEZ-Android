package com.example.anthonygrisaffi.roomez;

import android.app.AlertDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import me.drakeet.materialdialog.MaterialDialog;


public class CreateCalendarEvent extends ActionBarActivity {

    DatePicker datePicker;
    TimePicker timePicker;
    Button doneTime;
    Button doneDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_calendar_event);

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR); // get the current year
        int month = cal.get(Calendar.MONTH); // month...
        int day = cal.get(Calendar.DAY_OF_MONTH); // current day in the month

        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);

        Button currDate = (Button) findViewById(R.id.currentDate);
        currDate.setHint(month + "/" + day + "/" + year);

        Button currTime = (Button) findViewById(R.id.currentTime);
        currTime.setHint(hour + ":" + minute);

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
                final AlertDialog.Builder mAlertDialog = new AlertDialog.Builder(CreateCalendarEvent.this);
//                final MaterialDialog mAlertDialog = new MaterialDialog(CreateCalendarEvent.this);

                doneDate = (Button) myView.findViewById(R.id.doneDate);
                datePicker = (DatePicker) myView.findViewById(R.id.date1);

                doneDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        int month = datePicker.getMonth();
                        int day= datePicker.getDayOfMonth();
                        int year= datePicker.getYear();
                       CalendarActivity dateSetting =  new CalendarActivity();
                       dateSetting.setDatePicker(month, day, year);
                   //Toast.makeText(dateSetting, "Date is " + month + "/" + day + "/" + year, Toast.LENGTH_SHORT).show();
                        finish();

                    }
                });



               // mAlertDialog.setCanceledOnTouchOutside(true);
                //Sets the Linear Layout
                mAlertDialog.setView(hello);
                //Show dialog
                mAlertDialog.show();

            }
        });
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
                //        addListenerOnButton(myView);
                //Responsible for the dialog
                final MaterialDialog mAlertDialog = new MaterialDialog(CreateCalendarEvent.this);

                mAlertDialog.setCanceledOnTouchOutside(true);
                //Sets the Linear Layout
                mAlertDialog.setView(hello);
                //Show dialog
                mAlertDialog.show();

            }
        });
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
}
