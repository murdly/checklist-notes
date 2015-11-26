package com.example.arkadiuszkarbowy.tasklog.view.interactors;

import android.support.design.widget.Snackbar;
import android.view.View;

import com.example.arkadiuszkarbowy.tasklog.data.Note;

/**
 * Created by arkadiuszkarbowy on 26/11/15.
 */
public class SnackbarInteractor extends Snackbar.Callback implements View.OnClickListener {
    private boolean mShown = true;
    private Note mCopy;
    private int mPosition;
    private OnSnackbarInteraction mListener;

    public SnackbarInteractor(OnSnackbarInteraction listener) {
        mListener = listener;
    }

    @Override
    public void onClick(View v) {
        mListener.restoreCopy(mCopy, mPosition);
    }


    @Override
    public void onDismissed(Snackbar snackbar, int event) {
        if (mShown) {
            mShown = false;
            if (Snackbar.Callback.DISMISS_EVENT_ACTION != event) {
                mListener.deletePermanently(mCopy.getId());
            }
        }
    }

    @Override
    public void onShown(Snackbar snackbar) {
        mShown = true;
    }

    public void saveNote(Note copy, int position) {
        mCopy = copy;
        mPosition = position;
    }
}
