package com.example.arkadiuszkarbowy.tasklog.view.interactors;

/**
 * Created by arkadiuszkarbowy on 25/11/15.
 */
public interface OnTaskInteractionListener {
    void onChecked(long id);
    void onUnchecked(long id);
}