package com.example.arkadiuszkarbowy.tasklog.view;

import com.example.arkadiuszkarbowy.tasklog.data.Note;

import java.util.ArrayList;

/**
 * Created by arkadiuszkarbowy on 16/11/15.
 */
public interface TodoView {

    void setData(ArrayList<Note> notes);

    void addNew(Note note);

    void remove(int position);

}
