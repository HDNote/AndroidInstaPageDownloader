package hadi.greencode.instapagedownloader.utils;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

public class RecyclerUtils {

    private int lastPosition = -1;

    public void setFadeAnimation(View view, int position) {
        if (position > lastPosition) {
            AlphaAnimation anim = new AlphaAnimation(0.4f, 1.0f);
            anim.setDuration(800);
            view.startAnimation(anim);
            lastPosition = position;
        }
    }

    public void setScaleAnimation(View view, int position) {
        if (position > lastPosition) {
            ScaleAnimation anim = new ScaleAnimation(0.5f, 1.0f, 0.5f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            anim.setDuration(400);
            view.startAnimation(anim);
            lastPosition = position;
        }
    }

    public void clearAnimation(View view) {
        view.clearAnimation();
    }
}
