package blottn.org.silk;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import java.util.ArrayList;
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

    public static void tether(ActivityCompat activityCompat, Spider spider) {
        //used for providing non-standard spider implemetations
    }
    
    public static void touch(AppCompatActivity activity, MotionEvent event) {
        //on touch, check if the activity has touched a web already.
        if (tethers.containsKey(activity)) {
            //seen

        }
        else {
            //unseen
            //tether it, add a spider
            Web web = new Web(new Spider());
            tethers.put(activity, web);
            web.snare(activity, event);
        }
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
    }
}