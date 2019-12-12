package com.example.vulcan.ui;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.os.Build;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class MyWebViewClient extends WebViewClient {

    private ProgressDialog progressDialog;

    public MyWebViewClient(ProgressDialog progressDialog) {
        this.progressDialog = progressDialog;
        progressDialog.show();
    }

    @SuppressWarnings("deprecation") @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }

    @TargetApi(Build.VERSION_CODES.N) @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
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