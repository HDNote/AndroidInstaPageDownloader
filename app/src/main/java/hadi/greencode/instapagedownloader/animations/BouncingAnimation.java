package hadi.greencode.instapagedownloader.animations;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

/**
 * Created by Hadi Note on 2018-06-27.
 */

public class BouncingAnimation implements android.view.animation.Interpolator {

    private OnAnimationListener listener;

    private double mAmplitude = 1;
    private double mFrequency = 10;

    public BouncingAnimation(double mAmplitude, double mFrequency) {
        this.mAmplitude = mAmplitude;
        this.mFrequency = mFrequency;
    }

    public float getInterpolation(float time) {
        return (float) (-1 * Math.pow(Math.E, -time / mAmplitude) * Math.cos(mFrequency * time) + 1);
    }

    public ScaleAnimation startAnimation(View view, int duration, float from, float to) {
        ScaleAnimation anim = new ScaleAnimation(from, to, from, to, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(duration);
        anim.setInterpolator(this);
        view.startAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onEnd(animation);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        return anim;
    }

    public void setListener(OnAnimationListener listener) {
        this.listener = listener;
    }

    public interface OnAnimationListener {
        void onEnd(Animation animation);
    }
}
