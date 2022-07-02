package hadi.greencode.instapagedownloader.dialogs;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.downloader.Error;
import com.downloader.OnDownloadListener;
import com.downloader.PRDownloader;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import hadi.greencode.instapagedownloader.InstaPageApplication;
import hadi.greencode.instapagedownloader.R;
import hadi.greencode.instapagedownloader.models.Post;
import hadi.greencode.instapagedownloader.utils.BasedDialog;

public class DownloaderDialog extends BasedDialog implements View.OnClickListener {

    @BindView(R.id.iBtnClose)
    ImageButton iBtnClose;

    @BindView(R.id.prgDownloadProgress)
    ProgressBar prgDownloadProgress;

    @BindView(R.id.tvDownloadedFileSize)
    TextView tvDownloadedFileSize;

    @BindView(R.id.btnChangeStatus)
    ImageButton btnChangeStatus;

    /*
     *   Main
     * */
    private final Unbinder   bind;
    private       int        downloadId;
    private       int        postIndex       = 0;
    private       int        postImagesIndex = 0;
    private final List<Post> posts;

    public DownloaderDialog(Context context, List<Post> posts) {
        super(context, R.layout.dialog_downloader, false, false, true);
        bind                                         = ButterKnife.bind(this, this);
        this.posts                                   = posts;
        getWindow().getAttributes().windowAnimations = R.style.DialogFadeAnimation;
        setListeners();
//        prgDownloadProgress.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
        tvDownloadedFileSize.setTypeface(InstaPageApplication.getLightIransans());
    }

    @Override
    public void show() {
        super.show();
        download(posts.get(postIndex));
    }

    @Override
    public void cancel() {
        super.cancel();
        PRDownloader.cancel(downloadId);
        bind.unbind();
    }

    private void setListeners() {
        iBtnClose.setOnClickListener(this);
        btnChangeStatus.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iBtnClose:
                cancel();
                break;

            case R.id.btnChangeStatus:
                if (btnChangeStatus.isSelected()) {
                    btnChangeStatus.setSelected(false);
                    btnChangeStatus.setImageResource(R.drawable.ic_pause_white_24dp);
                    PRDownloader.resume(downloadId);
                } else {
                    btnChangeStatus.setSelected(true);
                    btnChangeStatus.setImageResource(R.drawable.ic_play_arrow_white_24dp);
                    PRDownloader.pause(downloadId);
                }
                break;
        }
    }

    private void download(Post post) {
        String downloadLink = "";
        String fileName     = "";

        if (post.getPostImages().size() > 1 &&
            post.getPostImages().size() > postImagesIndex) {
            boolean isVideo    = post.getPostImages().get(postImagesIndex).isVideo();
            String  fileFormat = "_image.jpg";
            if (isVideo) {
                fileFormat = "_video.mp4";
            }
            fileName     = post.getProfile() + "_" + System.currentTimeMillis() + fileFormat;
            downloadLink = isVideo ? post.getPostImages().get(postImagesIndex).getVideoUrl() :
                    post.getPostImages().get(postImagesIndex).getImageUrl();

        } else {
            Boolean isVideo    = post.getVideo();
            String  fileFormat = "_image.jpg";
            if (isVideo) {
                fileFormat = "_video.mp4";
            }
            fileName     = post.getProfile() + "_" + System.currentTimeMillis() + fileFormat;
            downloadLink = isVideo ? post.getVideoUrl() : post.getImage();

        }

        String folderToDownload = InstaPageApplication.APP_PICS_DIR + "/" + post.getProfile();
        new File(folderToDownload).mkdirs();
        downloadId = PRDownloader.download(downloadLink, folderToDownload, fileName)
                .build()
                .setOnStartOrResumeListener(() -> {
                })
                .setOnPauseListener(() -> {
                })
                .setOnCancelListener(() -> {
                })
                .setOnProgressListener(progress -> {
                    try {
                        long totalSize      = (progress.totalBytes / 1024);
                        long downloadedSize = (progress.currentBytes / 1024);
                        tvDownloadedFileSize.setText("Downloaded : " + InstaPageApplication.formatter.format(downloadedSize) + " KB");

                        double downloadedProgress = 100.0 * downloadedSize / totalSize;
                        prgDownloadProgress.setProgress((int) downloadedProgress);
                    } catch (NullPointerException e) {
                        Toast.makeText(getContext(), "Downloader Dialog = onProgress = NullPointerException", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                })
                .start(new OnDownloadListener() {
                    @Override
                    public void onDownloadComplete() {
                        if (posts.size() <= postIndex) {
                            cancel();
                            Toast.makeText(getContext(), "Download finished.", Toast.LENGTH_LONG).show();
                        } else {
                            Post currentDownloadingPost = posts.get(postIndex);
                            if (currentDownloadingPost.getPostImages().size() > 1 &&
                                currentDownloadingPost.getPostImages().size() > postImagesIndex + 1 &&
                                currentDownloadingPost.getPostImages().size() != postImagesIndex) {
                                postImagesIndex++;
                                download(currentDownloadingPost);
                            } else {
                                postImagesIndex = 0;
                                postIndex++;
                                if (posts.size() > postIndex) {
                                    download(posts.get(postIndex));
                                } else {
                                    cancel();
                                    Toast.makeText(getContext(), "Download finished.", Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(Error error) {
                        Toast.makeText(getContext(), "Error: " + error.getServerErrorMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        post.setDownloadId(downloadId);
    }

//    private Status getDownloadStatus() {
//        return PRDownloader.getStatus(downloadId);
//    }

}
