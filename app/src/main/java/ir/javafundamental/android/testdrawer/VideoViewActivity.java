package ir.javafundamental.android.testdrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);

        VideoView vv_Main = findViewById(R.id.vv_Main);
        final MediaController mediacontroller = new MediaController(this);
        mediacontroller.setAnchorView(vv_Main);
        vv_Main.setMediaController(mediacontroller);
        vv_Main.setVideoURI(Uri.parse("https://s-v4.tamasha.com/statics/videos_file/c8/f7/lEy6P_c8f7743b9c43048a7cd4c4f09b3c9dc770f3536e_n_360.mp4"));
        vv_Main.start();

    }
}