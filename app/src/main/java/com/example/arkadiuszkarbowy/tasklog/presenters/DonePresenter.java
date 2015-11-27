package com.example.arkadiuszkarbowy.tasklog.presenters;

import com.example.arkadiuszkarbowy.tasklog.events.NoteCreatedEvent;
import com.example.arkadiuszkarbowy.tasklog.events.NoteDeletedEvent;
import com.example.arkadiuszkarbowy.tasklog.events.NoteDoneEvent;

/**
 * Created by arkadiuszkarbowy on 16/11/15.
 */
public interface DonePresenter extends Presenter {
     void noteDone(NoteDoneEvent event);

     void clearDoneNotes();
}
