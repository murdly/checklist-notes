package com.example.arkadiuszkarbowy.tasklog.util;

import android.database.Cursor;

import java.util.Date;

/**
 * Created by arkadiuszkarbowy on 29/10/15.
 */
public class Conversion {

    public static Long persistDate(Date date) {
        if (date != null) {
            return date.getTime();
        }
        return null;
    }

    public static Date loadDate(Cursor cursor, int index) {
        if (cursor.isNull(index)) {
            return null;
        }
        return new Date(cursor.getLong(index));
    }
}
