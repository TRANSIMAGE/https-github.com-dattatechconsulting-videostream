package io.antmedia.android;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.airbnb.deeplinkdispatch.DeepLink;
import com.klinker.android.logger.OnLogListener;
import com.klinker.android.send_message.ApnUtils;
import com.klinker.android.send_message.BroadcastUtils;
import com.klinker.android.send_message.Message;
import com.klinker.android.send_message.Transaction;
import com.klinker.android.send_message.Utils;

import io.antmedia.android.AddNumber.AddNumberActivity;
import io.antmedia.android.liveVideoBroadcaster.*;
import io.antmedia.android.liveVideoPlayer.LiveVideoPlayerActivity;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

@DeepLink("http://example.com/deepLink/")
public class MainActivity extends AppCompatActivity {

    /**
     * PLEASE WRITE RTMP BASE URL of the your RTMP SERVER.
     */
    public static final String RTMP_BASE_URL =  "URL HERE";
    public  String preDefined = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(io.antmedia.android.liveVideoBroadcaster.R.layout.activity_main);
        // ATTENTION: This was auto-generated to handle app links.
        Intent intent = getIntent();
        String action = intent.getAction();
        Uri data = intent.getData();
        if (data != null && data.isHierarchical()) {
            Log.i("MyApp", "Received link click on URL: " + intent.getDataString());
            preDefined = intent.getDataString().replaceFirst(".*/(\\w+).*","$1");
            Intent i = new Intent(this, LiveVideoPlayerActivity.class);
            i.putExtra("preDefinedURI",preDefined);
            startActivity(i);
        }
        // ATTENTION: This was auto-generated to handle app links.
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();
    }

    public void openVideoBroadcaster(View view) {
        Intent i = new Intent(this, LiveVideoBroadcasterActivity.class);
        startActivity(i);
    }

    public void openVideoPlayer(View view) {
        Intent i = new Intent(this, LiveVideoPlayerActivity.class);
        startActivity(i);
    }

    public void openAddNumber(View view) {
        Intent i = new Intent(this, AddNumberActivity.class);
        startActivity(i);
    }
}
