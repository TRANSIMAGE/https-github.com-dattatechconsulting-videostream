package io.antmedia.android.AddNumber;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.ui.PlaybackControlView;
import com.google.android.exoplayer2.util.Util;

//import net.rimoto.intlphoneinput.IntlPhoneInput;
//import net.rimoto.intlphoneinput.R;
//import net.rimoto.intlphoneinput.*;

import java.net.CookieHandler;


import io.antmedia.android.liveVideoBroadcaster.R;
import io.antmedia.android.liveVideoPlayer.RtmpDataSource;

import static io.antmedia.android.MainActivity.RTMP_BASE_URL;

public class AddNumberActivity extends AppCompatActivity implements View.OnClickListener{
EditText phone1,phone2,phone3;
TextView validate;
public static final String MY_PREFS_NAME = "videobroadcastNumbersFile";

    public static boolean isValidPhoneNo(CharSequence iPhoneNo) {
        return !TextUtils.isEmpty(iPhoneNo) &&
                Patterns.PHONE.matcher(iPhoneNo).matches();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String Phone1 = prefs.getString("Phone1", "No number defined");
        String Phone2 = prefs.getString("Phone2", "No number defined");
        String Phone3 = prefs.getString("Phone3", "No number defined");
        setContentView(R.layout.activity_add_number);
        phone1 = (EditText) findViewById(R.id.phone1);
        phone2 = (EditText) findViewById(R.id.phone2);
        phone3 = (EditText) findViewById(R.id.phone3);
        if (Phone1 != null){
            phone1.setText(Phone1);
        }
        if (Phone2 != null){
            phone2.setText(Phone2);

        }
        if (Phone3 != null){
            phone3.setText(Phone3);
        }


    }

    @Override
    public void onClick(View view) {

    }

    public void save(View view) {
        phone1 = (EditText) findViewById(R.id.phone1);
        phone2 = (EditText) findViewById(R.id.phone2);
        phone3 = (EditText) findViewById(R.id.phone3);
        validate = (TextView) findViewById(R.id.validate);


        String Phone1 =phone1.getText().toString();
        String Phone2 =phone2.getText().toString();
        String Phone3 =phone3.getText().toString();


        if ( (! isValidPhoneNo(Phone1) && !Phone1.equals("No number defined") && !Phone1.equals("") ) ||
                ( ! isValidPhoneNo(Phone2) && !Phone2.equals("No number defined")  && !Phone2.equals("") ) ||
                ( ! isValidPhoneNo(Phone3) && !Phone3.equals("No number defined")  && !Phone3.equals("") ) )
        {
            validate.setText("Please enter a valid global number");
            return;
        }

        Context context = this;
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("Phone1", Phone1);
        editor.putString("Phone2", Phone2);
        editor.putString("Phone3", Phone3);

        editor.apply();

    }


}

