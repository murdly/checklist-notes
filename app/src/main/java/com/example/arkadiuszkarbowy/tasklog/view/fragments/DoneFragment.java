package com.example.arkadiuszkarbowy.tasklog.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.arkadiuszkarbowy.tasklog.di.AndroidApplication;
import com.example.arkadiuszkarbowy.tasklog.data.Note;
import com.example.arkadiuszkarbowy.tasklog.events.ClearHistoryEvent;
import com.example.arkadiuszkarbowy.tasklog.events.NoteDoneEvent;
import com.example.arkadiuszkarbowy.tasklog.presenters.NotesDonePresenter;
import com.example.arkadiuszkarbowy.tasklog.util.BusProvider;
import com.example.arkadiuszkarbowy.tasklog.view.DoneView;
import com.example.arkadiuszkarbowy.tasklog.view.adapters.DoneRecyclerAdapter;
import com.example.arkadiuszkarbowy.tasklog.view.adapters.TodoRecyclerAdapter;
import com.squareup.otto.Subscribe;

import java.util.List;

import javax.inject.Inject;

public class DoneFragment extends TabFragment implements DoneView {

    @Inject
    NotesDonePresenter mDonePresenter;
    protected DoneRecyclerAdapter mDoneAdapter;

    public DoneFragment(){
        mDoneAdapter = new DoneRecyclerAdapter();
        BusProvider.getBus().register(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialize();
    }

    private void initialize() {
        AndroidApplication.getComponent(getActivity()).inject(this);
        mDonePresenter.setView(this);
        mDonePresenter.loadDoneList();
        mDoneAdapter.setOnTaskInteractionListener(null);
    }

    @Override
    public void setData(List<Note> notes) {
        mDoneAdapter.setNotes(notes);
        setUpRecyclerView();
    }

    @Subscribe
    public void noteDone(NoteDoneEvent event){
        mDonePresenter.noteDone(event);
    }

    @Subscribe
    public void clearDoneNotes(ClearHistoryEvent event){
        mDonePresenter.clearDoneNotes();
    }

    @Override
    public void addNote(int position) {
        mDoneAdapter.notifyDataSetChanged();
        mRecyclerView.scrollToPosition(position);
    }

    @Override
    public void clearNotes() {
        mDoneAdapter.notifyDataSetChanged();
    }

    @Override
    public DoneRecyclerAdapter getAdapter() {
        return mDoneAdapter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        BusProvider.getBus().unregister(this);
        mDonePresenter.onDestroy();
    }
}