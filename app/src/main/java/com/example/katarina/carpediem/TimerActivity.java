package com.example.katarina.carpediem;

import android.os.CountDownTimer;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.Locale;

public class TimerActivity extends AppCompatActivity {

    private static final long START_TIME_IN_MILLIS=60000;

    private TextView mTextViewCountDown;
    private Button mButtonStartPause;
    private Button mButtonReset;
    private Button previousButton;
    private Button nextButton;

    ViewFlipper viewFlipper;


    private CountDownTimer mCountDownTimer;

    private boolean mTimerRunning;

    private long mTimeLeftInMillis=START_TIME_IN_MILLIS;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);


        viewFlipper=(ViewFlipper) findViewById(R.id.viewFlipper);
        previousButton=(Button) findViewById(R.id.button_prev);
        nextButton=(Button) findViewById(R.id.button_next);

        mTextViewCountDown=findViewById(R.id.text_view_countdown);
        mButtonStartPause=findViewById(R.id.button_start_pause);
        mButtonReset=findViewById(R.id.button_reset);

        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mTimerRunning) {
                    pauseTimer();
                }
                else {
                    startTimer();
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    viewFlipper.showNext();

                if(mTimerRunning) {
                    pauseTimer();
                    resetTimer();
                }
                else {
                    resetTimer();
                }


            }
        });

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.showPrevious();
                if(mTimerRunning) {
                    pauseTimer();
                    resetTimer();
                }
                else {
                    resetTimer();
                }
            }
        });

        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resetTimer();
            }
        });

        updateCountDownText();
    }

    private void startTimer() {
        //u mCountDownTimer-u mora da se prosledi duzina u mili sekundama i duzina na koliko milisekundi ce se pozivati ova metoda
        mCountDownTimer=new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {

                mTimerRunning=false;
                mButtonStartPause.setText("START");
                mButtonStartPause.setVisibility(View.INVISIBLE);
                mButtonReset.setVisibility(View.VISIBLE);

            }
        }.start();
        mTimerRunning=true;
        mButtonStartPause.setText("pause");
        mButtonReset.setVisibility(View.INVISIBLE);
    }

    private void pauseTimer () {

        mCountDownTimer.cancel();
        mTimerRunning=false;
        mButtonStartPause.setText("START");
        mButtonReset.setVisibility(View.VISIBLE);

    }

    private void resetTimer() {
        mTimeLeftInMillis=START_TIME_IN_MILLIS;
        updateCountDownText();
        mButtonReset.setVisibility(View.INVISIBLE);
        mButtonStartPause.setVisibility(View.VISIBLE);


    }

    private void updateCountDownText() {
        //mora da se castuje zbog long
        int minutes=(int) (mTimeLeftInMillis /1000) /60;
        int seconds=(int) (mTimeLeftInMillis /1000) %60;

        //kreiran string da bi izgledao kao sat
        String timeLeftFormatted=String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);

        mTextViewCountDown.setText(timeLeftFormatted);

    }
}
