package com.example.arkadiuszkarbowy.tasklog.view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.arkadiuszkarbowy.tasklog.R;
import com.example.arkadiuszkarbowy.tasklog.view.adapters.RecyclerAdapter;
import com.example.arkadiuszkarbowy.tasklog.data.Note;
import com.example.arkadiuszkarbowy.tasklog.view.custom.EmptyStateRecycler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TabFragment extends Fragment {
    private static final int GRID_COLUMNS = 2;
    public final static String TAB_TYPE_KEY = "TabFragment$TabType";
    public static final int TAB_TODO = 0;
    public static final int TAB_DONE = 1;


    @Bind(R.id.recyclerView)
    EmptyStateRecycler mRecyclerView;
    @Bind(R.id.emptyView)
    TextView mEmptyView;

    protected RecyclerAdapter mAdapter;
    protected List<Note> mData;

    public static TabFragment createInstance(int tabType) {
        TabFragment tabFragment;

        if (tabType == TAB_TODO)
            tabFragment = new TodoFragment();
        else
            tabFragment = new DoneFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(TAB_TYPE_KEY, tabType);
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    protected void setUpRecyclerView() {
        mAdapter = new RecyclerAdapter(getActivity(), mData);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(GRID_COLUMNS, StaggeredGridLayoutManager
                .VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setEmptyView(mEmptyView);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}