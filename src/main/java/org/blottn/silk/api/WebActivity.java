package org.blottn.silk.api;

import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import org.blottn.silk.core.Web;

/**
 * Created by Nick on 08/11/2017.
 */

public class WebActivity extends AppCompatActivity {

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Web.touch(this,event);
        return super.dispatchTouchEvent(event);
    }
}
