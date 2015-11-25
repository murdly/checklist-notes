package com.example.arkadiuszkarbowy.tasklog.data;

import java.util.Currency;

/**
 * Created by arkadiuszkarbowy on 25/11/15.
 */
public enum NoteType {
    TODO("todo"),
    DONE("done");

    private final String type;
    private NoteType(String type){
        this.type = type;
    }

    public static NoteType getTypeForString(String s){
        if(s.equals(TODO.type)) return TODO;
        else if(s.equals(DONE.type)) return DONE;

        return TODO;
    }

    @Override
    public String toString() {
        return type;
    }
}
