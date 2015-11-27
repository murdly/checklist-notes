package com.example.arkadiuszkarbowy.tasklog.presenters;

import com.example.arkadiuszkarbowy.tasklog.data.Note;
import com.example.arkadiuszkarbowy.tasklog.data.TasksDataSource;
import com.example.arkadiuszkarbowy.tasklog.events.NoteDoneEvent;
import com.example.arkadiuszkarbowy.tasklog.view.DoneView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by arkadiuszkarbowy on 16/11/15.
 */
public class NotesDonePresenter implements DonePresenter {

    private DoneView mView;
    private TasksDataSource mDataSource;
    private List<Note> mData;

    @Inject
    public NotesDonePresenter(TasksDataSource data) {
        mDataSource = data;
    }

    public void setView(DoneView view) {
        mView = view;
    }

    public void loadDoneList() {
        mDataSource.open();
        List<Note> notes = mDataSource.getDoneNotes();
        mDataSource.close();
        mData = notes;
        mView.setData(mData);
    }

    @Override
    public void noteDone(NoteDoneEvent event) {
        mData.add(event.note);
        mView.addNote(0);
    }

    @Override
    public void clearDoneNotes() {
        if(mData.size() > 0) {
            mDataSource.open();
            mDataSource.clearDoneNotes();
            mDataSource.close();
            mData.clear();
            mView.clearNotes();
        }
    }

    @Override
    public void onDestroy() {
    }
}
