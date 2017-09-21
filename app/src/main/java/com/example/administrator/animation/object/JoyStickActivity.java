package com.example.administrator.animation.object;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;

import com.example.administrator.animation.R;

public class JoyStickActivity extends AppCompatActivity {

    int playerX = 0;
    int playerY = 0;
    private Button player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joy_stick);
        initView();
    }

    private void initView() {
        player = (Button) findViewById(R.id.player);
    }

    public void up(View view) {
        playerY -= 100;
        move();
    }

    public void left(View view) {
        playerX -= 100;
        move();
    }

    public void right(View view) {
        playerX += 100;
        move();
    }

    public void down(View view) {
        playerY += 100;
        move();
    }

    private void move() {
        ObjectAnimator aniY = ObjectAnimator.ofFloat(player, "translationY", playerY);
        ObjectAnimator aniX = ObjectAnimator.ofFloat(player, "translationX", playerX);

        PropertyValuesHolder holder;

        AnimatorSet set = new AnimatorSet();
        set.playTogether(aniX, aniY);
        set.setDuration(1000);
        set.setInterpolator(new BounceInterpolator());
        set.start();
    }
}
