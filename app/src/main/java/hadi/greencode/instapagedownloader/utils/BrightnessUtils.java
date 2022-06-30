package hadi.greencode.instapagedownloader.utils;

import android.app.Activity;
import android.view.WindowManager;

public class BrightnessUtils {

    private float currentBrightness;
    private Activity activity;

    public BrightnessUtils(Activity activity) {
        this.activity = activity;
        updateCurrentBrightness();
    }

    public int onBrightnessSlide(float microPercent) {
        WindowManager.LayoutParams windowParams = activity.getWindow().getAttributes();
        windowParams.screenBrightness = currentBrightness + microPercent;
        if (windowParams.screenBrightness > 1f) {
            windowParams.screenBrightness = 1f;
        } else if (windowParams.screenBrightness < 0.01f) {
            windowParams.screenBrightness = 0.01f;
        }
        activity.getWindow().setAttributes(windowParams);

        return ((int) (windowParams.screenBrightness * 100));
    }

    public void updateCurrentBrightness() {
        currentBrightness = activity.getWindow().getAttributes().screenBrightness;

        if (currentBrightness <= 0) {
            currentBrightness = 0.01f;

        } else if (currentBrightness >= 1f) {
            currentBrightness = 1f;
        }
    }
}
