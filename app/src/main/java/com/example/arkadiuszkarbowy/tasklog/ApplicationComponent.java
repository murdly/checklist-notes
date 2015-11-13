package com.example.arkadiuszkarbowy.tasklog;

import android.app.Application;
import android.content.Context;

import com.example.arkadiuszkarbowy.tasklog.data.DataModule;
import com.example.arkadiuszkarbowy.tasklog.note.CreateNoteDialogActivity;
import com.example.arkadiuszkarbowy.tasklog.scopes.PerApp;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by arkadiuszkarbowy on 28/10/15.
 */
@PerApp
@Component(
        modules = {
                ApplicationModule.class,
                DataModule.class
        })
public interface ApplicationComponent {
    void inject(TabFragment fragment);
    void inject(CreateNoteDialogActivity activity);

//    Application application();
    Context context();
}
