package blottn.org.silk;

import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import java.util.List;

/**
 * Created by Nick on 18/10/2017.
 */

public class Web {

    List<WebListener> attached;

    public void touch(AppCompatActivity activity, MotionEvent event) {
        for (WebListener listener : attached) {
            listener.onTouch(activity, event);
        }
    }
}
