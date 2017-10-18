package blottn.org.silk;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by Nick on 18/10/2017.
 */

public class Spider {

    private static Web web = new Web();

//    public static void trace(AppCompatActivity activity){
//        activity.findViewById(android.R.id.content).setOnTouchListener(new WebListener());
//    }

    public static Web getWeb() {
        return web;
    }

}
