package com.example.arkadiuszkarbowy.tasklog.data;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by arkadiuszkarbowy on 28/10/15.
 */
public class Note {
    private long id;
    private String type;
    private Date deadline;
    private Date reminder;
    private ArrayList<Task> tasks;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getReminder() {
        return reminder;
    }

    public void setReminder(Date reminder) {
        this.reminder = reminder;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
}