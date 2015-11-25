package com.example.arkadiuszkarbowy.tasklog.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.arkadiuszkarbowy.tasklog.R;
import com.example.arkadiuszkarbowy.tasklog.di.AndroidApplication;
import com.example.arkadiuszkarbowy.tasklog.data.Note;
import com.example.arkadiuszkarbowy.tasklog.presenters.NotesTodoPresenter;
import com.example.arkadiuszkarbowy.tasklog.view.TodoView;
import com.example.arkadiuszkarbowy.tasklog.view.adapters.RecyclerAdapter;
import com.example.arkadiuszkarbowy.tasklog.view.adapters.TaskListAdapter;
import com.example.arkadiuszkarbowy.tasklog.view.interactors.OnTaskInteractionListener;

import java.util.List;

import javax.inject.Inject;

public class TodoFragment extends TabFragment implements TodoView {

    @Inject
    NotesTodoPresenter mTodoPresenter;
    protected RecyclerAdapter mTodoAdapter;

    public TodoFragment(){
        mTodoAdapter = new RecyclerAdapter();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialize();
    }

    private void initialize() {
        AndroidApplication.getComponent(getActivity()).inject(this);
        mTodoPresenter.setView(this);
        mTodoPresenter.loadNoteList();
        mTodoAdapter.setOnTaskInteractionListener(mTaskListener);
    }

    private OnTaskInteractionListener mTaskListener = new OnTaskInteractionListener() {
        @Override
        public void onChecked(long id) {
            Log.d("Todo", "checked " + id);
            mTodoPresenter.taskChecked(id);
        }

        @Override
        public void onUnchecked(long id) {
            Log.d("Todo", "unchecked " + id);
            mTodoPresenter.taskUnchecked(id);

        }
    };

    @Override
    public void setData(List<Note> notes) {
        mTodoAdapter.setNotes(notes);
        setUpRecyclerView();
    }

    @Override
    public void addNote() {
        mTodoAdapter.notifyDataSetChanged();
        mRecyclerView.scrollToPosition(0);
    }

    @Override
    public void remove(int position) {
        mTodoAdapter.notifyItemRemoved(position);
        mTodoAdapter.notifyDataSetChanged();
    }

    @Override
    public void showToastBlankNote() {
        Toast.makeText(getActivity() , getResources().getString(R.string.blank), Toast.LENGTH_SHORT).show();
    }

    @Override
    public RecyclerAdapter getAdapter() {
        return mTodoAdapter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mTodoPresenter.onDestroy();
    }

}