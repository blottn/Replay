package org.blottn.silk.core;

import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

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
    private List<String> viewPaths;

    public Prey(long instant, MotionEvent event, AppCompatActivity activity, List<String> views) {
        this.instant = instant;
        this.event = event;
        this.activity = activity;
        this.viewPaths = views;
    }

    public long getInstant() {
        return instant;
    }

    public MotionEvent getEvent() {
        return event;
    }

    public AppCompatActivity getActivity() {
        return activity;
    }

    public List<String> getViewpaths() { return viewPaths; }
    public String getViewpathsString() {
        String paths = "";
        for (String path : getViewpaths()) {
            paths += path + " ";
        }
        return paths;
    }
    @Override
    public String toString() {
        return instant + " " + event.toString() + " "  +activity.getClass();
    }
}
