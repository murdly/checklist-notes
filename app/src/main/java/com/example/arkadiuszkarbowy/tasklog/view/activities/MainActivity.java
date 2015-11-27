package com.example.arkadiuszkarbowy.tasklog.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.arkadiuszkarbowy.tasklog.R;
import com.example.arkadiuszkarbowy.tasklog.events.ClearHistoryEvent;
import com.example.arkadiuszkarbowy.tasklog.util.BusProvider;
import com.example.arkadiuszkarbowy.tasklog.view.adapters.MyPagerAdapter;
import com.example.arkadiuszkarbowy.tasklog.view.fragments.TabFragment;
import com.example.arkadiuszkarbowy.tasklog.view.interactors.SnackbarHolder;
import com.example.arkadiuszkarbowy.tasklog.view.interactors.SnackbarInteractor;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;

/**
 * Created by arkadiuszkarbowy on 28/10/15.
 */
public class MainActivity extends AppCompatActivity implements SnackbarHolder {

    @Bind(R.id.coordinatorLayout)
    CoordinatorLayout mCoordinatorLayout;
    @Bind(R.id.viewPager)
    ViewPager mViewPager;
    @Bind(R.id.tabLayout)
    TabLayout mTabLayout;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.fab)
    FloatingActionButton mFab;
    @BindString(R.string.title_toolbar)
    String mTitle;
    @BindString(R.string.todo)
    String mTodo;
    @BindString(R.string.done)
    String mDone;
    @BindString(R.string.clear)
    String mClear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setUpToolbar();

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CreateNoteDialogActivity.class);
                startActivity(i);
            }
        });

        initViewPagerAndTabs();
    }

    private void initViewPagerAndTabs() {
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager()); //todo inject
        TabFragment mTodoFragment = TabFragment.createInstance(TabFragment.TAB_TODO);
        TabFragment mDoneFragment = TabFragment.createInstance(TabFragment.TAB_DONE);
        pagerAdapter.addFragment(mTodoFragment, mTodo);
        pagerAdapter.addFragment(mDoneFragment, mDone);
        mViewPager.setAdapter(pagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setOnTabSelectedListener(mTabSelectedListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDeleteSnackbar(SnackbarInteractor interactor) {
        makeSnackbar(getString(R.string.delete_note), getString(R.string.restore), interactor).show();
    }

    private Snackbar makeSnackbar(String msg, String action, SnackbarInteractor interactor) {
        return Snackbar.make(mCoordinatorLayout, msg, Snackbar.LENGTH_LONG)
                .setAction(action, interactor)
                .setCallback(interactor);
    }

    private TabLayout.OnTabSelectedListener mTabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            mViewPager.setCurrentItem(tab.getPosition());
            if (tab.getPosition() == 1) {
                if (mToolbar.getMenu().size() == 0)
                    mToolbar.inflateMenu(R.menu.menu_tab_done);
            } else {
                mToolbar.getMenu().clear();
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };

    private void setUpToolbar() {
        mToolbar.setTitle(mTitle);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getTitle().equals(mClear)) {
                    BusProvider.getBus().post(new ClearHistoryEvent());
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}