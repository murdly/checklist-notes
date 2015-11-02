package com.example.arkadiuszkarbowy.tasklog.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.arkadiuszkarbowy.tasklog.scopes.PerApp;
import com.example.arkadiuszkarbowy.tasklog.util.Conversion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by arkadiuszkarbowy on 28/10/15.
 */
@PerApp
public class TasksDataSource {
    private static final String NOTE_TYPE_TODO = "todo";
    private static final String NOTE_TYPE_DONE = "done";
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;


    @Inject
    public TasksDataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Note createNote(Date deadline, Date reminder, ArrayList<String> text) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_TYPE, NOTE_TYPE_TODO);
        values.put(SQLiteHelper.COLUMN_DEADLINE, Conversion.persistDate(deadline));
        values.put(SQLiteHelper.COLUMN_REMINDER, Conversion.persistDate(reminder));
        long insertId = database.insert(SQLiteHelper.TABLE_NOTES, null,
                values);

        ArrayList<Task> tasks = createTasksForNote(text, insertId);

//        Cursor cursor = database.query(SQLiteHelper.TABLE_NOTES,
//                SQLiteHelper.allColumnsNotes, SQLiteHelper.COLUMN_ID_NOTE + " = " + insertId, null,
//                null, null, null);
//        cursor.moveToFirst();
//        Note newNote = cursorToNote(cursor);
//        cursor.close();
//        return newNote;
        return null;
    }

    private ArrayList<Task> createTasksForNote(ArrayList<String> textTask, long insertId) {
        for(String text: textTask) {
            ContentValues values = new ContentValues();
            values.put(SQLiteHelper.COLUMN_FRID_NOTE, insertId);
            values.put(SQLiteHelper.COLUMN_TEXT, text);
            values.put(SQLiteHelper.COLUMN_ISDONE, 0);
            database.insert(SQLiteHelper.TABLE_TASKS, null, values);
        }
        return null;
    }


    public List<Note> getTodoNotes() {
        Log.d("TaskDatSource", "getTodoNotes");

        String table = SQLiteHelper.TABLE_NOTES;
        String[] columns = SQLiteHelper.allColumnsNotes;
        String where =  SQLiteHelper.COLUMN_TYPE + " = ?";
        String[] args = {NOTE_TYPE_TODO};

        Cursor cursor = database.query(table, columns, where, args, null, null, null);

        return cursorToNoteList(cursor);
    }

    private List<Note> cursorToNoteList(Cursor cursor){
        List<Note> notes = new ArrayList<Note>();

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Note note = cursorToNote(cursor);
            note.setTasks(getTasksForNote(note.getId()));
            notes.add(note);
            cursor.moveToNext();
        }

        cursor.close();
        return notes;
    }

    public List<Note> getAllNotes() {
        List<Note> notes = new ArrayList<Note>();

        Cursor cursor = database.query(SQLiteHelper.TABLE_NOTES,
                SQLiteHelper.allColumnsNotes, null, null, null, null, null);

        return cursorToNoteList(cursor);
    }

    private ArrayList<Task> getTasksForNote(long idNote) {
        ArrayList<Task> tasks = new ArrayList<>();

        Cursor cursor = database.query(SQLiteHelper.TABLE_TASKS, SQLiteHelper.allColumnsTasks, SQLiteHelper
                .COLUMN_FRID_NOTE + " = " + idNote, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Task task = cursorToTask(cursor);
            tasks.add(task);
            cursor.moveToNext();
        }
        return tasks;
    }

    private Task cursorToTask(Cursor cursor) {
        Task task = new Task();
        task.setId(cursor.getInt(cursor.getColumnIndex(SQLiteHelper.COLUMN_ID_TASK)));
        task.setIsDone(cursor.getInt(cursor.getColumnIndex(SQLiteHelper.COLUMN_ISDONE)));
        task.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_TEXT)));
        return task;
    }

    private Note cursorToNote(Cursor cursor) {
        Note note = new Note();
        note.setId(cursor.getInt(cursor.getColumnIndex(SQLiteHelper.COLUMN_ID_NOTE)));
        note.setType(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_TYPE)));
        note.setDeadline(Conversion.loadDate(cursor, cursor.getColumnIndex(SQLiteHelper.COLUMN_DEADLINE)));
        note.setReminder(Conversion.loadDate(cursor, cursor.getColumnIndex(SQLiteHelper.COLUMN_REMINDER)));
        return note;
    }

    public List<Note> getDoneTasks() {

        Log.d("TaskDatSource", "getDoneTasks");

        return new ArrayList<>();
    }




    //    public void deleteComment(TaskNote taskNote) {
//        long id = taskNote.getId();
//        System.out.println("Comment deleted with id: " + id);
//        database.delete(SQLiteHelper.TABLE_COMMENTS, SQLiteHelper.COLUMN_ID
//                + " = " + id, null);
//    }


//
//
//
//    public void deleteMyPlaceById(long id) {
//        System.out.println("MyPlace deleted with id: " + id);
//        mDatabase.delete(SQLiteHelper.TABLE_PLACES, SQLiteHelper.COLUMN_ID
//                + " = " + id, null);
//    }


}