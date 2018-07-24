package com.studytree.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.studytree.R;
import com.studytree.log.Logger;
import com.studytree.utils.StringUtils;
import com.studytree.utils.StudyTreeTools;
import com.studytree.view.base.BaseActivity;
import com.studytree.view.widget.StudyTreeTitleBar;

/**
 * 网页显示Activity
 * Title: WebViewActivity
 * @date 2018/7/20 12:31
 * @author Freedom0013
 */
public class WebViewActivity extends BaseActivity implements StudyTreeTitleBar.TitleBarClickListener {
    public static final String TAG = WebViewActivity.class.getSimpleName();
    private String url;
    private String title;
    private String image;
    private int from = WEB_ABOUT;
    private boolean share;

    public static final int WEB_ABOUT = 0;
    private ProgressBar webview_progress;
    private WebView web_view;

    public static void start(Context ctx,String url,String title,int from){
        Intent intent = new Intent(ctx, WebViewActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", title);
        intent.putExtra("share", false);
        intent.putExtra("from", from);
        ctx.startActivity(intent);
    }

    public static void start(Context ctx,String url,String shareTitle,String shareImage){
        Intent intent = new Intent(ctx, WebViewActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", shareTitle);
        intent.putExtra("image", shareImage);
        intent.putExtra("share", true);
        ctx.startActivity(intent);
    }

    /**
     * 启动WebViewActivity
     * @param ctx 来源Context
     */
    public static void start(Context ctx) {
        Intent intent = new Intent(ctx, WebViewActivity.class);
        ctx.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        //沉浸
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }

        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        share = getIntent().getBooleanExtra("share", false);
        from = getIntent().getIntExtra("from", 1);
        if (share) {
            image = getIntent().getStringExtra("image");
        }
        Logger.d(TAG, "url: " + url);
        if (StringUtils.isNullOrEmpty(url)) {
            Logger.e(TAG, "WebViewActivity没有接收到地址字段！finish()！");
            finish();
            return;
        }

        initView();
    }

    private void initView() {
        //设置占位View以实现沉浸式状态栏
        View statusBar = findViewById(R.id.statusBarView);
        ViewGroup.LayoutParams layoutParams = statusBar.getLayoutParams();
        layoutParams.height = StudyTreeTools.getStatusBarHeight(WebViewActivity.this);

        //配置toolBar
        StudyTreeTitleBar webview_tool = findViewById(R.id.webview_tool);
        webview_tool.setTitleRightVisibility(false);
        webview_tool.setLeftDrawable(R.drawable.titlebar_back);
        webview_tool.setTitle(title);
        webview_tool.setOnTitleBarClickedListener(this);
        //添加系统
        setSupportActionBar(webview_tool);

        webview_progress = findViewById(R.id.webview_progress);
        web_view = findViewById(R.id.web_view);

        initWebSettings();

        //此处复写WebViewClient，防止webview加载网页出现("找不到网页net:err_unknown_url_scheme")
        WebViewClient webViewClient = new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView wv, String url) {
                if (url == null) return false;
                try {
                    if (url.startsWith("weixin://") //微信
                            || url.startsWith("alipays://") //支付宝
                            || url.startsWith("mailto://") //邮件
                            || url.startsWith("tel://")//电话
                            || url.startsWith("dianping://")//大众点评
                        //其他自定义的scheme
                            ) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                        return true;
                    }
                } catch (Exception e) { //防止crash (如果手机上没有安装处理某个scheme开头的url的APP, 会导致crash)
                    return true;//没有安装该app时，返回true，表示拦截自定义链接，但不跳转，避免弹出上面的错误页面
                }

                //处理http和https开头的url
                wv.loadUrl(url);
                return true;
            }
        };
        web_view.setWebViewClient(webViewClient);
        web_view.getSettings().setUserAgentString("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:54.0) Gecko/20100101 Firefox/54.0");
        web_view.loadUrl(url);
    }

    private void initWebSettings() {
        WebSettings settings = web_view.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(false);
//		settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setDomStorageEnabled(true);
        web_view.setWebChromeClient(new MyWebChromeClient());
        web_view.setWebViewClient(new MyWebClient());
        web_view.setDownloadListener(new MyDownloadListener());
    }

    /**
     * webVIew设置进度条
     */
    class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            webview_progress.setProgress(newProgress);
        }
    }

    class MyWebClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            webview_progress.setVisibility(View.GONE);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            webview_progress.setVisibility(View.VISIBLE);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Logger.d(TAG, "shouldOverrideUrlLoading: " + url);
            return super.shouldOverrideUrlLoading(view, url);
        }
    }

    class MyDownloadListener implements DownloadListener {
        @Override
        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }

    @Override
    public void onTitleLeftClicked() {
        finish();
    }

    @Override
    public void onTitleRightClicked() {

    }

    @Override
    public void onTitleClicked() {

    }
}
