package com.example.arkadiuszkarbowy.tasklog.note;

/**
 * Created by arkadiuszkarbowy on 13/11/15.
 */
public class NoteDeletedEvent {
    public long id;
    public int position;
    public NoteDeletedEvent(long id, int positon) {
        this.id = id;
        this.position = positon;
    }
}
