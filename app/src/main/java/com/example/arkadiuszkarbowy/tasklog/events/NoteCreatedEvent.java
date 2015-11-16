package com.example.arkadiuszkarbowy.tasklog.events;

import java.util.Calendar;
import java.util.LinkedHashMap;

/**
 * Created by arkadiuszkarbowy on 13/11/15.
 */
public class NoteCreatedEvent {

    public LinkedHashMap<String, Boolean> taskEntries;
    public Calendar deadlineCalendar, alarmCalendar;


    public NoteCreatedEvent(LinkedHashMap<String, Boolean> entries, Calendar deadlineCalendar, Calendar alarmCalendar) {
        taskEntries = entries;
        this.deadlineCalendar = deadlineCalendar;
        this.alarmCalendar = alarmCalendar;
    }
}
