package hadi.greencode.instapagedownloader.network;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import androidx.multidex.BuildConfig;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import hadi.greencode.instapagedownloader.R;
import hadi.greencode.instapagedownloader.events.SnackbarEvent;
import hadi.greencode.instapagedownloader.utils.ActivityUtils;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    public static final String AUTH_URL         = "https://instagram.com/oauth/authorize/?";
    public static final String ACCESS_TOKEN_URL = "https://api.instagram.com/oauth/access_token";
    public static final String API_BASE_URL     = "https://api.instagram.com/v1";

    public static final String URL      = "https://amin-j.ir/api/v1/";
    public static final String BASE_URL = "https://amin-j.ir/api/";

    private static Retrofit             retrofit;
    private static Gson                 gson    = new GsonBuilder().setLenient().create();
    private static GsonConverterFactory factory = GsonConverterFactory.create(gson);
    private static Context              context;

    private RetrofitService() {
    }

    public synchronized static Retrofit get(Context context) {
        RetrofitService.context = context;
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .client(getClient(RetrofitService.context))
                    .addConverterFactory(factory)
                    .build();
        }

        return retrofit;
    }

    private static OkHttpClient getClient(Context context) {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(5, TimeUnit.SECONDS);
        builder.writeTimeout(10, TimeUnit.SECONDS);
        setUnsafeSsl(builder);

        if (BuildConfig.DEBUG) {
            builder.addNetworkInterceptor(httpLoggingInterceptor());
        }
        builder.addInterceptor(responseCodesInterceptor());

        return builder.build();
    }

    private static Interceptor responseCodesInterceptor() {
        return chain -> {
            Response response = chain.proceed(chain.request());
            if (response.code() == 503) {
//                ActivityUtils activityUtils = new ActivityUtils();
//                activityUtils.getActivity(RetrofitService.context).runOnUiThread(() -> {
//                    MaintenanceBreakDialog maintenanceBreakDialog = new MaintenanceBreakDialog(RestService.context);
//                    if (!maintenanceBreakDialog.isShowing()) {
//                        maintenanceBreakDialog.show();
//                    }
//                });
            }
            return response;
        };
    }

    private static HttpLoggingInterceptor httpLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(message -> {
//            Log.d("OKK", "log: http log: " + message);
//        });
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }

    private static boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public static boolean checkInternetException(Context context, Throwable t) {
        if (context != null && t != null) {
            if (t instanceof SocketTimeoutException) {
                showError(context);
                return false;

            } else if (t instanceof TimeoutException) {
                showError(context);
                return false;

            } else if (t instanceof IOException) {
                new SnackbarEvent()
                        .setDrawable(R.drawable.ic_signal_wifi_off_white_24dp)
                        .shouldBlink(true)
                        .setBackgroundColor(Color.parseColor("#ff1744"))
                        .setShowTime(Snackbar.LENGTH_LONG)
                        .show(context, "خطا در اتصال به اینترنت . . .");
                return true;

            } else {
                showError(context);
                return false;
            }
        } else {
            return false;
        }
    }

    private static void showError(Context context) {
        new SnackbarEvent()
                .setDrawable(R.drawable.ic_close_white_24dp)
                .setBackgroundColor(Color.parseColor("#ff1744"))
                .setShowTime(Snackbar.LENGTH_LONG)
                .show(context, "با مشکل مواجه شد . . .");
    }

    private static void setUnsafeSsl(OkHttpClient.Builder builder) {
        try {
            final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                }

                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[]{};
                }
            }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext;
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier((hostname, session) -> true);
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
    }
}
