package com.example.arkadiuszkarbowy.tasklog.data;

/**
 * Created by arkadiuszkarbowy on 02/11/15.
 */
public class Task {
    private long id;
    private String text;
    private boolean isDone;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
}
