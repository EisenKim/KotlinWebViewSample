package org.nsa.kotlinwebviewsample

import android.annotation.TargetApi
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.*

class WebViewMainActivity : AppCompatActivity() {

    var wbMain: WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view_main)

        wbMain = findViewById<WebView>(R.id.wb_main)

        val wbSetting = wbMain?.settings
        wbSetting?.javaScriptEnabled = true

        wbMain!!.webViewClient = GCWebViewClient()
        wbMain!!.webChromeClient = GCWebChromeClient()

        wbMain!!.loadUrl("https://m.daum.net/")
    }

    override fun onBackPressed() {
        if (wbMain!!.canGoBack()) {
            wbMain!!.goBack()
        } else {
            super.onBackPressed()
        }
    }
    class GCWebViewClient : WebViewClient() {
        @SuppressWarnings("deprecation")
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            view?.loadUrl(url)
            return true
        }

        @TargetApi(Build.VERSION_CODES.N)
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            val url: String = request?.url.toString()
            view?.loadUrl(url)
            return true
        }
    }

    class GCWebChromeClient : WebChromeClient() {
        override fun onJsAlert(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean {
            return super.onJsAlert(view, url, message, result)
        }
    }
}
