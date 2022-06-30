package hadi.greencode.instapagedownloader.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Hadi Note on 11/28/2017.
 */

public class BasedDialog extends Dialog {

    /**
     * Default dim, Normal dialog.
     */
    public BasedDialog(Context context, int layoutRes, boolean cancelable) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(layoutRes);
        setCancelable(cancelable);
    }

    /**
     * Default dim
     */
    public BasedDialog(Context context, int layoutRes, boolean isHeightMatchParent, boolean cancelable, boolean isBackgroundTransparent) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(layoutRes);
        setCancelable(cancelable);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        Window                     window       = getWindow();
        if (window != null) {
            if (isBackgroundTransparent) {
                window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            }
            layoutParams.copyFrom(window.getAttributes());
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = isHeightMatchParent ? ViewGroup.LayoutParams.MATCH_PARENT : ViewGroup.LayoutParams.WRAP_CONTENT;
            window.setAttributes(layoutParams);
        }
    }

    /**
     * Default dim with theme
     */
    public BasedDialog(Context context, int theme, int layoutRes, boolean isHeightMatchParent, boolean cancelable, boolean isBackgroundTransparent) {
        super(context, theme);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(layoutRes);
        setCancelable(cancelable);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        Window                     window       = getWindow();
        if (window != null) {
            if (isBackgroundTransparent) {
                window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            }
            layoutParams.copyFrom(window.getAttributes());
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = isHeightMatchParent ? ViewGroup.LayoutParams.MATCH_PARENT : ViewGroup.LayoutParams.WRAP_CONTENT;
            window.setAttributes(layoutParams);
        }
    }


    /**
     * Custom dim
     */
    public BasedDialog(Context context, int layoutRes, boolean isHeightMatchParent, boolean cancelable, boolean isBackgroundTransparent, float dimValue) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(layoutRes);
        setCancelable(cancelable);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        Window                     window       = getWindow();
        if (window != null) {
            if (isBackgroundTransparent) {
                window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            }
            window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            window.setDimAmount(dimValue);
            layoutParams.copyFrom(window.getAttributes());
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = isHeightMatchParent ? ViewGroup.LayoutParams.MATCH_PARENT : ViewGroup.LayoutParams.WRAP_CONTENT;
            window.setAttributes(layoutParams);
        }
    }
}
