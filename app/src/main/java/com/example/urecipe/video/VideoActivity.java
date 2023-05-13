package com.example.urecipe.video;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.urecipe.MainActivity;
import com.example.urecipe.R;
import com.example.urecipe.explore.ExploreActivity;
import com.example.urecipe.message.MessageActivity;
import com.example.urecipe.settings.SettingsActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Locale;

public class VideoActivity extends AppCompatActivity {

    VideoView videoView;
    String videoPath;

    //Convert user's speech to text
    private static final int REQ_CODE_SPEECH_INPUT = 100;
    TextView speechTxt;
    ImageButton speechBtn;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setSelectedItemId(R.id.nav_video);
        bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.nav_video:
                    return true;
                case R.id.nav_explore:
                    startActivity(new Intent(getApplicationContext(), ExploreActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.nav_home:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.nav_message:
                    startActivity(new Intent(getApplicationContext(), MessageActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.nav_settings:
                    startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;
        });

        //Create method to play video in the application
        videoView = findViewById(R.id.video_view);
        videoPath = "android.resource://" + getPackageName() + "/" + R.raw.brownies;
        //To provide the content
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        //Allow user to manage the playback
        MediaController controller = new MediaController(this);
        videoView.setMediaController(controller);
        controller.setAnchorView(videoView);

        speechTxt = findViewById(R.id.speech_txtV);
        speechBtn = findViewById(R.id.mic_btn);
        speechBtn.setOnClickListener(v -> startVoiceInput());
    }

    //Create method for voice input to text
    private void startVoiceInput() {
        //This method will starts the activity from the user speech through speech recognizer
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Hello...");
        try{
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        }catch (ActivityNotFoundException a){

        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent input) {
        super.onActivityResult(requestCode, resultCode, input);

        switch (requestCode){
            case REQ_CODE_SPEECH_INPUT: {
                if(resultCode == RESULT_OK && null != input){
                    ArrayList<String> result = input.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    speechTxt.setText(result.get(0));
                }
                break;
            }
        }
    }
}