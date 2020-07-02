package ir.javafundamental.android.testdrawer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_OpenDrawer = (Button)findViewById(R.id.btn_showDrawer);
        btn_OpenDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout nv = (DrawerLayout)findViewById(R.id.activity_main);
                nv.openDrawer(Gravity.RIGHT);
            }
        });

        WebView wv_Main = (WebView)findViewById(R.id.wv_Main);
        wv_Main.getSettings().setJavaScriptEnabled(true);
        wv_Main.loadUrl("https://www.ibm.com/bg-en");

        VideoView vv_Main = findViewById(R.id.vv_Main);
        final MediaController mediacontroller = new MediaController(this);
        mediacontroller.setAnchorView(vv_Main);
        vv_Main.setMediaController(mediacontroller);
        vv_Main.setVideoURI(Uri.parse("https://s-v4.tamasha.com/statics/videos_file/c8/f7/lEy6P_c8f7743b9c43048a7cd4c4f09b3c9dc770f3536e_n_360.mp4"));
        vv_Main.start();

        Button btn1 = (Button) findViewById(R.id.btn_showToast);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "This is a Test" , Toast.LENGTH_SHORT ).show();
            }
        });
    }
}