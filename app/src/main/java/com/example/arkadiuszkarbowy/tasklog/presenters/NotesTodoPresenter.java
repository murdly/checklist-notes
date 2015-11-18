package com.example.arkadiuszkarbowy.tasklog.presenters;

import com.example.arkadiuszkarbowy.tasklog.data.Note;
import com.example.arkadiuszkarbowy.tasklog.data.TasksDataSource;
import com.example.arkadiuszkarbowy.tasklog.events.NoteCreatedEvent;
import com.example.arkadiuszkarbowy.tasklog.events.NoteDeletedEvent;
import com.example.arkadiuszkarbowy.tasklog.util.BusProvider;
import com.example.arkadiuszkarbowy.tasklog.view.TodoView;
import com.example.arkadiuszkarbowy.tasklog.view.custom.TaskRowLayout;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.inject.Inject;

/**
 * Created by arkadiuszkarbowy on 16/11/15.
 */
public class NotesTodoPresenter implements Presenter {

    private TodoView mView;
    private TasksDataSource mDataSource;

    @Inject
    public NotesTodoPresenter(TasksDataSource data) {
        mDataSource = data;
        BusProvider.getBus().register(this);
    }

    public void setView(TodoView view) {
        mView = view;
    }

    public void loadNoteList() {
        mDataSource.open();
        ArrayList<Note> notes = mDataSource.getTodoNotes();
        mDataSource.close();
        mView.setData(notes);
    }

    @Subscribe
    public void noteInserted(NoteCreatedEvent event) {
        if(!hasContent(event.taskEntries)){
            mView.showToastBlankNote();
        }else {
            mDataSource.open();
            Note note = mDataSource.createNote(event.taskEntries, event.deadlineCalendar, event.alarmCalendar);
            mDataSource.close();
            mView.addNew(note);
        }
    }

    private boolean hasContent(LinkedHashMap<Integer, TaskRowLayout.Entry> taskEntries) {
        boolean hasContent = false;
        int i = 0;

        while(!hasContent && i<taskEntries.size()) {
            hasContent = taskEntries.get(i).hasContent();
            i++;
        }

        return hasContent;
    }

    @Subscribe
    public void noteDeleted(NoteDeletedEvent event) {
        mDataSource.open();
        mDataSource.delete(event.id);
        mDataSource.close();

        mView.remove(event.position);

    }

    @Override
    public void onDestroy() {
        BusProvider.getBus().unregister(this);
    }


}
