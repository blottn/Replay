package blottn.org.silk;

import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Nick on 18/10/2017.
 *
 * Captures events and attaches meaningful data to them
 *
 */

public class Web {

    private static Map<AppCompatActivity, Web> tethers = new HashMap<>();

    public static void tether(AppCompatActivity activity, Spider spider) {
        Web web = new Web(spider);
        tethers.put(activity, web);
    }
    
    public static void touch(AppCompatActivity activity, MotionEvent event) {
        //on touch, check if the activity has touched a web already.
        if (!tethers.containsKey(activity)) {
            tether(activity, new Spider()); //if it's not yet seen, add it
        }
        tethers.get(activity).snare(activity, event);
    }

    /******************************************************************************
     *                                                                            *
     *                                                                            *
     *                                                                            *
     *                      Dynamic stuff from here down                          *
     *                                                                            *
     *                                                                            *
     ******************************************************************************/

    private List<Spider> spiders;

    public Web(Spider spider) {
        spiders = new ArrayList<>();
        spiders.add(spider);
    }

    public void snare(AppCompatActivity activity, MotionEvent event) {
        //handle data
        System.out.println("Motion Detected!");
        // check views involved
        List<Field> fields = findAllWebbedViews(activity);
        if (fields == null) {
//            fields = findAllViews();
        }
        Prey prey = new Prey(new Date().getTime(),event,activity, null);    //TODO find the views involved
        for (Spider spider : spiders) {
            spider.feed(prey);
        }
    }

    private List<Field> findAllWebbedViews(AppCompatActivity activity) {
        List<Field> found = new ArrayList<>();
        for (Field field : activity.getClass().getDeclaredFields()) {
            boolean wasAccessible = field.isAccessible();
            field.setAccessible(true);
            try {
                if (isWebbedView(field) && field.get(activity) != null) {
                    found.add(field);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();    //This shouldn't happen anyways as we have set the field as accessible
            }
            field.setAccessible(wasAccessible);
        }
        return found;
    }

    private boolean isWebbedView(Field field) {
        return field.isAnnotationPresent(Webbed.class)
                && (field.getAnnotation(Webbed.class)).enabled()
                && (View.class.isAssignableFrom(field.getType()));
    }
}