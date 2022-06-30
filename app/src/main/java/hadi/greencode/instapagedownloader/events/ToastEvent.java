package hadi.greencode.instapagedownloader.events;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class ToastEvent {

    private static int backgroundColor = Color.parseColor("#1E88E5");
	
    public static Toast make(Context context, String text, int length) {
        @SuppressLint("ShowToast") Toast toast = Toast.makeText(context, text, length);

        View     view      = toast.getView();
        TextView tvMessage = view.findViewById(android.R.id.message);

        view.setBackgroundColor(backgroundColor);
        tvMessage.setShadowLayer(1, 1, 1, Color.BLACK);
        tvMessage.setPadding(5, 0, 5, 0);
        tvMessage.setTextColor(Color.WHITE);
        tvMessage.setTextSize(15f);

        toast.setView(view);
        return toast;
    }

    public static Toast make(Context context, CharSequence text, int length) {
        @SuppressLint("ShowToast") Toast toast = Toast.makeText(context, text, length);

        View     view      = toast.getView();
        TextView tvMessage = view.findViewById(android.R.id.message);

        view.setBackgroundColor(backgroundColor);
        tvMessage.setShadowLayer(1, 1, 1, Color.BLACK);
        tvMessage.setPadding(5, 0, 5, 0);
        tvMessage.setTextColor(Color.WHITE);
        tvMessage.setTextSize(15f);

        toast.setView(view);
        return toast;
    }
}
