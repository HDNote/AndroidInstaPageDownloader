package hadi.greencode.instapagedownloader.gson;

import com.google.gson.Gson;


/**
 * Created by Hadi Note on 2018-08-18.
 */

public class GsonSingleton {

    private static GsonSingleton instance;
    private Gson gson;

    public static GsonSingleton getInstance() {
        if (instance == null) {
            instance = new GsonSingleton();
        }
        return instance;
    }

    private GsonSingleton() {
        if (gson == null) {
            gson = new Gson();
        }
    }

    public Gson getGson() {
        return gson;
    }

//    public <T extends Keys> List<T> getJsonList(String value) {
//        return gson.fromJson(value, new TypeToken<List<T>>(){}.getType());
//    }

}
