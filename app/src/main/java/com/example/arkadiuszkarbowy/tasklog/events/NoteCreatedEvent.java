package com.example.arkadiuszkarbowy.tasklog.events;

import android.widget.EditText;

import com.example.arkadiuszkarbowy.tasklog.view.custom.Row;
import com.example.arkadiuszkarbowy.tasklog.view.custom.TaskRowLayout;

import java.util.Calendar;
import java.util.LinkedHashMap;

/**
 * Created by arkadiuszkarbowy on 13/11/15.
 */
public class NoteCreatedEvent {

    public LinkedHashMap<Integer, TaskRowLayout.Entry> taskEntries;
    public Calendar deadlineCalendar, alarmCalendar;

    public NoteCreatedEvent(LinkedHashMap<Integer, TaskRowLayout.Entry> entries, Calendar
            deadlineCalendar, Calendar
            alarmCalendar) {
        this.taskEntries = entries;
        this.deadlineCalendar = deadlineCalendar;
        this.alarmCalendar = alarmCalendar;
    }
}
