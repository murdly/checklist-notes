package com.example.arkadiuszkarbowy.tasklog.scopes;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by arkadiuszkarbowy on 28/10/15.
 */
@Scope
@Retention(RUNTIME) public @interface PerActivity {}
