package hadi.greencode.instapagedownloader.animations;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

public class FadeAnimation {

    public static void setInAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.4f, 1.0f);
        anim.setDuration(200);
        view.startAnimation(anim);
    }

    public static void setOutAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(1.0f, 0.0f);
        anim.setDuration(200);
        view.startAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void clearAnimation(View view) {
        view.clearAnimation();
    }
}
