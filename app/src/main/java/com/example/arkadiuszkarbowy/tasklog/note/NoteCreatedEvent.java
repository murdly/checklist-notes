package com.example.arkadiuszkarbowy.tasklog.note;

import com.example.arkadiuszkarbowy.tasklog.data.Note;

/**
 * Created by arkadiuszkarbowy on 13/11/15.
 */
public class NoteCreatedEvent {

    public Note note;

    public NoteCreatedEvent(Note note) {
        this.note = note;
    }

    public Note getNote() {
        return note;
    }
}
