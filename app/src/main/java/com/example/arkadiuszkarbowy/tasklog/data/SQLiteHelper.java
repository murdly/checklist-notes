package com.example.arkadiuszkarbowy.tasklog.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by arkadiuszkarbowy on 28/10/15.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_TASKS = "tasks";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_LISTID = "groupid";
    public static final String COLUMN_ISDONE= "isdone";
    public static final String COLUMN_DEADLINE = "deadline";
    public static final String COLUMN_REMINDER = "reminder";
    public static final String COLUMN_TEXT = "text";

    public static String[] allColumns = { COLUMN_ID, COLUMN_LISTID, COLUMN_ISDONE, COLUMN_DEADLINE,
            COLUMN_REMINDER, COLUMN_TEXT };

    private static final String DATABASE_NAME = "tasknotes.db";
    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_CREATE = "create table "
            + TABLE_TASKS + "(" +
            COLUMN_ID + " integer primary key autoincrement, " +
            COLUMN_LISTID + " integer, " +
            COLUMN_ISDONE + " integer, " +
            COLUMN_DEADLINE + " integer, " +
            COLUMN_REMINDER + " integer, " +
            COLUMN_TEXT + " text not null);";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
        onCreate(db);
    }

}