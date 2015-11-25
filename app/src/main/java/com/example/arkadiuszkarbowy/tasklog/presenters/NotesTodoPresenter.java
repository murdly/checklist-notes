package com.example.arkadiuszkarbowy.tasklog.presenters;

import android.util.Log;

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
import java.util.List;

import javax.inject.Inject;

/**
 * Created by arkadiuszkarbowy on 16/11/15.
 */
public class NotesTodoPresenter implements TodoPresenter {

    private TodoView mView;
    private TasksDataSource mDataSource;
    private List<Note> mData;


    @Inject
    public NotesTodoPresenter(TasksDataSource data) {
        mDataSource = data;
    }

    public void setView(TodoView view) {
        mView = view;
    }

    public void loadNoteList() {
        mDataSource.open();
        ArrayList<Note> notes = mDataSource.getTodoNotes();
        mDataSource.close();
        mData = notes;
        mView.setData(mData);
    }

    @Override
    public void noteInserted(NoteCreatedEvent event) {
        if (!hasContent(event.taskEntries)) {
            mView.showToastBlankNote();
        } else {
            mDataSource.open();
            Note note = mDataSource.createNote(event.taskEntries, event.deadlineCalendar, event.alarmCalendar);
            mDataSource.close();
            mData.add(note);
            mView.addNote();
        }
    }

    private boolean hasContent(LinkedHashMap<Integer, TaskRowLayout.Entry> taskEntries) {
        boolean hasContent = false;
        int i = 0;
        while (!hasContent && i < taskEntries.size()) {
            hasContent = taskEntries.get(i).hasContent();
            i++;
        }

        return hasContent;
    }

    @Override
    public void noteDeleted(NoteDeletedEvent event) {
        mDataSource.open();
        mDataSource.delete(event.id);
        mDataSource.close();
        int position = findMessagePositionById(event.id);
        mData.remove(position);

        mView.remove(position);
    }

    private int findMessagePositionById(long id) {
        for (int i = 0; i < mData.size(); i++) {
            if (mData.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void taskChecked(long id) {
        mDataSource.open();
        mDataSource.updateTaskDone(id);
        mDataSource.close();
    }

    @Override
    public void taskUnchecked(long id) {
        mDataSource.open();
        mDataSource.updateTaskTodo(id);
        mDataSource.close();
    }

    @Override
    public void onDestroy() {
    }
}