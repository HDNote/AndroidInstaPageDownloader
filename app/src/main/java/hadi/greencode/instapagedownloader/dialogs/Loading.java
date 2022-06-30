package hadi.greencode.instapagedownloader.dialogs;

import android.content.Context;
import android.widget.TextView;

import hadi.greencode.instapagedownloader.InstaPageApplication;
import hadi.greencode.instapagedownloader.R;
import hadi.greencode.instapagedownloader.utils.BasedDialog;


/**
 * Created by Hadi Note on 2018-08-15.
 */

public class Loading {

    private static Loading       instance;
    private static DialogLoading dialogLoading;

    private Loading() {
    }

    public static synchronized Loading get() {
        if (instance == null) {
            instance = new Loading();
        }
        return instance;
    }

    public void show(Context context) {
        try {
            dialogLoading = new DialogLoading(context);
            if (!dialogLoading.isShowing()) {
                dialogLoading.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hide() {
        if (dialogLoading != null && dialogLoading.isShowing()) {
            dialogLoading.cancel();
            dialogLoading = null;
        }
    }

    private class DialogLoading extends BasedDialog {

        private DialogLoading(Context context) {
            super(context, R.layout.dialog_loading, true, false, true);
            if (getWindow() != null) {
                getWindow().getAttributes().windowAnimations = R.style.DialogFadeAnimation;

                ((TextView) findViewById(R.id.txtWait)).setTypeface(InstaPageApplication.getBoldIranSans());

            }
        }
    }
}
