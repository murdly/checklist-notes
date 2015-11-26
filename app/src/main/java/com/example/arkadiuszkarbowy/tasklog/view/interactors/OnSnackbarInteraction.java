package com.example.arkadiuszkarbowy.tasklog.view.interactors;

import com.example.arkadiuszkarbowy.tasklog.data.Note;

/**
 * Created by arkadiuszkarbowy on 26/11/15.
 */
public interface OnSnackbarInteraction {
    void restoreCopy(Note note, int currentLocation);
    void deletePermanently(long id);
}
