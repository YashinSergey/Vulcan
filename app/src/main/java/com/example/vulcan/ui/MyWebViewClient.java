package com.example.vulcan.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.material.snackbar.Snackbar;

public class MyWebViewClient extends WebViewClient {

    private ProgressDialog progressDialog;
    private Context context;
    private Activity activity;

    public MyWebViewClient(ProgressDialog progressDialog, Context context, Activity activity) {
        this.progressDialog = progressDialog;
        this.context = context;
        this.activity = activity;
        progressDialog.show();
    }

    @SuppressWarnings("deprecation") @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }

    @TargetApi(Build.VERSION_CODES.N) @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        if (request.getUrl().toString().equals("fail")) {
            Intent intent = new Intent(context, RegistrationActivity.class);
            activity.startActivity(intent);
        }
        view.loadUrl(request.getUrl().toString());
        return true;
    }


    @Override
    public void onPageFinished(WebView view, String url) {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        super.onPageFinished(view, url);
    }

    @Override
    public void onLoadResource(WebView view, String url) {
        super.onLoadResource(view, url);
    }
}