package com.example.arkadiuszkarbowy.tasklog.data;

import java.util.Date;

/**
 * Created by arkadiuszkarbowy on 28/10/15.
 */
public class Task {
    private long id;
    private long listId;
    private boolean isDone;
    private Date deadline;
    private Date reminder;
    private String text;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getListId() {
        return listId;
    }

    public void setListId(long listId) {
        this.listId = listId;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setIsDone(int isDone) {
      this.isDone =  (isDone == 1);
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getReminder() {
        return reminder;
    }

    public void setReminder(Date reminder) {
        this.reminder = reminder;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isSingle(){
        return listId != -1;
    }
}


/*
  id...listId......isDone...Deadline.......reminder.....text
  0     -1            0      3453534/-1     4564336/-1   reeertertret
  1     1            0      3453534         4564336     podzadanie1
  2     1            1      3453534         4564336         podzadanie2
  3    -1            0      3453534/-1     4564336/-1   reeertertret
 */

/* notes
        id...fNote...type
        1      a       todoo
        2      b        done
 */

/*
    idNote...idTask...tasktype
        1       1
        1       2
        1       3
        2       4
        3       5

 */

/*
    idTask...isDone...Deadline.......reminder.....text
     1
     2
     3
     4
 */