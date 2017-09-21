package com.example.administrator.animation.object;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.administrator.animation.R;

public class WindmillActivity2 extends AppCompatActivity {

    private Button start;
    private RelativeLayout windmill;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_windmill);
        initView();
    }

    private void initView() {
        start = (Button) findViewById(R.id.start);
        windmill = (RelativeLayout) findViewById(R.id.windmill);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        windmill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WindmillActivity2.this, AnimationActivity.class);
                startActivity(intent);
            }
        });
        windmill.setClickable(true);
    }

    public void start(View view) {


        // 1
        ObjectAnimator anim1X = ObjectAnimator.ofFloat(
                button1, "translationX", button1.getWidth()/2
        );

        ObjectAnimator anim1Y = ObjectAnimator.ofFloat(
                button1, "translationY", button1.getWidth()/2
        );

        ObjectAnimator anim1R = ObjectAnimator.ofFloat(
                button1, "rotation", 360
        );

        // 2
        ObjectAnimator anim2X = ObjectAnimator.ofFloat(
                button2, "translationX", -button1.getWidth()/2
        );

        ObjectAnimator anim2Y = ObjectAnimator.ofFloat(
                button2, "translationY", button1.getWidth()/2
        );

        ObjectAnimator anim2R = ObjectAnimator.ofFloat(
                button2, "rotation", 360
        );

        // 3
        ObjectAnimator anim3X = ObjectAnimator.ofFloat(
                button3, "translationX", button1.getWidth()/2
        );

        ObjectAnimator anim3Y = ObjectAnimator.ofFloat(
                button3, "translationY", -button1.getWidth()/2
        );

        ObjectAnimator anim3R = ObjectAnimator.ofFloat(
                button3, "rotation", 360
        );

        // 4
        ObjectAnimator anim4X = ObjectAnimator.ofFloat(
                button4, "translationX", -button1.getWidth()/2
        );

        ObjectAnimator anim4Y = ObjectAnimator.ofFloat(
                button4, "translationY", -button1.getWidth()/2
        );

        ObjectAnimator anim4R = ObjectAnimator.ofFloat(
                button4, "rotation", 360
        );

        AnimatorSet set1 = new AnimatorSet();
        set1.playTogether(anim1R, anim1X, anim1Y);
        set1.start();

        AnimatorSet set2 = new AnimatorSet();
        set2.playTogether(anim2R, anim2X, anim2Y);
        set2.start();

        AnimatorSet set3 = new AnimatorSet();
        set3.playTogether(anim3R, anim3X, anim3Y);
        set3.start();

        AnimatorSet set4 = new AnimatorSet();
        set4.playTogether(anim4R, anim4X, anim4Y);

        set4.start();



    }

    public void back(View view){
        ObjectAnimator anim4X = ObjectAnimator.ofFloat(
                button4, "translationX", +button1.getWidth()/2
        );

        ObjectAnimator anim4Y = ObjectAnimator.ofFloat(
                button4, "translationY", +button1.getWidth()/2
        );

        ObjectAnimator anim4R = ObjectAnimator.ofFloat(
                button4, "rotation", 360
        );

        AnimatorSet set4 = new AnimatorSet();
        set4.playTogether(anim4R, anim4X, anim4Y);
        set4.start();
    }

}
