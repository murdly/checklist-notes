package com.example.arkadiuszkarbowy.tasklog.util;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by arkadiuszkarbowy on 13/11/15.
 */
public class BusProvider {

    private static Bus bus;
    private BusProvider(){}

    public static Bus getBus(){
        if(bus == null) bus = new Bus(ThreadEnforcer.MAIN);
        return bus;
    }
}
