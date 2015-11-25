package com.example.arkadiuszkarbowy.tasklog.presenters;

/**
 * Created by arkadiuszkarbowy on 16/11/15.
 */
public interface TodoPresenter extends Presenter {
     void taskChecked(long id);
     void taskUnchecked(long id);
}
