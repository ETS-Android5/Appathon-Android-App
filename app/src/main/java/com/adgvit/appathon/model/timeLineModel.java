package com.adgvit.appathon.model;

import java.sql.Timestamp;
import java.util.Date;

public class timeLineModel {
    public String _id;
    public String name;
    public int day;
    public Timestamp date;
    public String description;
    public boolean onGoing;
    public boolean isCompleted;

    public timeLineModel()
    {

    }

    public timeLineModel(String _id, String name, int day, Timestamp date, String description, boolean onGoing, boolean isCompleted) {
        this._id = _id;
        this.name = name;
        this.day = day;
        this.date = date;
        this.description = description;
        this.onGoing = onGoing;
        this.isCompleted = isCompleted;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isOnGoing() {
        return onGoing;
    }

    public void setOnGoing(boolean onGoing) {
        this.onGoing = onGoing;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
