package com.example.arkadiuszkarbowy.tasklog.data;

import android.app.Application;
import android.content.Context;

import com.example.arkadiuszkarbowy.tasklog.AndroidApplication;
import com.example.arkadiuszkarbowy.tasklog.scopes.PerApp;

import dagger.Module;
import dagger.Provides;

/**
 * Created by arkadiuszkarbowy on 28/10/15.
 */
@Module
public class DataModule {
//    AndroidApplication app;

//    public DataModule(AndroidApplication androidApplication) {
//        app = androidApplication;
//    }

    @Provides @PerApp
    TasksDataSource provideTasksDataSource(Context c){
        return new TasksDataSource(c);
    }

}
