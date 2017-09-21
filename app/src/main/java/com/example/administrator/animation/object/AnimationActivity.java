package com.example.administrator.animation.object;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;

import com.example.administrator.animation.R;

public class AnimationActivity extends AppCompatActivity implements View.OnClickListener {


    private Button btnProp;
    private Button btnRed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        initView();
    }

    private void initView() {
        btnProp = (Button) findViewById(R.id.btnProp);
        btnRed = (Button) findViewById(R.id.btnRed);

        btnProp.setOnClickListener(this);
        btnRed.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnProp:

                ObjectAnimator transY = ObjectAnimator.ofFloat(
                        btnRed,             // 움직일 대상
                        "translationY",     // 애니메이션 속성
                        -500                // 속성 값
                );
                ObjectAnimator transX = ObjectAnimator.ofFloat(
                        btnRed,
                        "translationX",
                        300
                );
                ObjectAnimator rotate = ObjectAnimator.ofFloat(
                        btnRed,
                        "rotation",
                        14400
                );

                // AnimatorSet 에서 모아두고 한번에 실행할 수 있다.
                AnimatorSet aniSet = new AnimatorSet();
                aniSet.playTogether(transX, transY, rotate);    // 개수 제한이 없음
                aniSet.setDuration(3000);                        // 셋의 실행 시간
                aniSet.setInterpolator(new AccelerateDecelerateInterpolator()); // 처음엔 빨리, 점점 느리게
                aniSet.start();
                break;
            case R.id.btnRed:
                ObjectAnimator transYR = ObjectAnimator.ofFloat(
                        btnRed,             // 움직일 대상
                        "translationY",     // 애니메이션 속성
                        500                // 속성 값
                );
                ObjectAnimator transXR = ObjectAnimator.ofFloat(
                        btnRed,
                        "translationX",
                        -300
                );
                ObjectAnimator rotateR = ObjectAnimator.ofFloat(
                        btnRed,
                        "rotation",
                        -14400
                );

                // AnimatorSet 에서 모아두고 한번에 실행할 수 있다.
                AnimatorSet aniSetR = new AnimatorSet();
                aniSetR.playTogether(transXR, transYR, rotateR);    // 개수 제한이 없음
                aniSetR.setDuration(3000);                        // 셋의 실행 시간
                aniSetR.setInterpolator(new AccelerateDecelerateInterpolator()); // 처음엔 빨리, 점점 느리게
                aniSetR.start();
                break;
        }
    }

}
