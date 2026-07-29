package com.example.pricepeek;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class SplashScreen extends AppCompatActivity {

    private static final String PREFS_NAME = "app_prefs";
    private static final String KEY_DARK_MODE = "dark_mode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean isDark = prefs.getBoolean(KEY_DARK_MODE, false);
        AppCompatDelegate.setDefaultNightMode(
                isDark ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO
        );

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(() -> {

            Intent intent = new Intent(SplashScreen.this, StartScreen.class);
            startActivity(intent);
            finish();

        }, 10000);


        View logoImage = findViewById(R.id.AppImg);
        View appName = findViewById(R.id.AppName);
        View hintText = findViewById(R.id.hint);




        View circleTopRight = findViewById(R.id.circleTopRight);
        View circleMidLeft = findViewById(R.id.circleMidLeft);
        View circleBottomLeft = findViewById(R.id.circleBottomLeft);

        startFloatingAnimation(circleTopRight, -25f, 1800);
        startFloatingAnimation(circleMidLeft, 20f, 1800);
        startFloatingAnimation(circleBottomLeft, -30f, 1800);


        if (logoImage != null) logoImage.setAlpha(0f);
        if (appName != null) appName.setAlpha(0f);
        if (hintText != null) hintText.setAlpha(0f);




        if (logoImage != null) {
            logoImage.animate()
                    .alpha(1f)
                    .setDuration(1000)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);


                            if (appName != null) {
                                appName.animate()
                                        .alpha(1f)
                                        .setDuration(900)
                                        .setListener(new AnimatorListenerAdapter() {
                                            @Override
                                            public void onAnimationEnd(Animator animation) {
                                                super.onAnimationEnd(animation);


                                                if (hintText != null) {
                                                    hintText.animate()
                                                            .alpha(1f)
                                                            .setDuration(800)
                                                            .start();
                                                }
                                            }
                                        })
                                        .start();
                            }
                        }
                    })
                    .start();
        }


    }

    private void startFloatingAnimation(View view, float deltaY, long duration) {
        if (view == null) return;

        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationY", 0f, deltaY);
        animator.setDuration(duration);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.start();
    }



}