package hadi.greencode.instapagedownloader.activities.home;

import static hadi.greencode.instapagedownloader.utils.PythonUtils.INSTAGRAM_API_MODULE;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chaquo.python.PyObject;
import com.chaquo.python.Python;

import hadi.greencode.instapagedownloader.base.BasePresenter;
import hadi.greencode.instapagedownloader.dialogs.Loading;
import hadi.greencode.instapagedownloader.gson.GsonSingleton;
import hadi.greencode.instapagedownloader.models.PageProfile;
import hadi.greencode.instapagedownloader.models.Response;
import hadi.greencode.instapagedownloader.storage.DataManager;

public class
HomePresenter extends BasePresenter {

    private final Context context;
    private final View    view;
//    private       Call<HomePage> callHomePage;

    public interface View {
        void getProfileByUsername(PageProfile pageProfile);
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

    void getProfileByUsername(String username, int page, boolean isPagination) {
        if (!isPagination) {
            Loading.get().show(context);
        }
        new Thread(() -> {
            Python python = Python.getInstance();

            String logedUsername = DataManager.getInstance(context).getAll().getUsername();
            String functionName  = "getProfileByUsername";

            PyObject module = python.getModule(INSTAGRAM_API_MODULE);
            PyObject result = module.callAttr(functionName, logedUsername, username, page);
            Log.i("OKK", functionName + ": " + result );

            if (result != null) {
                PageProfile response = GsonSingleton.getInstance().getGson().fromJson(result.toString(), PageProfile.class);

                ((Activity) context).runOnUiThread(() -> {
                    if (!isPagination) {
                        Loading.get().hide();
                    }
                    view.getProfileByUsername(response);
                });
            } else {
                Log.i("OKK", functionName + ": Result is null!");
                ((Activity) context).runOnUiThread(() -> {
                    if (!isPagination) {
                        Loading.get().hide();
                    }
                    Toast.makeText(context, "با مشکل مواجه شد!", Toast.LENGTH_SHORT).show();
                });
            }
        }).start();
    }
}
