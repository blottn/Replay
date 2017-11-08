package org.blottn.silk.core;

import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick on 19/10/2017.
 * Describes what type of event was caught.
 * Largely a struct to organise data
 */

public class Prey {

    private long instant;
    private MotionEvent event;
    private AppCompatActivity activity;
    private List<View> involvedViews = new ArrayList<>();

    public Prey(long instant, MotionEvent event, AppCompatActivity activity, List<View> views) {
        this.instant = instant;
        this.event = event;
        this.activity = activity;
        this.involvedViews = views;
    }

    public long getInstant() {
        return instant;
    }

    public MotionEvent getEvent() {
        return event;
    }

    public void setEvent(MotionEvent event) {
        this.event = event;
    }

    public AppCompatActivity getActivity() {
        return activity;
    }

    public void setActivity(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    public String toString() {
        return instant + " " + event.toString() + " "  +activity.getClass();
    }
}
