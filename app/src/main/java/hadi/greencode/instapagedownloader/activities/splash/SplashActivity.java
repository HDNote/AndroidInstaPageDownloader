package hadi.greencode.instapagedownloader.activities.splash;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import hadi.greencode.instapagedownloader.activities.home.HomeActivity;
import hadi.greencode.instapagedownloader.activities.login.LoginActivity;
import hadi.greencode.instapagedownloader.models.Local;
import hadi.greencode.instapagedownloader.storage.DataManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Local local = DataManager.getInstance(this).getAll();
        if (!local.getUsername().isEmpty() && !local.getPassword().isEmpty()) {
            startActivity(new Intent(this, HomeActivity.class));
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }

        finish();
    }
}
