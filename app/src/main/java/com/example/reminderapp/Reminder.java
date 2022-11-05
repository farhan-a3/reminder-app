package com.example.reminderapp;

public class Reminder {

    String name;
    String date;
    int id;

    public Reminder() {
        name = "default";
        date = "default";
        id = 0;
    }

    public Reminder(Reminder other) {
        this.name = other.name;
        this.date = other.date;
        this.id = other.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
