package com.example.arkadiuszkarbowy.tasklog.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arkadiuszkarbowy on 29/10/15.
 */
public class TaskNote {
    public static final int SIMPLE_TASK_ITEM = 0;
    public static final int LIST_TASK_ITEM = 1;

    private List<Task> mListTasks;
    private Task mSimpleTask;

    public TaskNote(Task simpleTask) {
        mSimpleTask = simpleTask;
    }

    public TaskNote(List<Task> listTasks) {
        this.mListTasks = listTasks;
    }

    public int getType() {
        return (mSimpleTask != null) ? SIMPLE_TASK_ITEM : LIST_TASK_ITEM;
    }

    public String getTaskContent() {
        return mSimpleTask.getText();
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.mListTasks = tasks;
    }

    public List<Task> getTaskList() {
        return mListTasks;
    }
}
