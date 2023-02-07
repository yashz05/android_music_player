package com.twp.music_player;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MediaPlayer mp = new MediaPlayer();

        String url = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3";
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button play = findViewById(R.id.play);
        SeekBar s = findViewById(R.id.s);


        try {
            mp.setDataSource(url);
            mp.prepare();

            s.setMax(mp.getDuration());


        } catch (IOException e) {
            Log.d("TAG", "ERROR: " + e);
        }
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                s.setProgress(mp.getCurrentPosition());
            }
        }, 0, 500);
        play.setOnClickListener(e -> {
            if (mp.isPlaying()) {
                mp.pause();

                play.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_baseline_play_circle_filled_24));

            } else {
                mp.start();
                play.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_baseline_pause_circle_24));
            }
        });
        s.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mp.seekTo(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}