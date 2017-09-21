package com.example.administrator.animation.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.administrator.animation.R;
import com.example.administrator.animation.object.PropActivity;

public class MainActivity extends AppCompatActivity {

    Animation animation;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WindmillActivity.class);
                startActivity(intent);
            }
        });
    }

    public void translate(View view) {
        animation = AnimationUtils.loadAnimation(this, R.anim.trans);
        imageView.startAnimation(animation);
    }

    public void scale(View view) {
        animation = AnimationUtils.loadAnimation(this, R.anim.scale);
        imageView.startAnimation(animation);
    }

    public void alpha(View view) {
        animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        imageView.startAnimation(animation);
    }

    public void rotate(View view) {
        animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        imageView.startAnimation(animation);
    }

    public void prop(View view){
        Intent intent = new Intent(this, PropActivity.class);
        startActivity(intent);
    }
}
