package hadi.greencode.instapagedownloader;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Environment;

import androidx.multidex.MultiDex;

import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import java.io.File;
import java.text.DecimalFormat;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class InstaPageApplication extends Application {

    public static final String        APP_DIR      = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Filmy/";
    public static final String        APP_PICS_DIR = APP_DIR + "Pictures";
    public static       DecimalFormat formatter    = new DecimalFormat("#,###,###");
    private static      Typeface      boldIransans;
    private static      Typeface      lightIransans;
    private             Activity      activity;

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        lightIransans = Typeface.createFromAsset(getAssets(), "fonts/IRANSans-Mobile-Light.ttf");
        boldIransans  = Typeface.createFromAsset(getAssets(), "fonts/IRANSans-Bold.ttf");
        initFont();
        initDevMetrics();
        initLeakCanary();
        initDownloader();
        initPython();
        setupFolders();
    }

    private void initPython() {
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }
    }

    private void setupFolders() {
        new File(APP_DIR).mkdirs();
    }

    private void initFont() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/IRANSans-Mobile.ttf")
//                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    private void initLeakCanary() {
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            return;
//        }
//        LeakCanary.install(this);
    }

    private void initDevMetrics() {
        if (BuildConfig.DEBUG) {
//            AndroidDevMetrics.initWith(this);
        }
    }

    private void initDownloader() {
//        PRDownloader.initialize(getApplicationContext(), PRDownloaderConfig.newBuilder()
//                .setDatabaseEnabled(true)
//                .build());
    }

    public DecimalFormat getFormatter() {
        return formatter;
    }

    public void setCurrentActivity(Activity activity) {
        this.activity = activity;
    }

    public Activity getCurrentActivity() {
        return activity;
    }

    public static Typeface getBoldIranSans() {
        return boldIransans;
    }

    public static Typeface getLightIransans() {
        return lightIransans;
    }

}
