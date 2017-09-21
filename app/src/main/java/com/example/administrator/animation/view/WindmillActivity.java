package com.example.administrator.animation.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.administrator.animation.R;
import com.example.administrator.animation.object.AnimationActivity;

public class WindmillActivity extends AppCompatActivity {

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
                Intent intent = new Intent(WindmillActivity.this, AnimationActivity.class);
                startActivity(intent);
            }
        });
        windmill.setClickable(true);
    }

    public void start(View view) {
        Animation anim1 = AnimationUtils.loadAnimation(this, R.anim.trans1);
        Animation anim2 = AnimationUtils.loadAnimation(this, R.anim.trans2);
        Animation anim3 = AnimationUtils.loadAnimation(this, R.anim.trans3);
        Animation anim4 = AnimationUtils.loadAnimation(this, R.anim.trans4);
        Animation rotate = AnimationUtils.loadAnimation(this, R.anim.rotate_windmill);

        AnimationSet set1 = new AnimationSet(true);
        set1.addAnimation(rotate);
        set1.addAnimation(anim1);
        set1.setFillAfter(true);

        AnimationSet set2 = new AnimationSet(true);
        set2.addAnimation(rotate);
        set2.addAnimation(anim2);
        set2.setFillAfter(true);

        AnimationSet set3 = new AnimationSet(true);
        set3.addAnimation(rotate);
        set3.addAnimation(anim3);
        set3.setFillAfter(true);

        AnimationSet set4 = new AnimationSet(true);
        set4.addAnimation(rotate);
        set4.addAnimation(anim4);
        set4.setFillAfter(true);

        button1.startAnimation(set1);
        button2.startAnimation(set2);
        button3.startAnimation(set3);
        button4.startAnimation(set4);

    }

}
