package hadi.greencode.instapagedownloader.activities.home;

import static hadi.greencode.instapagedownloader.utils.PythonUtils.INSTAGRAM_API_MODULE;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;

import hadi.greencode.instapagedownloader.base.BasePresenter;
import hadi.greencode.instapagedownloader.dialogs.Loading;
import hadi.greencode.instapagedownloader.gson.GsonSingleton;
import hadi.greencode.instapagedownloader.models.Response;
import hadi.greencode.instapagedownloader.storage.DataManager;

public class
HomePresenter extends BasePresenter {

    private final Context context;
    private final View    view;
//    private       Call<HomePage> callHomePage;

    public interface View {
        void getFeedsResponse(Response response);
    }

    HomePresenter(Context context, View view) {
        this.context = context;
        this.view    = view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        if (callHomePage != null) {
//            callHomePage.cancel();
//        }
    }

    void getFeeds() {
        Loading.get().show(context);
        new Thread(() -> {
            Python python = Python.getInstance();

            PyObject sys              = python.getModule("sys");
            PyObject io               = python.getModule("io");
            PyObject textOutputStream = io.callAttr("StringIO");
            sys.put("stdout", textOutputStream);

            String functionName = "getFeeds";

            PyObject module   = python.getModule(INSTAGRAM_API_MODULE);
            PyObject result   = module.callAttr(functionName, DataManager.getInstance(context).getAll().getUsername());
            String output = textOutputStream.callAttr("getvalue").toString();
            Log.i("OKK", functionName + ": " + result + " ---- " + output);

            if (result != null) {
                Response response = GsonSingleton.getInstance().getGson().fromJson(result.toString(), Response.class);

                ((Activity) context).runOnUiThread(() -> {
                    Loading.get().hide();
                    view.getFeedsResponse(response);
                });
            } else {
                Loading.get().hide();
                Log.i("OKK", functionName + ": Result is null!");
                ((Activity) context).runOnUiThread(() -> {
                    Toast.makeText(context, "با مشکل مواجه شد!", Toast.LENGTH_SHORT).show();
                });
            }
        }).start();
    }

    void getProfileByUsername() {
        Loading.get().show(context);
        new Thread(() -> {
            Python python = Python.getInstance();

            PyObject sys              = python.getModule("sys");
            PyObject io               = python.getModule("io");
            PyObject textOutputStream = io.callAttr("StringIO");
            sys.put("stdout", textOutputStream);


            String functionName = "getProfileByUsername";

            PyObject module   = python.getModule(INSTAGRAM_API_MODULE);
            PyObject result   = module.callAttr(functionName, DataManager.getInstance(context).getAll().getUsername());
            String output = textOutputStream.callAttr("getvalue").toString();
            Log.i("OKK", functionName + ": " + result  + " ---- " + output);

            if (result != null) {
//                Response response = GsonSingleton.getInstance().getGson().fromJson(result.toString(), Response.class);
//                  Glide.with(this).load("http://goo.gl/gEgYUd").into(imageView);
                ((Activity) context).runOnUiThread(() -> {
                    Loading.get().hide();
//                    view.getFeedsResponse(response);
                });
            } else {
                Loading.get().hide();
                Log.i("OKK", functionName + ": Result is null!");
                ((Activity) context).runOnUiThread(() -> {
                    Toast.makeText(context, "با مشکل مواجه شد!", Toast.LENGTH_SHORT).show();
                });
            }
        }).start();
    }
}
