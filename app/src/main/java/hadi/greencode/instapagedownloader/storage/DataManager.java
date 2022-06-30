package hadi.greencode.instapagedownloader.storage;

import android.content.Context;
import android.content.SharedPreferences;

import hadi.greencode.instapagedownloader.models.Local;

import static android.content.Context.MODE_PRIVATE;


public class DataManager {

    private        SharedPreferences        prefs;
    private        SharedPreferences.Editor editor;
    private static DataManager               manager = null;

    private static final String PREFS_KEY = "Nsccsasc";

    private static final String USERNAME_KEY     = "slflksefm";
    private static final String PASSWORD_KEY = "oskfDDDp[pp";

    private DataManager(Context context) {
        if (prefs == null) {
            this.prefs = context.getApplicationContext().getSharedPreferences(PREFS_KEY, MODE_PRIVATE);
        }
        this.editor = prefs.edit();
    }

    public static synchronized DataManager getInstance(Context context) {
        if (manager == null) {
            manager = new DataManager(context);
        }
        return manager;
    }

    public Local getAll() {
        Local local = new Local();
        local.setUsername(prefs.getString(USERNAME_KEY, ""));
        local.setPassword(prefs.getString(PASSWORD_KEY, ""));
        return local;
    }

    public DataManager deleteAll() {
        editor.remove(USERNAME_KEY).commit();
        editor.remove(PASSWORD_KEY).commit();
        return this;
    }

    public void saveLoginData(String username, String password) {
        editor.putString(USERNAME_KEY, username).apply();
        editor.putString(PASSWORD_KEY, password).apply();
    }

    public DataManager deleteLoginData() {
        editor.remove(USERNAME_KEY).commit();
        editor.remove(PASSWORD_KEY).commit();
        return this;
    }

}