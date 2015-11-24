package com.example.arkadiuszkarbowy.tasklog.di;

import android.app.Application;
import android.content.Context;

import com.example.arkadiuszkarbowy.tasklog.scopes.PerApp;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

import dagger.Module;
import dagger.Provides;

/**
 * Created by arkadiuszkarbowy on 28/10/15.
 */
@Module
public class ApplicationModule {
    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @PerApp
    Context provideApplicationContext() {
        return application.getApplicationContext();
    }

    @Provides
    @PerApp
    Bus provideBus() {
        return new Bus(ThreadEnforcer.MAIN);
    }
}
