package org.otsoft.slawkens.otland;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends Activity {
    SharedPreferences prefs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView viewx, String urlx) {
                viewx.loadUrl(urlx);
                return false;
            }
        });

        prefs = getSharedPreferences("org.otsoft.slawkens.otland", MODE_PRIVATE);

        String url = "https://otland.net";
        if(prefs.getBoolean("firstRun", true)) {
            url = "https://otland.net/misc/style?style_id=9";
            Toast.makeText(this, "Welcome to the OtLand!", Toast.LENGTH_SHORT).show();
        }

        webView.loadUrl(url);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (prefs.getBoolean("firstRun", true)) {
            // Do first run stuff here then set 'firstrun' as false
            // using the following line to edit/commit prefs
            prefs.edit().putBoolean("firstRun", false).apply();
        }
    }
}
