//package hadi.greencode.instapagedownloader;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.GridView;
//import android.widget.ImageView;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//
//import net.londatiga.android.instagram.Instagram;
//import net.londatiga.android.instagram.InstagramSession;
//import net.londatiga.android.instagram.InstagramUser;
//
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.LinkedList;
//import java.util.List;
//
//public class MainActivity extends AppCompatActivity {
//    private InstagramSession mInstagramSession;
//    private Instagram mInstagram;
//
//    private ProgressBar mLoadingPb;
//    private GridView mGridView;
//
//    private static final String CLIENT_ID = "your client id";
//    private static final String CLIENT_SECRET = "your client secret";
//    private static final String REDIRECT_URI = "your redirect uri";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        mInstagram          = new Instagram(this, CLIENT_ID, CLIENT_SECRET, REDIRECT_URI);
//        mInstagramSession   = mInstagram.getSession();
//
//        if (mInstagramSession.isActive()) {
//            setContentView(R.layout.activity_main);
//
//            InstagramUser instagramUser = mInstagramSession.getUser();
//
//            mLoadingPb  = (ProgressBar) findViewById(R.id.pb_loading);
//            mGridView   = (GridView) findViewById(R.id.gridView);
//
//            ((TextView) findViewById(R.id.tv_name)).setText(instagramUser.fullName);
//            ((TextView) findViewById(R.id.tv_username)).setText(instagramUser.username);
//
//            ((Button) findViewById(R.id.btn_logout)).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View arg0) {
//                    mInstagramSession.reset();
//
//                    startActivity(new Intent(MainActivity.this, MainActivity.class));
//
//                    finish();
//                }
//            });
//
//            ImageView userIv = (ImageView) findViewById(R.id.iv_user);
//
//            DisplayImageOptions displayOptions = new DisplayImageOptions.Builder()
//                    .showImageOnLoading(R.drawable.ic_user)
//                    .showImageForEmptyUri(R.drawable.ic_user)
//                    .showImageOnFail(R.drawable.ic_user)
//                    .cacheInMemory(true)
//                    .cacheOnDisc(false)
//                    .considerExifParams(true)
//                    .build();
//
//            ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
//                    .writeDebugLogs()
//                    .defaultDisplayImageOptions(displayOptions)
//                    .build();
//
//            ImageLoader imageLoader = ImageLoader.getInstance();
//            imageLoader.init(config);
//
//            AnimateFirstDisplayListener animate  = new AnimateFirstDisplayListener();
//
//            imageLoader.displayImage(instagramUser.profilPicture, userIv, animate);
//
//            new DownloadTask().execute();
//
//        } else {
//            setContentView(R.layout.activity_main);
//
//            ((Button) findViewById(R.id.btn_connect)).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View arg0) {
//                    mInstagram.authorize(mAuthListener);
//                }
//            });
//        }
//    }
//
//    private void showToast(String text) {
//        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
//    }
//
//    private Instagram.InstagramAuthListener mAuthListener = new Instagram.InstagramAuthListener() {
//        @Override
//        public void onSuccess(InstagramUser user) {
//            finish();
//
//            startActivity(new Intent(MainActivity.this, MainActivity.class));
//        }
//
//        @Override
//        public void onError(String error) {
//            showToast(error);
//        }
//    };
//
//    public static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {
//
//        static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());
//
//        @Override
//        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
//            if (loadedImage != null) {
//                ImageView imageView = (ImageView) view;
//                boolean firstDisplay = !displayedImages.contains(imageUri);
//                if (firstDisplay) {
//                    FadeInBitmapDisplayer.animate(imageView, 500);
//                    displayedImages.add(imageUri);
//                }
//            }
//        }
//    }
//
//    public class DownloadTask extends AsyncTask<URL, Integer, Long> {
//        ArrayList<String> photoList;
//
//        protected void onCancelled() {
//
//        }
//
//        protected void onPreExecute() {
//
//        }
//
//        protected Long doInBackground(URL... urls) {
//            long result = 0;
//
//            try {
//                List<NameValuePair> params = new ArrayList<NameValuePair>(1);
//
//                params.add(new BasicNameValuePair("count", "10"));
//
//                InstagramRequest request = new InstagramRequest(mInstagramSession.getAccessToken());
//                String response          = request.requestGet("/users/self/feed", params);
//
//                if (!response.equals("")) {
//                    JSONObject jsonObj  = (JSONObject) new JSONTokener(response).nextValue();
//                    JSONArray jsonData  = jsonObj.getJSONArray("data");
//
//                    int length = jsonData.length();
//
//                    if (length > 0) {
//                        photoList = new ArrayList<String>();
//
//                        for (int i = 0; i < length; i++) {
//                            JSONObject jsonPhoto = jsonData.getJSONObject(i).getJSONObject("images").getJSONObject("low_resolution");
//
//                            photoList.add(jsonPhoto.getString("url"));
//                        }
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            return result;
//        }
//
//        protected void onProgressUpdate(Integer... progress) {
//        }
//
//        protected void onPostExecute(Long result) {
//            mLoadingPb.setVisibility(View.GONE);
//
//            if (photoList == null) {
//                Toast.makeText(getApplicationContext(), "No Photos Available", Toast.LENGTH_LONG).show();
//            } else {
//                DisplayMetrics dm = new DisplayMetrics();
//
//                getWindowManager().getDefaultDisplay().getMetrics(dm);
//
//                int width   = (int) Math.ceil((double) dm.widthPixels / 2);
//                width=width-50;
//                int height  = width;
//
//                PhotoListAdapter adapter = new PhotoListAdapter(MainActivity.this);
//
//                adapter.setData(photoList);
//                adapter.setLayoutParam(width, height);
//
//                mGridView.setAdapter(adapter);
//            }
//        }
//    }
//}