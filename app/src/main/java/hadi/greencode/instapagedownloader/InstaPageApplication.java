package hadi.greencode.instapagedownloader;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Environment;

import androidx.multidex.MultiDex;

import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.downloader.PRDownloader;
import com.downloader.PRDownloaderConfig;

import java.io.File;
import java.text.DecimalFormat;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;

public class InstaPageApplication extends Application {

    public static final String        APP_DIR      = Environment.getExternalStorageDirectory().getAbsolutePath() + "/InstaPageDownloader/";
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
        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath("fonts/IRANSans-Mobile.ttf")
                                .setFontAttrId(io.github.inflationx.calligraphy3.R.attr.fontPath)
                                .build()))
                .build());
    }

    private void initDownloader() {
        PRDownloader.initialize(getApplicationContext(), PRDownloaderConfig.newBuilder()
                .setDatabaseEnabled(true)
                .setReadTimeout(30_000)
                .setConnectTimeout(30_000)
                .build());
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
