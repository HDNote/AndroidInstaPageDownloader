package hadi.greencode.instapagedownloader.activities.login;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import butterknife.BindView;
import butterknife.OnClick;
import hadi.greencode.instapagedownloader.BuildConfig;
import hadi.greencode.instapagedownloader.InstaPageApplication;
import hadi.greencode.instapagedownloader.activities.home.HomeActivity;
import hadi.greencode.instapagedownloader.base.BaseActivity;
import hadi.greencode.instapagedownloader.R;
import hadi.greencode.instapagedownloader.events.ToastEvent;
import hadi.greencode.instapagedownloader.models.Local;
import hadi.greencode.instapagedownloader.models.Response;
import hadi.greencode.instapagedownloader.storage.DataManager;

public class LoginActivity extends BaseActivity implements LoginPresenter.View {


    @BindView(R.id.etUsername)
    AppCompatEditText etUsername;

    @BindView(R.id.etPassword)
    AppCompatEditText etPassword;

    @BindView(R.id.btLogin)
    AppCompatButton btLogin;

    /*
     *   Main
     * */
    private LoginPresenter presenter;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void destroy() {
        presenter.onDestroy();
    }

    @Override
    protected void restart() {

    }

    @Override
    protected void start() {

    }

    @Override
    protected void stop() {

    }

    @Override
    protected void pause() {

    }

    @Override
    protected void resume() {

    }

    @Override
    protected void listeners() {

    }

    @OnClick({R.id.btLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btLogin:
                if (etUsername.getText().toString().isEmpty()) {
                    ToastEvent.make(this, "Username is empty!", Toast.LENGTH_SHORT).show();
                } else if (etPassword.getText().toString().isEmpty()) {
                    ToastEvent.make(this, "Password is empty!", Toast.LENGTH_SHORT).show();
                } else {
                    presenter.login(etUsername.getText().toString(), etPassword.getText().toString());
                }
                break;
        }
    }

    @Override
    protected void main(Bundle savedInstanceState) {
        presenter = new LoginPresenter(this, this);
        setFonts();
        setInitials();
        permissions();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 400) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                try {
                    Uri    uri    = Uri.parse("package:" + BuildConfig.APPLICATION_ID);
                    Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION, uri);
                    startActivity(intent);
                } catch (Exception ex) {
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                    startActivity(intent);
                }
            }
        }
    }

    private void permissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 400);
        }
    }

    private void setInitials() {
        Local local = DataManager.getInstance(this).getAll();
        if (!local.getUsername().isEmpty() && !local.getPassword().isEmpty()) {
            etUsername.setText(local.getUsername());
            etPassword.setText(local.getPassword());

            if (TextUtils.equals(etUsername.getText().toString(), local.getUsername()) &&
                TextUtils.equals(etPassword.getText().toString(), local.getPassword())) {
                presenter.login(etUsername.getText().toString(), etPassword.getText().toString());
            }
        }
    }

    private void setFonts() {
        etUsername.setTypeface(InstaPageApplication.getLightIransans());
        etPassword.setTypeface(InstaPageApplication.getLightIransans());
    }

    @Override
    public void loginResponse(Response response) {
        if (response.getCode() == 200) {
            DataManager.getInstance(this).saveLoginData(etUsername.getText().toString(), etPassword.getText().toString());
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
            Toast.makeText(this, "با موفقیت وارد شدید!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, response.getStatus(), Toast.LENGTH_SHORT).show();
        }
    }

}
