package hadi.greencode.instapagedownloader.utils;

import android.app.Activity;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Hadi Note on 4/13/2018.
 */

public class JSONHandler {

    public static boolean isJSONObject(String text) {
        try {
            new JSONObject(text);
            return true;
        } catch (JSONException ex) {
            return false;
        }
    }

    public static boolean isJSONArray(String text) {
        try {
            new JSONArray(text);
            return true;
        } catch (JSONException ex1) {
            return false;
        }
    }
//
//    public static StructActions getActions(String json) {
//        return new Gson().fromJson(json, StructActions.class);
//    }
//
//    public static List<StructMaps> getUriMaps(String json) {
//        return new Gson().fromJson(json, new TypeToken<List<StructMaps>>() {
//        }.getType());
//    }
//
//    public static void getFinalList(Context context, String fileNamePath, OnArrayListener onArrayListener) {
//        new Thread(() -> {
//            List<StructFinal> finalList = new ArrayList<>();
//            String json = FileHandler.loadJSONFromAsset(context, "json/" + fileNamePath);
//            StructActions actions = getActions(json);
//
//            List<StructActionsDetaile> actionsList = actions.getActionsList();
//
//            for (StructActionsDetaile structActionsDetaile : actionsList) {
//                for (StructMaps map : GymApplication.maps) {
//                    if (structActionsDetaile.getId() == map.getId()) {
//                        StructFinal sf = new StructFinal();
//                        sf.setUrisList(map.getUrisList());
//                        sf.setTime(structActionsDetaile.getTime());
//                        finalList.add(sf);
//                        break;
//                    }
//                }
//            }
//            ((Activity) context).runOnUiThread(() -> onArrayListener.OnResult(finalList));
//        }).start();
//    }
//
//    public interface OnArrayListener {
//        void OnResult(List<StructFinal> finalList);
//    }
}
