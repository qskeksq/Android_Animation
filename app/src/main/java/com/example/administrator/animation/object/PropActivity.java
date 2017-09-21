package com.example.administrator.animation.object;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;

import com.example.administrator.animation.R;

public class PropActivity extends AppCompatActivity {

    Button go;

    float y, x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prop);
        go = (Button) findViewById(R.id.go);
    }

    public void go(View view){
        Intent intent = new Intent(this, JoyStickActivity.class);
        startActivity(intent);
    }

    public void move(View view){

        y += 100;

        ObjectAnimator aniY = ObjectAnimator.ofFloat(
                go,
                "translationY",
                y
        );

        x += 100;
        ObjectAnimator aniX = ObjectAnimator.ofFloat(
                go,
                "translationX",
                x
        );



        AnimatorSet set = new AnimatorSet();
        set.playTogether(aniX, aniY);
        set.setDuration(1000);
        set.setInterpolator(new BounceInterpolator());
        set.start();
    }

}
