package com.example.arkadiuszkarbowy.tasklog.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.arkadiuszkarbowy.tasklog.scopes.PerApp;
import com.example.arkadiuszkarbowy.tasklog.util.Conversion;
import com.example.arkadiuszkarbowy.tasklog.view.custom.TaskRowLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by arkadiuszkarbowy on 28/10/15.
 */
@PerApp
public class TasksDataSource {
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

    public Note createNote(LinkedHashMap<Integer, TaskRowLayout.Entry> entries, Calendar mDeadlineCalendar,
                           Calendar mAlarmCalendar) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_TYPE, NoteType.TODO.toString());
        values.put(SQLiteHelper.COLUMN_DEADLINE, Conversion.persistDate(mDeadlineCalendar));
        values.put(SQLiteHelper.COLUMN_REMINDER, Conversion.persistDate(mAlarmCalendar));
        long noteId = database.insert(SQLiteHelper.TABLE_NOTES, null,
                values);

        ArrayList<Task> tasks = new ArrayList<>();
        long availableTaskId = getFirstAvailableTaskId();
        for (Integer i : entries.keySet()) {
            TaskRowLayout.Entry entry = entries.get(i);
            Task task = new Task();
            task.setId(availableTaskId + i);
            task.setIsDone(entry.isNoteChecked);
            task.setText(entry.noteText);
            tasks.add(task);
        }

        createTasks(tasks, noteId);

        Note note = new Note();
        note.setId(noteId);
        note.setTasks(tasks);
        note.setDeadline(mDeadlineCalendar != null ? mDeadlineCalendar.getTime() : null);
        note.setReminder(mAlarmCalendar != null ? mAlarmCalendar.getTime() : null);
        note.setType(NoteType.TODO); //todo sprawdzic checki

        return note;
    }


    private void createTasks(ArrayList<Task> tasks, long noteId) {
        for (Task task : tasks) {
            ContentValues values = new ContentValues();
            values.put(SQLiteHelper.COLUMN_FRID_NOTE, noteId);
            values.put(SQLiteHelper.COLUMN_TEXT, task.getText());
            values.put(SQLiteHelper.COLUMN_ISDONE, task.isDone() ? 1 : 0);
            database.insert(SQLiteHelper.TABLE_TASKS, null, values);
        }
    }

    public ArrayList<Note> getTodoNotes() {

        String table = SQLiteHelper.TABLE_NOTES;
        String[] columns = SQLiteHelper.allColumnsNotes;
        String where = SQLiteHelper.COLUMN_TYPE + " = ?";
        String[] args = {NoteType.TODO.toString()};

        Cursor cursor = database.query(table, columns, where, args, null, null, null);
        Log.d("TaskDatSource", "getTodoNotes " + cursor.getCount());

        return cursorToNoteList(cursor);
    }

    private ArrayList<Note> cursorToNoteList(Cursor cursor) {
        ArrayList<Note> notes = new ArrayList<Note>();

        boolean any = cursor.moveToFirst();
        while (any && !cursor.isAfterLast()) {
            Note note = cursorToNote(cursor);
            note.setTasks(getTasksForNote(note.getId()));
            notes.add(note);
            cursor.moveToNext();
        }

        cursor.close();
        return notes;
    }

    private ArrayList<Task> getTasksForNote(long idNote) {
        ArrayList<Task> tasks = new ArrayList<>();

        Cursor cursor = database.query(SQLiteHelper.TABLE_TASKS, SQLiteHelper.allColumnsTasks, SQLiteHelper
                .COLUMN_FRID_NOTE + " = " + idNote, null, null, null, null, null);
        boolean any = cursor.moveToFirst();
        while (any && !cursor.isAfterLast()) {
            Task task = cursorToTask(cursor);
            tasks.add(task);
            cursor.moveToNext();
        }
        return tasks;
    }

    private Task cursorToTask(Cursor cursor) {
        Task task = new Task();
        task.setId(cursor.getInt(cursor.getColumnIndex(SQLiteHelper.COLUMN_ID_TASK)));
        int isDone = cursor.getInt(cursor.getColumnIndex(SQLiteHelper.COLUMN_ISDONE));
        task.setIsDone(isDone == 1);
        task.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_TEXT)));
        Log.d("cursorToTask()", task.getText() + "-id: " + task.getId() + " -isDone: " + task.isDone());
        return task;
    }

    private Note cursorToNote(Cursor cursor) {
        Note note = new Note();
        note.setId(cursor.getInt(cursor.getColumnIndex(SQLiteHelper.COLUMN_ID_NOTE)));
        note.setType(NoteType.getTypeForString(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_TYPE))));
        note.setDeadline(Conversion.loadDate(cursor, cursor.getColumnIndex(SQLiteHelper.COLUMN_DEADLINE)));
        note.setReminder(Conversion.loadDate(cursor, cursor.getColumnIndex(SQLiteHelper.COLUMN_REMINDER)));
        return note;
    }

    public List<Note> getDoneTasks() {

        Log.d("TaskDatSource", "getDoneTasks");

        return new ArrayList<>();
    }

    public void delete(long id) {

        int tasksRemoved = database.delete(SQLiteHelper.TABLE_TASKS, SQLiteHelper.COLUMN_FRID_NOTE
                + " = " + id, null);

        int notesRemoved = database.delete(SQLiteHelper.TABLE_NOTES, SQLiteHelper.COLUMN_ID_NOTE
                + " = " + id, null);

        Log.d("TaskDataSource", "delete() notes: " + notesRemoved + " with task: " + tasksRemoved);
    }

    public long getFirstAvailableTaskId() {
        long testNoteId = -1;
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_FRID_NOTE, testNoteId);
        values.put(SQLiteHelper.COLUMN_TEXT, "test");
        values.put(SQLiteHelper.COLUMN_ISDONE, -1);
        long lastId =  database.insert(SQLiteHelper.TABLE_TASKS, null, values);
        this.delete(testNoteId);
        return lastId + 1;
    }

    public void updateTaskDone(long id) {
        updateTask(id, 1);
    }

    public void updateTaskTodo(long id) {
        updateTask(id, 0);
    }

    private void updateTask(long id, int isDone) {
        String where = SQLiteHelper.COLUMN_ID_TASK + " = " + id;
        ContentValues args = new ContentValues();
        args.put(SQLiteHelper.COLUMN_ISDONE, isDone);
        database.update(SQLiteHelper.TABLE_TASKS, args, where, null);

        Log.d("TaskDataSource", "updateTask() id: " + id + " isDOne: " + isDone);

    }
}