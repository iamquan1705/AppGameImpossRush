package com.samsung.dvt.impossiblerush2;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Android on 11/03/2018.
 */

public class AnimationActivity extends Activity {
    private ImageView imgTranslate, imgRotate, imgScale;
    private TextView tvAlpha, tvTranslate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        imgTranslate = findViewById(R.id.img_translate);

        imgTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TranslateAnimation translateAnimation =
                        new TranslateAnimation(
                                Animation.RELATIVE_TO_PARENT, 0f,
                                Animation.RELATIVE_TO_PARENT, 0f,
                                Animation.RELATIVE_TO_PARENT, 0f,
                                Animation.RELATIVE_TO_PARENT, -1f);
                translateAnimation.setRepeatCount(Animation.INFINITE);
                translateAnimation.setRepeatMode(Animation.REVERSE);
                Animation animation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.demoo_translate_fly);
                translateAnimation.setDuration(3000);
                imgTranslate.startAnimation(translateAnimation);
            }
        });

        imgRotate = findViewById(R.id.img_rotate);
        imgRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation rotateAnimation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.demo_rotate);
                imgRotate.startAnimation(rotateAnimation);
            }
        });
        imgScale = findViewById(R.id.img_scale);
        imgScale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation rotateAnimation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.demo_scale);
                imgScale.startAnimation(rotateAnimation);
            }
        });
        tvAlpha = findViewById(R.id.tv_alpha);
        tvAlpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation alphaAnimation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.demo_alpha);
                tvAlpha.startAnimation(alphaAnimation);
            }
        });

        tvTranslate = findViewById(R.id.tv_translate);
        tvTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation rotateAnimation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.demo_translate_text);
                tvTranslate.startAnimation(rotateAnimation);
            }
        });

    }
}
