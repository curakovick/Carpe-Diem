package com.example.katarina.carpediem;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MeditationActivity extends AppCompatActivity {


    private Button startButton;
    private Button pauseButton;
    private Button stopButton;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditation);

        startButton=(Button) findViewById(R.id.button_play);
        pauseButton=(Button) findViewById(R.id.button_pause);
        stopButton=(Button) findViewById(R.id.button_stop);
    }

    //Kreiramo player u metodama jer ga zelimo imati samo kada zelimo da pustimo zvuk, ovako zauzima mnogo resursa

    //View prosledjujemo jer pozivamo iz xml, u suprotnom aplikacija ne bi radila
    public void play(View view) {

        if(player==null) {
            player=MediaPlayer.create(this, R.raw.meditation);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                }
            });
        }

        player.start();
    }

    public void pause(View view) {

        if(player!=null) {
            player.pause();
        }

    }

    public void stop(View view) {


        //pozivamo stopPlayer metodu kako bi se oslobodili resursi i zaustavio plejer
        stopPlayer();

    }

    //Ne zelimo samo da stopiramo medija plejer vec i da oslobodimo resurse na drugim mestima
    private void stopPlayer() {
        if(player!=null) {
            player.release();
            player=null;
            Toast.makeText(this, "MediaPlayer released", Toast.LENGTH_SHORT).show();
        }
    }

    //kada izadjemo iz aplikacije da se isto oslobode resursi
    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();

    }
}
