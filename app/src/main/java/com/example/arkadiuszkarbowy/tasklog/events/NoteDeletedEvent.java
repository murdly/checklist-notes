package com.example.arkadiuszkarbowy.tasklog.events;

/**
 * Created by arkadiuszkarbowy on 13/11/15.
 */
public class NoteDeletedEvent {
    public long id;
    public int position;
    public NoteDeletedEvent(long id, int position) {
        this.id = id;
        this.position = position;
    }
}
