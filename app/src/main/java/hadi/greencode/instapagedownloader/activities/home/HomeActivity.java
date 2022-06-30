package hadi.greencode.instapagedownloader.activities.home;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import butterknife.BindView;
import butterknife.OnClick;
import hadi.greencode.instapagedownloader.R;
import hadi.greencode.instapagedownloader.base.BaseActivity;
import hadi.greencode.instapagedownloader.events.ToastEvent;
import hadi.greencode.instapagedownloader.models.Response;

public class HomeActivity extends BaseActivity implements HomePresenter.View {


    @BindView(R.id.etUsername)
    AppCompatEditText etUsername;

    @BindView(R.id.btFind)
    AppCompatButton btFind;

    /*
     *   Main
     * */
    private HomePresenter presenter;

    @Override
    protected int getLayout() {
        return R.layout.activity_home;
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


    @OnClick({R.id.btFind})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btFind:
                if (etUsername.getText().toString().isEmpty()) {
                    ToastEvent.make(this, "Username is empty!", Toast.LENGTH_SHORT).show();
                } else {

                }
                break;
        }
    }

    @Override
    protected void main(Bundle savedInstanceState) {
        presenter = new HomePresenter(this, this);
        setFonts();
        setInitials();
    }

    private void setInitials() {
        presenter.getProfileByUsername();
    }

    private void setFonts() {

    }

    @Override
    public void getFeedsResponse(Response response) {
        if (response.getCode() == 200) {
            Toast.makeText(this, "موفق", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, response.getStatus(), Toast.LENGTH_SHORT).show();
        }
    }

}
