package com.example.test.View.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.Model.News;
import com.example.test.Preferences.ManagerPreferences;
import com.example.test.R;
import com.example.test.View.Dialog.ConfrimDialog;
import com.example.test.View.Dialog.DialogLoading;
import com.example.test.View.Dialog.DialogShareData;
import com.example.test.View.Dialog.OnChangeSeekBar;
import com.example.test.utils.ConnectionUtils;

public class TinMoiActivity extends AppCompatActivity  implements  View.OnClickListener, OnChangeSeekBar {

    private WebSettings webSettings = null;
    private WebSettings.TextSize TEXTSIZE[] = new WebSettings.TextSize[]{WebSettings.TextSize.SMALLEST,
            WebSettings.TextSize.SMALLER, WebSettings.TextSize.NORMAL, WebSettings.TextSize.LARGER,
            WebSettings.TextSize.LARGEST};

    private ImageView mImgBackHomeRight;

    private ImageView mImgShare;

    private ImageView mImgExpand;

    private TextView mTvTitle;

    private WebView mWvNews;

    private ManagerPreferences mManagerPreferences;

    private final boolean SETTING_NOT_IMAGE = false;

    private final boolean SETTING_AUTO_LOAD_IMAGE = false;

    private News news;

    private void configWebView() {
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setAppCacheEnabled(false);
        webSettings.setBlockNetworkImage(mManagerPreferences.getSettingNotImage());
        webSettings.setLoadsImagesAutomatically(mManagerPreferences.getSettingAutoLoadImage());
        webSettings.setGeolocationEnabled(false);
        webSettings.setNeedInitialFocus(false);
        webSettings.setSaveFormData(false);
    }

    private void initView() {
        mImgBackHomeRight = findViewById(R.id.img_back_right);
        mImgBackHomeRight.setOnClickListener((View.OnClickListener) this);

        mImgShare = findViewById(R.id.img_share_right);
        mImgShare.setOnClickListener((View.OnClickListener) this);

        mImgExpand = findViewById(R.id.img_expand_right);
        mImgExpand.setOnClickListener((View.OnClickListener) this);

        mTvTitle = findViewById(R.id.tv_content_news_small);

        mWvNews = findViewById(R.id.wv_news);

        mWvNews.setWebViewClient(new MyWebViewClient());
        webSettings = mWvNews.getSettings();

        configWebView();

        webSettings.setTextSize(TEXTSIZE[mManagerPreferences.getTextSizeWebView()]);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinmoi);

        Intent intent = getIntent();

        news = (News) intent.getSerializableExtra("data");

        mManagerPreferences = new ManagerPreferences(this);

        initView();

        mTvTitle.setText(news.getTitle());

        mWvNews.setWebChromeClient(new WebChromeClient());

    }

    @Override
    public void setOnChangeSeekBar(int position) {
        webSettings.setTextSize(TEXTSIZE[position]);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back_right:
                super.onBackPressed();
                break;
            case R.id.img_share_right:
                DialogShareData.ShowShare(this, news.getLink());
                break;

        }
    }


    class MyWebViewClient extends WebViewClient {

        private DialogLoading mDialogLoading;

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            mDialogLoading = new DialogLoading(TinMoiActivity.this);
            mDialogLoading.show();
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            mDialogLoading.dismiss();
            super.onPageFinished(view, url);
        }

        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
        }
    }
}
