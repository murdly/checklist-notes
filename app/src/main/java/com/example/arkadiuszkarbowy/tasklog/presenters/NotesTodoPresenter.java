package com.example.arkadiuszkarbowy.tasklog.presenters;

import com.example.arkadiuszkarbowy.tasklog.data.Note;
import com.example.arkadiuszkarbowy.tasklog.data.TasksDataSource;
import com.example.arkadiuszkarbowy.tasklog.events.NoteCreatedEvent;
import com.example.arkadiuszkarbowy.tasklog.events.NoteDeletedEvent;
import com.example.arkadiuszkarbowy.tasklog.view.TodoView;
import com.example.arkadiuszkarbowy.tasklog.view.custom.TaskRowLayout;
import com.example.arkadiuszkarbowy.tasklog.view.interactors.OnSnackbarInteraction;
import com.example.arkadiuszkarbowy.tasklog.view.interactors.SnackbarInteractor;

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
    private SnackbarInteractor mOnDelete;

    @Inject
    public NotesTodoPresenter(TasksDataSource data) {
        mDataSource = data;
        mOnDelete = new SnackbarInteractor(mSnackbarInteractionFinished);
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
        } else if (isComplete(event.taskEntries)) {
            mView.showToastCompletedNote();
        } else {
            mDataSource.open();
            Note note = mDataSource.createNote(event.taskEntries, event.deadlineCalendar, event.alarmCalendar);
            mDataSource.close();
            mData.add(note);
            mView.addNote(0);
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

    private boolean isComplete(LinkedHashMap<Integer, TaskRowLayout.Entry> taskEntries) {
        for (int i = 0; i < taskEntries.size(); i++) {
            if (!taskEntries.get(i).isNoteChecked)
                return false;
        }
        return true;
    }

    @Override
    public void noteDeleted(NoteDeletedEvent event) {
        int position = findNotePositionById(event.id);
        Note copy = mData.remove(position);
        mOnDelete.saveNote(copy, position);
        mView.remove(position);
        mView.showOnDeleteSnackbar(mOnDelete);
    }

    private int findNotePositionById(long id) {
        for (int i = 0; i < mData.size(); i++) {
            if (mData.get(i).getId() == id)
                return i;
        }
        return -1;
    }

    private OnSnackbarInteraction mSnackbarInteractionFinished = new OnSnackbarInteraction() {
        @Override
        public void restoreCopy(Note note, int currentLocation) {
            mData.add(currentLocation, note);
            mView.addNote(currentLocation);
        }

        @Override
        public void deletePermanently(long id) {
            mDataSource.open();
            mDataSource.delete(id);
            mDataSource.close();
        }
    };

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