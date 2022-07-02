package hadi.greencode.instapagedownloader.activities.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.progressindicator.CircularProgressIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import hadi.greencode.instapagedownloader.BuildConfig;
import hadi.greencode.instapagedownloader.InstaPageApplication;
import hadi.greencode.instapagedownloader.R;
import hadi.greencode.instapagedownloader.activities.login.LoginActivity;
import hadi.greencode.instapagedownloader.adapters.ListMediaAdapter;
import hadi.greencode.instapagedownloader.base.BaseActivity;
import hadi.greencode.instapagedownloader.dialogs.AskDialog;
import hadi.greencode.instapagedownloader.dialogs.DownloaderDialog;
import hadi.greencode.instapagedownloader.events.ToastEvent;
import hadi.greencode.instapagedownloader.models.PageProfile;
import hadi.greencode.instapagedownloader.models.Post;
import hadi.greencode.instapagedownloader.storage.DataManager;

public class HomeActivity extends BaseActivity implements HomePresenter.View {


    @BindView(R.id.etUsername)
    AppCompatEditText etUsername;

    @BindView(R.id.civUser)
    CircleImageView civUser;

    @BindView(R.id.tvPostCount)
    AppCompatTextView tvPostCount;

    @BindView(R.id.tvFollowers)
    AppCompatTextView tvFollowers;

    @BindView(R.id.tvFollowing)
    AppCompatTextView tvFollowing;

    @BindView(R.id.tvUsername)
    AppCompatTextView tvUsername;

    @BindView(R.id.tvFullName)
    AppCompatTextView tvFullName;

    @BindView(R.id.tvBiography)
    AppCompatTextView tvBiography;

    @BindView(R.id.tvIsPrivate)
    AppCompatTextView tvIsPrivate;

    @BindView(R.id.rcMedia)
    RecyclerView rcMedia;

    @BindView(R.id.btFind)
    AppCompatButton btFind;

    @BindView(R.id.btLogout)
    AppCompatButton btLogout;

    @BindView(R.id.pbLoadMedia)
    CircularProgressIndicator pbLoadMedia;

    /*
     *   Main
     * */
    private HomePresenter    presenter;
    private List<Post>       postList = new ArrayList<>();
    private ListMediaAdapter listMediaAdapter;

    /*
     *   Primitives
     * */
    private boolean isScrolling = false;
    private int     currentItems, totalItems, scrollOutItems;
    private int     currentPage      = 1;
    private String  searchedUsername = "";
    private boolean isLoading         = false;
    private boolean pressedOnce;

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

    @OnClick({R.id.btFind, R.id.btLogout, R.id.ibDownloadSelectedImages})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btFind:
                if (etUsername.getText().toString().isEmpty()) {
                    ToastEvent.make(this, "Username is empty!", Toast.LENGTH_SHORT).show();
                } else {
                    currentPage = 1;
                    postList.clear();
                    listMediaAdapter.notifyDataSetChanged();
                    presenter.getProfileByUsername(etUsername.getText().toString(), currentPage, false);
                }
                break;

            case R.id.btLogout:
                new AskDialog(this, "Are you sure?", () -> {
                    startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                    DataManager.getInstance(HomeActivity.this).deleteLoginData();
                    finish();
                }).show();
                break;

            case R.id.ibDownloadSelectedImages:
                List<Post> newList = new ArrayList<>();

                if (!postList.isEmpty()) {
                    for (Post post : postList) {
                        if (post.isChecked()) {
                            newList.add(post);
                        }
                    }
                }
                if (!newList.isEmpty()) {
                    new DownloaderDialog(this, newList).show();
                } else {
                    Toast.makeText(this, "You didn't select any photo to download!", Toast.LENGTH_SHORT).show();
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

    private void setFonts() {
        tvFollowing.setTypeface(InstaPageApplication.getLightIransans());
        tvFollowers.setTypeface(InstaPageApplication.getLightIransans());
        tvPostCount.setTypeface(InstaPageApplication.getLightIransans());
        tvFullName.setTypeface(InstaPageApplication.getLightIransans());
        tvUsername.setTypeface(InstaPageApplication.getLightIransans());
        etUsername.setTypeface(InstaPageApplication.getLightIransans());
        tvBiography.setTypeface(InstaPageApplication.getLightIransans());
    }

    private void setInitials() {
        initMedia();
    }

    private void initMedia() {
        rcMedia.setLayoutManager(new GridLayoutManager(this, 3));
        listMediaAdapter = new ListMediaAdapter(this, postList, (position, post) -> {

        });
        rcMedia.setAdapter(listMediaAdapter);

        rcMedia.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.getLayoutManager() != null) {
                    currentItems   = recyclerView.getLayoutManager().getChildCount();
                    totalItems     = recyclerView.getLayoutManager().getItemCount();
                    scrollOutItems = ((GridLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();

                    if (isScrolling && (currentItems + scrollOutItems == totalItems)) {
                        isScrolling = false;
                        if (currentPage > 0 && !searchedUsername.isEmpty() && !isLoading) {
                            pbLoadMedia.setVisibility(View.VISIBLE);
                            presenter.getProfileByUsername(searchedUsername, currentPage, true);
                            isLoading = true;
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (pressedOnce) {
            super.onBackPressed();
        }
        pressedOnce = true;
        Toast.makeText(this, "برای خروج دوباره بزن!", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(() -> pressedOnce = false, 1500);
    }

    @Override
    public void getProfileByUsername(PageProfile pageProfile) {
        pbLoadMedia.setVisibility(View.GONE);
        isLoading = false;

        if (pageProfile.getCode() == 200) {
            Boolean isPrivate = pageProfile.getProfile().getPrivate();
            if (isPrivate) {
                if (pageProfile.getProfile().getFollowedByViewer()) {
                    tvIsPrivate.setText("Private Account - Following");
                    tvIsPrivate.setTextColor(getResources().getColor(R.color.materialGreen));
                } else {
                    tvIsPrivate.setText("Private Account - Not Accepted You !!!");
                    tvIsPrivate.setTextColor(getResources().getColor(R.color.materialRed));
                }
            } else {
                tvIsPrivate.setText("Public Account");
                tvIsPrivate.setTextColor(getResources().getColor(R.color.materialGreen));
            }
            if (currentPage == 1 && isPrivate) {
                Toast.makeText(this, "مطمئن شو که فالوش کردی!", Toast.LENGTH_LONG).show();
            }

            searchedUsername = etUsername.getText().toString();
            currentPage++;
            postList.addAll(pageProfile.getPosts());
            listMediaAdapter.notifyDataSetChanged();

            tvUsername.setText(searchedUsername);
            tvPostCount.setText(String.valueOf(pageProfile.getProfile().getPostCount()));
            tvFollowers.setText(String.valueOf(pageProfile.getProfile().getFollowers()));
            tvFollowing.setText(String.valueOf(pageProfile.getProfile().getFollowees()));
            tvBiography.setText(pageProfile.getProfile().getBiography());
            tvFullName.setText(pageProfile.getProfile().getFullName());

            Glide.with(this).load(pageProfile.getProfile().getProfilePicUrl()).into(civUser);
        } else {
            Toast.makeText(this, pageProfile.getStatus(), Toast.LENGTH_SHORT).show();
        }
    }
}
