package com.homingos.sdk.browser

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.homingos.sdk.R

internal class BrowserActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_REDIRECTION_URL = "redirection_url"
    }

    private var url: String? = null
    private lateinit var toolbar: Toolbar
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browser)

        init()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun init() {
        extractDataFromIntent()
        setupToolbar()
        loadWebPage()
    }

    private fun extractDataFromIntent() {
        val extras = intent.extras
        url = extras?.getString(EXTRA_REDIRECTION_URL)
        if (url == null) {
            throw IllegalArgumentException("Missing redirection url in intent extras")
        }
    }

    private fun setupToolbar() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = getString(R.string.homingos)
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_close)
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun loadWebPage() {
        webView = findViewById(R.id.webView)
        webView.settings.apply {
            javaScriptEnabled = true
            displayZoomControls = false
            setSupportZoom(false)
            allowContentAccess = true
            domStorageEnabled = true
        }
        webView.loadUrl(url!!)
    }

}