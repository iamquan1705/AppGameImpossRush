package com.samsung.dvt.impossiblerush2;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.Random;

public class MainActivity extends Activity implements Runnable {
    private static final long TIME_TRANLATE = 2000;
    private Button btnLeft, btnRight;
    private ImageView imgSquare, imgBall, imgStart;
    private TextView tvScore;
    private int degrees;
    private Thread mThread;
    private Handler mHandler;
    private int mBallColor;
    private int mSquare;
    private int score;
    private boolean isPlay = false;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mHandler = new Handler();
        mediaPlayer = new MediaPlayer();
        mThread = new Thread(this);
        mThread.start();
    }

    private void initGame() {
        degrees = 0;
        mBallColor = 0;
        mSquare = 0;
        score = 0;
        isPlay = true;
    }

    private void initView() {
        btnLeft = findViewById(R.id.btn_left);
        btnRight = findViewById(R.id.btn_right);
        imgSquare = findViewById(R.id.img_square);
        imgStart = findViewById(R.id.img_start);
        imgBall = findViewById(R.id.img_ball);
        tvScore = findViewById(R.id.tv_score);
        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RotateAnimation rotateAnimation =
                        new RotateAnimation(degrees, degrees - 90,
                                Animation.RELATIVE_TO_SELF, 0.5f,
                                Animation.RELATIVE_TO_SELF, 0.5f);
                rotateAnimation.setFillAfter(true);
                rotateAnimation.setDuration(250);
                imgSquare.startAnimation(rotateAnimation);
                degrees -= 90;
                mSquare++;
                if (mSquare > 3) {
                    mSquare = 0;
                }
            }
        });
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RotateAnimation rotateAnimation =
                        new RotateAnimation(degrees, degrees + 90,
                                Animation.RELATIVE_TO_SELF, 0.5f,
                                Animation.RELATIVE_TO_SELF, 0.5f);
                rotateAnimation.setFillAfter(true);
                rotateAnimation.setDuration(250);
                imgSquare.startAnimation(rotateAnimation);
                degrees += 90;
                mSquare--;
                if (mSquare < 0) {
                    mSquare = 3;
                }
            }
        });
        imgStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgStart.setVisibility(View.INVISIBLE);
                imgBall.setVisibility(View.VISIBLE);
                tvScore.setText("00");
                imgSquare.clearAnimation();
                initGame();
            }
        });
    }

    @Override
    public void run() {
        while (true) {
            if (isPlay == true) {
                startBallAnimation();
                try {
                    Thread.sleep(TIME_TRANLATE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (mSquare == mBallColor) {
                    score++;
                    mediaPlayer.stop();
                    mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.point);
                    mediaPlayer.start();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            tvScore.setText("" + score);
                        }
                    });
                } else {
                    isPlay = false;
                    mediaPlayer.stop();
                    mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.gameover);
                    mediaPlayer.start();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            imgStart.setVisibility(View.VISIBLE);
                            imgBall.setVisibility(View.INVISIBLE);
                            tvScore.setText("GAME OVER");
                        }
                    });
                }
            }
        }
    }

    private void startBallAnimation() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                TranslateAnimation translateAnimation =
                        new TranslateAnimation(
                                Animation.RELATIVE_TO_PARENT, 0f,
                                Animation.RELATIVE_TO_PARENT, 0f,
                                Animation.RELATIVE_TO_PARENT, 0f,
                                Animation.RELATIVE_TO_PARENT, 0.9f);
                translateAnimation.setDuration(TIME_TRANLATE);
                mBallColor = new Random().nextInt(4);
                imgBall.setImageResource(R.drawable.ball_0 + mBallColor);
                imgBall.startAnimation(translateAnimation);
            }
        });
    }
}
