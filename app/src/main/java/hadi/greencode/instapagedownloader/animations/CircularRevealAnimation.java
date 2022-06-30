package hadi.greencode.instapagedownloader.animations;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class CircularRevealAnimation {

    private View view;

    public CircularRevealAnimation(View view, boolean shouldSetTouchListener) {
        this.view = view;

        if (shouldSetTouchListener) {
            view.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View myView, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        // get the final radius for the clipping circle
                        int finalRadius = Math.max(myView.getWidth(), myView.getHeight()) / 2;

                        // create the animator for this view (the start radius is zero)
                        Animator anim = ViewAnimationUtils.createCircularReveal(myView, (int) motionEvent.getX(), (int) motionEvent.getY(), 0, finalRadius);

                        // make the view visible and start the animation
                        myView.setVisibility(View.VISIBLE);
                        anim.start();
                    }
                    return false;
                }
            });
        }
    }

    public Animator enter() {
        // get the center for the clipping circle
        int cx = view.getMeasuredWidth() / 2;
        int cy = view.getMeasuredHeight() / 2;

        // get the final radius for the clipping circle
        int finalRadius = Math.max(view.getWidth(), view.getHeight()) / 2;

        // create the animator for this view (the start radius is zero)
        Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, finalRadius);

        // make the view visible and start the animation
        view.setVisibility(View.VISIBLE);
        anim.start();

        return anim;
    }

    public Animator exit() {

        // get the center for the clipping circle
        int cx = view.getMeasuredWidth() / 2;
        int cy = view.getMeasuredHeight() / 2;

        // get the initial radius for the clipping circle
        int initialRadius = view.getWidth() / 2;

        // create the animation (the final radius is zero)
        Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, initialRadius, 0);

        // make the view invisible when the animation is done
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setVisibility(View.INVISIBLE);
            }
        });

        // start the animation
        anim.start();
        return anim;
    }

}
