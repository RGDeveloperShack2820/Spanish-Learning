package com.basic.spanishlearning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.loader.app.LoaderManager;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SpanishActivity extends AppCompatActivity {

    TextView tv_eng_heading,tv_eng_content,tv_spa_heading,tv_spa_content;
    ImageButton bt_audio;
    SeekBar sb_audio;
    MediaPlayer mp_audio;
    Field[] fields;
    ArrayList<Integer> audioID;
    String str_eng;
    String str_spa;

    Runnable runnable;
    Handler handler= new Handler();
    boolean isPlaying=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spanish);

        tv_spa_heading=findViewById(R.id.tv_spa_heading);
        tv_spa_content=findViewById(R.id.tv_spa_content);
        tv_eng_content=findViewById(R.id.tv_eng_content);
        tv_eng_heading=findViewById(R.id.tv_eng_heading);


        bt_audio=findViewById(R.id.bt_audio);
        sb_audio=findViewById(R.id.sb_audio);
        audioID=new ArrayList<>();
        bt_audio.setImageDrawable(getDrawable(R.drawable.bt_play_icon));

        try {
            InputStream in=getAssets().open("01..txt");
            byte[] b=new byte[in.available()];
            in.read(b);
            in.close();
            str_eng=new String(b);

;        } catch (IOException e) {
            e.printStackTrace();
        }

        tv_eng_content.setText(str_eng);

        fields= R.raw.class.getFields();
        for (int i=0;i<fields.length;i++){
            try {
                audioID.add(fields[i].getInt(fields[i]));
            }
            catch (Exception e){
                Log.i("Exception",e.getMessage());
            }


        }



        Intent intent = getIntent();
        String str_heading= intent.getStringExtra("heading");
        dataSetter(str_heading);

        runnable=new Runnable() {
            @Override
            public void run() {

                sb_audio.setProgress(mp_audio.getCurrentPosition());
                handler.postDelayed(this,500);

            }
        };


        sb_audio.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if (fromUser){

                    mp_audio.seekTo(progress);
                }



            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {


            }
        });

        mp_audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                bt_audio.setImageDrawable(getDrawable(R.drawable.bt_play_icon));
                mp_audio.seekTo(0);
            }
        });


    }

    public void btAudioClicked(View view) {


        if(isPlaying){
            bt_audio.setImageDrawable(getDrawable(R.drawable.bt_play_icon));
            mp_audio.pause();
            handler.removeCallbacks(runnable);
            isPlaying=false;
        }
        else{

            bt_audio.setImageDrawable(getDrawable(R.drawable.bt_paused_icon));
            mp_audio.start();
            handler.postDelayed(runnable,0);
            sb_audio.setMax(mp_audio.getDuration());
            isPlaying=true;
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mp_audio.isPlaying()){
            mp_audio.stop();
        }
    }

    public void engHeadingClicked(View view) {

        if (tv_eng_content.getVisibility()== View.GONE){
            tv_eng_content.setVisibility(View.VISIBLE);
        }
        else{
            tv_eng_content.setVisibility(View.GONE);
        }
    }

    public void spaHeadingClicked(View view) {
        if (tv_spa_content.getVisibility()== View.GONE){
            tv_spa_content.setVisibility(View.VISIBLE);
        }
        else{
            tv_spa_content.setVisibility(View.GONE);
        }
    }

    public void dataSetter(String str_title){

        setTitle(str_title);
        int title_no= Integer.parseInt(str_title.substring(0,3).trim());
        Log.i("IMPORTANT",""+title_no);
        int i;

        for (i=1;i<=100;i++) {

            if (i==title_no){

                mp_audio= MediaPlayer.create(SpanishActivity.this,audioID.get(i-1));
                Toast.makeText(this, ""+audioID.get(i-1), Toast.LENGTH_LONG).show();
                break;
            }
        }

        if (i==101){

            Toast.makeText(this, "Audio Track Not Found At Path ()", Toast.LENGTH_SHORT).show();
        }


    }
}