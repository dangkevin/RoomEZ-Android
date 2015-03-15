package com.example.anthonygrisaffi.roomez;

import android.os.Bundle;

/**
 * Created by KD on 3/14/15.
 */
public class CalendarValues {
private int mon2;
private int day2;



    protected void onCreate(Bundle savedInstanceState) {
        CreateCalendarEvent hello = new CreateCalendarEvent();
        mon2 = hello.getMonth();

        day2 = hello.getDay();
    }




    public int getMonCalVal(){
        return mon2;
    }

    public int getDayCalVal(){
        return day2;
    }













}


