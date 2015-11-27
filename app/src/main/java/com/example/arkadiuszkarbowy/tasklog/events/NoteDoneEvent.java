package com.example.arkadiuszkarbowy.tasklog.events;

import com.example.arkadiuszkarbowy.tasklog.data.Note;

/**
 * Created by arkadiuszkarbowy on 13/11/15.
 */
public class NoteDoneEvent {
    public Note note;

    public NoteDoneEvent(Note note) {
        this.note = note;
    }
}
