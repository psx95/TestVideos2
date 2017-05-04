package com.psx.androidcourseproject.model;

/**
 * Created by Pranav on 04-05-2017.
 * A simple POJO for event
 */

public class Event {
    String event_name, event_location, event_time;
    String event_month, event_date, event_year;

    public Event (){
        // required empty constructor
    }

    public Event(String event_name, String event_location, String event_time, String event_month, String event_date, String event_year) {
        this.event_name = event_name;
        this.event_location = event_location;
        this.event_time = event_time;
        this.event_month = event_month;
        this.event_date = event_date;
        this.event_year = event_year;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_location() {
        return event_location;
    }

    public void setEvent_location(String event_location) {
        this.event_location = event_location;
    }

    public String getEvent_time() {
        return event_time;
    }

    public void setEvent_time(String event_time) {
        this.event_time = event_time;
    }

    public String getEvent_month() {
        return event_month;
    }

    public void setEvent_month(String event_month) {
        this.event_month = event_month;
    }

    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }

    public String getEvent_year() {
        return event_year;
    }

    public void setEvent_year(String event_year) {
        this.event_year = event_year;
    }
}
