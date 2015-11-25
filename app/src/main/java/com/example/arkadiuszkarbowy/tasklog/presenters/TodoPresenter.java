package com.example.arkadiuszkarbowy.tasklog.presenters;

import com.example.arkadiuszkarbowy.tasklog.events.NoteCreatedEvent;
import com.example.arkadiuszkarbowy.tasklog.events.NoteDeletedEvent;
import com.squareup.otto.Subscribe;

/**
 * Created by arkadiuszkarbowy on 16/11/15.
 */
public interface TodoPresenter extends Presenter {
     void noteInserted(NoteCreatedEvent event);
     void noteDeleted(NoteDeletedEvent event);
     void taskChecked(long id);
     void taskUnchecked(long id);
}
