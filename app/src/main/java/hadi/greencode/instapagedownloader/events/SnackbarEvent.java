package hadi.greencode.instapagedownloader.events;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.core.graphics.ColorUtils;

import com.google.android.material.snackbar.Snackbar;

import hadi.greencode.instapagedownloader.utils.ActivityUtils;


public class SnackbarEvent {

    private int     backgroundColor                  = Color.parseColor("#689F38");
    private int     secondBackgroundColorForBlinking = Color.WHITE;
    private boolean shouldBlink                      = false;
    private int     blinkTime                        = 3000;
    private int     snackBarLengthTime               = Snackbar.LENGTH_SHORT;
    private int     drawable                         = 0;


    public SnackbarEvent shouldBlink(boolean shouldBlink) {
        this.shouldBlink = shouldBlink;
        return this;
    }

    public SnackbarEvent shouldBlink(boolean shouldBlink, int blinkTime) {
        this.shouldBlink = shouldBlink;
        this.blinkTime = blinkTime;
        return this;
    }

    public SnackbarEvent setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public SnackbarEvent setDrawable(@DrawableRes int drawable) {
        this.drawable = drawable;
        return this;
    }

    public SnackbarEvent setSecondBackgroundColorForBlinking(@ColorInt int secondBackgroundColorForBlinking) {
        this.secondBackgroundColorForBlinking = secondBackgroundColorForBlinking;
        return this;
    }

    public SnackbarEvent setShowTime(int snackBarLengthTime) {
        this.snackBarLengthTime = snackBarLengthTime;
        return this;
    }

    @SuppressLint("NewApi")
    public static void showSimple(Context context, String text, int length) {
        View     decorView    = ((Activity) context).getWindow().getDecorView();
        View     parentLayout = decorView.findViewById(android.R.id.content);
        Snackbar snackbar     = Snackbar.make(parentLayout, text, length);

        View view = snackbar.getView();
        view.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        snackbar.show();
    }

    @SuppressLint("NewApi")
    public void show(Context context, String text) {
        ActivityUtils activityUtils = new ActivityUtils();
        View          decorView     = activityUtils.getActivity(context).getWindow().getDecorView();
        View          parentLayout  = decorView.findViewById(android.R.id.content);
        Snackbar      snackbar      = Snackbar.make(parentLayout, text, snackBarLengthTime);

        View view = snackbar.getView();
        view.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        if (shouldBlink) {
            ValueAnimator valueAnimator = ValueAnimator.ofFloat(0.0f, 0.2f, 0.0f, 0.2f, 0.0f, 0.2f, 0.0f, 0.2f, 0.0f);
            valueAnimator.setDuration(blinkTime);
            valueAnimator.addUpdateListener(animator -> {
                float animatedValue = (float) animator.getAnimatedValue();
                view.setBackgroundColor(ColorUtils.blendARGB(backgroundColor, secondBackgroundColorForBlinking, animatedValue));
            });
            valueAnimator.start();
        } else {
            view.setBackgroundColor(backgroundColor);
        }

        TextView tvMessage = view.findViewById(com.google.android.material.R.id.snackbar_text);
        if (drawable != 0) {
            tvMessage.setCompoundDrawablesWithIntrinsicBounds(0, 0, drawable, 0);
            tvMessage.setCompoundDrawablePadding(10);
        }
        tvMessage.setGravity(Gravity.RIGHT | Gravity.CENTER);
        tvMessage.setTextColor(Color.WHITE);
        snackbar.show();
    }
}
