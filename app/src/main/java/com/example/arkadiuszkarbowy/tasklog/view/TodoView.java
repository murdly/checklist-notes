package com.example.arkadiuszkarbowy.tasklog.view;

import com.example.arkadiuszkarbowy.tasklog.data.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arkadiuszkarbowy on 16/11/15.
 */
public interface TodoView {

    void setData(List<Note> notes);

    void addNote();

    void remove(int position);

    void showToastBlankNote();

}
