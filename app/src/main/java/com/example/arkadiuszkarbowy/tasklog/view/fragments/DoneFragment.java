package com.example.arkadiuszkarbowy.tasklog.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.arkadiuszkarbowy.tasklog.di.AndroidApplication;
import com.example.arkadiuszkarbowy.tasklog.data.Note;
import com.example.arkadiuszkarbowy.tasklog.presenters.NotesDonePresenter;
import com.example.arkadiuszkarbowy.tasklog.view.DoneView;

import java.util.List;

import javax.inject.Inject;

public class DoneFragment extends TabFragment implements DoneView {

    @Inject
    NotesDonePresenter mNotesDonePresenter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialize();
    }

    private void initialize() {
        AndroidApplication.getComponent(getActivity()).inject(this);
        mNotesDonePresenter.setView(this);
        mNotesDonePresenter.loadDoneList();
    }

    @Override
    public void setData(List<Note> notes) {
        mData = notes;
        setUpRecyclerView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mNotesDonePresenter.onDestroy();
    }
}