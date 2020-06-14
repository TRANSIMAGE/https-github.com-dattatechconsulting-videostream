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
import com.hbb20.CountryCodePicker;
//import com.hbb20.CountryCodePicker;

//import net.rimoto.intlphoneinput.IntlPhoneInput;
//import net.rimoto.intlphoneinput.R;
//import net.rimoto.intlphoneinput.*;

import java.net.CookieHandler;
import java.util.regex.Pattern;


import io.antmedia.android.liveVideoBroadcaster.R;
import io.antmedia.android.liveVideoPlayer.RtmpDataSource;

import static io.antmedia.android.MainActivity.RTMP_BASE_URL;

public class AddNumberActivity extends AppCompatActivity implements View.OnClickListener{
EditText phone1,phone2,phone3;
TextView validate;
CountryCodePicker ccp,ccp2,ccp3;

public static final String MY_PREFS_NAME = "videobroadcastNumbersFile";

    public static boolean isValidPhoneNo(CharSequence iPhoneNo) {
        return !TextUtils.isEmpty(iPhoneNo) &&
                Patterns.PHONE.matcher(iPhoneNo).matches();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);


        String Phone1 = prefs.getString("Phone1", "");
        String Phone2 = prefs.getString("Phone2", "");
        String Phone3 = prefs.getString("Phone3", "");

        String Phone1_cc = prefs.getString("Phone1_cc", "1");
        String Phone2_cc = prefs.getString("Phone2_cc", "1");
        String Phone3_cc = prefs.getString("Phone3_cc", "1");


        setContentView(R.layout.activity_add_number);
        phone1 = (EditText) findViewById(R.id.phone1);
        phone2 = (EditText) findViewById(R.id.phone2);
        phone3 = (EditText) findViewById(R.id.phone3);
        ccp = (CountryCodePicker) findViewById(R.id.ccp);
        ccp2 = (CountryCodePicker) findViewById(R.id.ccp2);
        ccp3 = (CountryCodePicker) findViewById(R.id.ccp3);


        if (Phone1 != null){
            phone1.setText(Phone1);
        }
        if (Phone2 != null){
            phone2.setText(Phone2);

        }
        if (Phone3 != null){
            phone3.setText(Phone3);
        }


        if (Phone1_cc != null){
            ccp.setCountryForPhoneCode(Integer.parseInt(Phone1_cc));
        }
        if (Phone2_cc != null){
            ccp2.setCountryForPhoneCode(Integer.parseInt(Phone2_cc));

        }
        if (Phone3_cc != null){
            ccp3.setCountryForPhoneCode(Integer.parseInt(Phone3_cc));
        }


    }

    @Override
    public void onClick(View view) {

    }

    public void save(View view) {
        ccp = (CountryCodePicker) findViewById(R.id.ccp);
        ccp2 = (CountryCodePicker) findViewById(R.id.ccp2);
        ccp3 = (CountryCodePicker) findViewById(R.id.ccp3);
        phone1 = (EditText) findViewById(R.id.phone1);
        phone2 = (EditText) findViewById(R.id.phone2);
        phone3 = (EditText) findViewById(R.id.phone3);
        validate = (TextView) findViewById(R.id.validate);

        ccp.registerCarrierNumberEditText(phone1);
        ccp2.registerCarrierNumberEditText(phone2);
        ccp3.registerCarrierNumberEditText(phone3);

        String Phone1 = phone1.getText().toString().replaceAll("\\D", "");;
        String Phone2 = phone2.getText().toString().replaceAll("\\D", "");;
        String Phone3 = phone3.getText().toString().replaceAll("\\D", "");;


        if (ccp.getSelectedCountryCode().equals(Phone1))
        {
            Phone1 = "";
        }
        if (ccp2.getSelectedCountryCode().equals(Phone2))
        {
            Phone2 = "";
        }
        if (ccp3.getSelectedCountryCode().equals(Phone3))
        {
            Phone3 = "";
        }
        if ( (! ccp.isValidFullNumber() && !Phone1.equals("") ) ||
                ( ! ccp2.isValidFullNumber()  && !Phone2.equals("") ) ||
                ( ! ccp3.isValidFullNumber()  && !Phone3.equals("") ) )
        {
            validate.setText("Please enter a valid global number");
            return;
        }



        String Phone1_cc = ccp.getSelectedCountryCode();
        String Phone2_cc = ccp2.getSelectedCountryCode();
        String Phone3_cc = ccp3.getSelectedCountryCode();

        Context context = this;
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("Phone1", Phone1);
        editor.putString("Phone2", Phone2);
        editor.putString("Phone3", Phone3);

        editor.putString("Phone1_cc", Phone1_cc);
        editor.putString("Phone2_cc", Phone2_cc);
        editor.putString("Phone3_cc", Phone3_cc);


        editor.apply();
        validate.setText("Successfully Updated the contacts");

    }


}

