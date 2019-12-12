package com.example.vulcan.ui;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vulcan.R;
import com.facebook.FacebookSdk;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "http://78.47.187.129/vmYvn5rS?offer={bundle_id}&uuid={uuid}&creative_id={bundle_id}&ad_campaign_id={bundle_id}&source={bundle_id}";

    private WebView webView;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initProgressDialog();

        FacebookSdk.setApplicationId(String.valueOf(R.string.facebook_app_id));
        FacebookSdk.sdkInitialize(getApplicationContext());

        initWebView();
        noConnectionAlert(hasConnection(this));
        setCookie();

        webView.loadUrl(BASE_URL);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {
        webView = findViewById(R.id.web_view);
        WebSettings webViewSettings = webView.getSettings();
        webViewSettings.setJavaScriptEnabled(true);
        webViewSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webView.setWebViewClient(new MyWebViewClient(progressDialog));
    }

    private void initProgressDialog() {
        progressDialog = new ProgressDialog(this,ProgressDialog.THEME_HOLO_DARK);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Loading...");
    }

    private void setCookie() {
        CookieSyncManager.createInstance(this);
        CookieSyncManager.getInstance().startSync();
        CookieManager.getInstance().setAcceptCookie(true);
    }

    public void noConnectionAlert(Boolean bool) {
        if (!bool) {
            Toast.makeText(this, "Нет подключения сети", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onPause() {
        CookieSyncManager.getInstance().sync();
        super.onPause();
    }

    @Override
    protected void onResume() {
        CookieSyncManager.getInstance().stopSync();
        super.onResume();
    }

    public static boolean hasConnection(final Context context) {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        wifiInfo = cm.getActiveNetworkInfo();
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        return false;
    }
}
