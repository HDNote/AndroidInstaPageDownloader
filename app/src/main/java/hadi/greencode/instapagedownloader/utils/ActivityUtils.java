package hadi.greencode.instapagedownloader.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;

import androidx.annotation.NonNull;

public class ActivityUtils {

    public Activity getActivity(@NonNull Context context) {
        if (context instanceof Activity) {
            return (Activity) context;

        } else if (context instanceof ContextWrapper) {
            return getActivity(((ContextWrapper) context).getBaseContext());
        }

        return null;
    }
}
