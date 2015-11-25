package com.example.arkadiuszkarbowy.tasklog.events;

/**
 * Created by arkadiuszkarbowy on 13/11/15.
 */
public class NoteDeletedEvent {
    public long id;

    public NoteDeletedEvent(long id) {
        this.id = id;
    }
}
