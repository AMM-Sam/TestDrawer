package ir.javafundamental.android.testdrawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

    private MyReceiver myReceiver = new MyReceiver();

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

        ArrayList<String> buttonNames = new ArrayList<>();
        buttonNames.add(getString(R.string.Call));
        buttonNames.add(getString(R.string.VideoActivity));
        buttonNames.add(getString(R.string.WebActivity));
        buttonNames.add(getString(R.string.StartService));
        buttonNames.add(getString(R.string.StopService));
        buttonNames.add(getString(R.string.Broadcast));

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        //recyclerView.setLayoutManager(layoutManager);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(this, buttonNames);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), new LinearLayoutManager(this).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        /*
        Button btn_showCallActivity = (Button) findViewById(R.id.btn_showCallActivity);
        Button btn_showVideoActivity = (Button) findViewById(R.id.btn_showVideoActivity);
        Button btn_showWebActivity = (Button) findViewById(R.id.btn_showWebActivity);

        btn_showCallActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CallActivity.class);
                startActivity(intent);
            }
        });
        btn_showVideoActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VideoViewActivity.class);
                startActivity(intent);
            }
        });
        btn_showWebActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                startActivity(intent);
            }
        });
        */
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = null;
        switch (position)
        {
            case 0:
                intent = new Intent(MainActivity.this, CallActivity.class);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(MainActivity.this, VideoViewActivity.class);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(MainActivity.this, WebViewActivity.class);
                startActivity(intent);
                break;
            case 3:
                startService();
                break;
            case 4:
                stopService();
                break;
            case 5:
                IntentFilter filter = new IntentFilter();
                filter.addAction(getPackageName() + "Samangi_Broadcast");//"android.net.conn.CONNECTIVITY_CHANGE");
                registerReceiver(myReceiver, filter);

                intent = new Intent();
                intent.setAction(getPackageName() + "Samangi_Broadcast");
                sendBroadcast(intent);
                break;
        }
        //Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        unregisterReceiver(myReceiver);
        super.onPause();
    }

    public void startService() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //Intent i = new Intent(MainActivity.this, ForegroundService.class);
            //getApplicationContext().startService(i);
            try {
                Intent serviceIntent = new Intent(MainActivity.this, ForegroundService.class);
                serviceIntent.putExtra("inputExtra", "Foreground Service Example in Android");
                ContextCompat.startForegroundService(this, serviceIntent);
            }catch(Exception ex){
                Log.d("MainActivity",ex.getMessage());
            }
        }
        else{
            Toast.makeText(MainActivity.this,
                    "This Device not Sport ForegroundService",
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void stopService() {
        Intent serviceIntent = new Intent(this, ForegroundService.class);
        stopService(serviceIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.mnu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnu_Profile: {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
                break;
            }
        }
        return true;
    }
}