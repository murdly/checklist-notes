package com.example.arkadiuszkarbowy.tasklog.view;

import com.example.arkadiuszkarbowy.tasklog.data.Note;
import com.example.arkadiuszkarbowy.tasklog.view.interactors.SnackbarInteractor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arkadiuszkarbowy on 16/11/15.
 */
public interface TodoView {

    void setData(List<Note> notes);

    void addNote(int position);

    void remove(int position);

    void showToastBlankNote();

    void showToastCompletedNote();

    void showOnDeleteSnackbar(SnackbarInteractor callback);

    void showToastNoteDone();
}
