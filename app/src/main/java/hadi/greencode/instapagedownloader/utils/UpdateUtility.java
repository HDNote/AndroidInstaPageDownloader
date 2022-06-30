//package hadi.greencode.instapagedownloader.utils;
//
//import android.Manifest;
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.content.pm.PackageInfo;
//import android.content.pm.PackageManager;
//import android.widget.Toast;
//
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//
//import ir.greencode.filmy.R;
//import ir.greencode.filmy.dialogs.UpdaterDialog;
//import ir.greencode.filmy.events.ToastEvent;
//import ir.greencode.filmy.network.models.UpdatePackage;
//
//public class UpdateUtility {
//    private      boolean       isCancelable;
//    public final int           REQUEST_PERMISSION_UPDATE = 983372;
//    private      Activity      context;
//    private      UpdatePackage updatePackage;
//
//    public UpdateUtility(Activity activity) {
//        this.context = activity;
//    }
//
//    public void onRequestPermissionsResult(int requestCode, String applicationUpdateLink) {
//        if (requestCode == REQUEST_PERMISSION_UPDATE) {
//            if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED ||
//                    ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
//                updateDownloadDialog(applicationUpdateLink, isCancelable);
//            }
//        }
//    }
//
//    public UpdatePackage getUpdatePackage() {
//        return updatePackage;
//    }
//
//    public void updateCheck(UpdatePackage updatePackage) {
//        if (updatePackage != null) {
//            try {
//                this.updatePackage = updatePackage;
//                PackageInfo packageInfo = context.getApplicationContext().getPackageManager().getPackageInfo(context.getPackageName(), 0);
//                int         verCode     = packageInfo.versionCode;
//                String      versionName = packageInfo.versionName;
//                isCancelable = updatePackage.getUpdateIsForce() == 0;
//
//                if (updatePackage.getUpdateVersionCode() > verCode) {
//                    AlertDialog.Builder sendAlert = new AlertDialog.Builder(context, R.style.AlertDialogCustom);
//                    sendAlert.setMessage("این نسخه از اپلیکیشن نیازمند بروزرسانی است.");
//                    sendAlert.setCancelable(true);
//                    sendAlert.setIcon(context.getResources().getDrawable(R.drawable.ic_update_red_a400_36dp));
//
//                    sendAlert.setPositiveButton("بروزرسانی", (dialogInterface, i) -> {
//                        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                            ToastEvent.make(context, "برای بروزرسانی، لطفا دسترسی حافظه را به دیدپ بدهید!", Toast.LENGTH_SHORT).show();
//                            ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION_UPDATE);
//                        } else {
//                            updateDownloadDialog(updatePackage.getUpdateVersionLink(), isCancelable);
//                        }
//                    });
//
//                    if (isCancelable) {
//                        sendAlert.setTitle("بروزرسانی اِختیاری");
//                        sendAlert.setNegativeButton("لغو", (dialogInterface, i) -> {
//                            dialogInterface.cancel();
//                        });
//                    } else {
//                        sendAlert.setTitle("بروزرسانی اِجباری");
//                        sendAlert.setNegativeButton("خروج", (dialogInterface, i) -> {
//                            dialogInterface.cancel();
//                            context.finish();
//                        });
//                    }
//
//                    sendAlert.create().show();
//                }
//            } catch (PackageManager.NameNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private void updateDownloadDialog(String updateURL, boolean isCancelable) {
//        UpdaterDialog updaterDialog = new UpdaterDialog(context, updateURL);
//        updaterDialog.setOnCancelListener(dialog -> {
//            if (!isCancelable) {
//                context.finish();
//            }
//        });
//        updaterDialog.show();
//    }
//}
