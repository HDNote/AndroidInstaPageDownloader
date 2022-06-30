//package hadi.greencode.instapagedownloader.dialogs;
//
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Color;
//import android.graphics.PorterDuff;
//import android.net.Uri;
//import android.os.Build;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageButton;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.core.content.FileProvider;
//
//import com.downloader.Error;
//import com.downloader.OnCancelListener;
//import com.downloader.OnDownloadListener;
//import com.downloader.OnPauseListener;
//import com.downloader.OnProgressListener;
//import com.downloader.OnStartOrResumeListener;
//import com.downloader.PRDownloader;
//import com.downloader.Progress;
//
//import java.io.File;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.Unbinder;
//import ir.greencode.filmy.FilmyApplication;
//import ir.greencode.filmy.R;
//import ir.greencode.filmy.events.ToastEvent;
//import ir.greencode.filmy.utils.BasedDialog;
//
//public class UpdaterDialog extends BasedDialog implements View.OnClickListener, OnStartOrResumeListener, OnPauseListener, OnCancelListener, OnProgressListener, OnDownloadListener {
//
//    @BindView(R.id.iBtnClose)
//    ImageButton iBtnClose;
//
//    @BindView(R.id.prgDownloadProgress)
//    ProgressBar prgDownloadProgress;
//
//    @BindView(R.id.tvFileSize)
//    TextView tvFileSize;
//
//    @BindView(R.id.tvDownloadedFileSize)
//    TextView tvDownloadedFileSize;
//
//    @BindView(R.id.btnChangeStatus)
//    ImageButton btnChangeStatus;
//
//    @BindView(R.id.btnInstallManually)
//    Button btnInstallManually;
//
//    /*
//     *   Main
//     * */
//    private final Unbinder bind;
//    private final String   downloadUrl;
//    private       int      downloadId;
//    private       String   fileName = "FilmyUpdate.apk";
//
//    public UpdaterDialog(Context context, String downloadUrl) {
//        super(context, R.layout.dialog_updater, false, false, true);
//        getWindow().getAttributes().windowAnimations = R.style.DialogFadeAnimation;
//        bind = ButterKnife.bind(this, this);
//        this.downloadUrl = downloadUrl;
//        setListeners();
//
//        prgDownloadProgress.getProgressDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
//    }
//
//    @Override
//    public void show() {
//        super.show();
//        download();
//    }
//
//    @Override
//    public void cancel() {
//        super.cancel();
//        PRDownloader.cancel(downloadId);
//        bind.unbind();
//    }
//
//    private void setListeners() {
//        iBtnClose.setOnClickListener(this);
//        btnChangeStatus.setOnClickListener(this);
//        btnInstallManually.setOnClickListener(this);
//    }
//
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.iBtnClose:
//                cancel();
//                break;
//
//            case R.id.btnChangeStatus:
//                if (btnChangeStatus.isSelected()) {
//                    btnChangeStatus.setSelected(false);
//                    btnChangeStatus.setImageResource(R.drawable.ic_pause_white_24dp);
//                    PRDownloader.resume(downloadId);
//                } else {
//                    btnChangeStatus.setSelected(true);
//                    btnChangeStatus.setImageResource(R.drawable.ic_play_arrow_white_24dp);
//                    PRDownloader.pause(downloadId);
//                }
//                break;
//
//            case R.id.btnInstallManually:
//                install();
//                break;
//        }
//    }
//
//    @Override
//    public void onCancel() {
//    }
//
//    @Override
//    public void onDownloadComplete() {
//        btnInstallManually.setVisibility(View.VISIBLE);
//        ToastEvent.make(getContext(), "دانلود به اتمام رسید", Toast.LENGTH_SHORT).show();
//        install();
//    }
//
//    @Override
//    public void onError(Error error) {
//    }
//
//    @Override
//    public void onPause() {
//    }
//
//    @Override
//    public void onProgress(Progress progress) {
//        try {
//            long totalSize      = (progress.totalBytes / 1024);
//            long downloadedSize = (progress.currentBytes / 1024);
//            tvFileSize.setText("اندازه : " + FilmyApplication.formatter.format(totalSize) + " مگابایت");
//            if (downloadedSize < 1000) {
//                tvDownloadedFileSize.setText("دانلود شده :" + FilmyApplication.formatter.format(downloadedSize) + " کیلوبایت");
//            } else {
//                tvDownloadedFileSize.setText("دانلود شده :" + FilmyApplication.formatter.format(downloadedSize) + " مگابایت");
//            }
//
//            double downloadedProgress = 100.0 * downloadedSize / totalSize;
//            prgDownloadProgress.setProgress((int) downloadedProgress);
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void onStartOrResume() {
//    }
//
//    private void download() {
//        downloadId = PRDownloader.download(downloadUrl, FilmyApplication.APP_DIR, fileName)
//            .build()
//            .setOnStartOrResumeListener(this)
//            .setOnPauseListener(this)
//            .setOnCancelListener(this)
//            .setOnProgressListener(this)
//            .start(this);
//    }
//
////    private Status getDownloadStatus() {
////        return PRDownloader.getStatus(downloadId);
////    }
//
//    private void install() {
//        try {
//            File file = new File(FilmyApplication.APP_DIR + fileName);
//
//            if (file.exists()) {
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                String type   = "application/vnd.android.package-archive";
//
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                    Uri downloadedApk = FileProvider.getUriForFile(getContext(), getContext().getPackageName(), file);
//                    intent.setDataAndType(downloadedApk, type);
//                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                } else {
//                    intent.setDataAndType(Uri.fromFile(file), type);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                }
//
//                getContext().startActivity(intent);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//}
