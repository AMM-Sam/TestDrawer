package ir.javafundamental.android.testdrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        WebView wv_Main = (WebView)findViewById(R.id.wv_Main);
        wv_Main.getSettings().setJavaScriptEnabled(true);
        wv_Main.loadUrl("https://www.ibm.com/bg-en");
    }
}