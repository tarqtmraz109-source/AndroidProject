package com.example.pricepeek;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.OvershootInterpolator;


public class StartScreen extends AppCompatActivity {

    private static final String PREFS_NAME = "app_prefs";
    private static final String KEY_DARK_MODE = "dark_mode";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean isDark = prefs.getBoolean(KEY_DARK_MODE, false);
        AppCompatDelegate.setDefaultNightMode(
                isDark ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO
        );

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_start_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.StartPage), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(v -> {
            Intent intent = new Intent(StartScreen.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        View cartCard = findViewById(R.id.cartCard);
        View p32 = findViewById(R.id.p32);
        View p34 = findViewById(R.id.p34);
        View p29 = findViewById(R.id.p29);
        View saveMoreBadge = findViewById(R.id.saveMoreBadge);

        View title = findViewById(R.id.title);
        View subTitle = findViewById(R.id.subTitle);
        View description = findViewById(R.id.description);


        startFloatingAnimation(cartCard, -12f, 1500);


        hideViews(p32, p34, p29, saveMoreBadge, title, subTitle, description, btnStart);


        showWithPop(p32, () -> {
            showWithPop(p34, () -> {
                showWithPop(saveMoreBadge, null);
                showWithPop(p29, () -> {
                    fadeInView(title, 350, () -> {
                        fadeInView(subTitle, 350, () -> {
                            fadeInView(description, 350, () -> {
                                fadeInView(btnStart, 400, null);
                            });
                        });
                    });
                });
            });
        });
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

    private void hideViews(View... views) {
        for (View v : views) {
            if (v != null) {
                v.setAlpha(0f);
                v.setScaleX(0.6f);
                v.setScaleY(0.6f);
            }
        }
    }

    private void showWithPop(View view, Runnable onEnd) {
        if (view == null) return;
        view.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(350)
                .setInterpolator(new OvershootInterpolator(1.2f))
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (onEnd != null) onEnd.run();
                    }
                })
                .start();
    }

    private void fadeInView(View view, long duration, Runnable onEnd) {
        if (view == null) return;
        view.setTranslationY(20f);
        view.animate()
                .alpha(1f)
                .translationY(0f)
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(duration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (onEnd != null) onEnd.run();
                    }
                })
                .start();
    }

}






