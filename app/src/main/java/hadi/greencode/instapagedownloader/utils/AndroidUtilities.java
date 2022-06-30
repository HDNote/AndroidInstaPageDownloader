package hadi.greencode.instapagedownloader.utils;

/**
 * Created by VAHID on 2018-09-02.
 */

import android.content.Context;

public class AndroidUtilities {

    public static float pixelToDp(Context context, final float px) {
        return px / context.getResources().getDisplayMetrics().density;
    }

    public static float dpToPixel(Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }
}

