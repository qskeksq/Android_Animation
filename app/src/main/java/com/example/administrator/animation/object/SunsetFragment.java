package com.example.administrator.animation.object;


import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;

import com.example.administrator.animation.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SunsetFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sunset, container, false);
        return view;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 전체 뷰에서 바다 영역에 리스너 set
        view.findViewById(R.id.bada).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // View 로 초기화
                View hae = view.findViewById(R.id.hae);
                View bada = view.findViewById(R.id.bada);
                View sky = view.findViewById(R.id.sky);
                int skyColor = v.getContext().getResources().getColor(R.color.blue_sky);
                int sunsetColor = v.getContext().getResources().getColor(R.color.sunset_sky);
                int nightColor = v.getContext().getResources().getColor(R.color.night_sky);

                // 태양 애니메이션
                ObjectAnimator animator = ObjectAnimator
                        .ofFloat(hae, "y", hae.getTop(), bada.getTop())
                        .setDuration(4000);

                // 해질녘 배경색 애니메이션
                ObjectAnimator colorAnimator = ObjectAnimator
                        .ofInt(sky, "backgroundColor", skyColor, sunsetColor)
                        .setDuration(4000);
                colorAnimator.setEvaluator(new ArgbEvaluator());
                colorAnimator.setInterpolator(new BounceInterpolator());    // 가속화

                // 해지고 난 후 배경 애니메이션
                ObjectAnimator nightColorAnimator = ObjectAnimator
                        .ofInt(sky, "backgroundColor",sunsetColor, nightColor)
                        .setDuration(2000);
                // 안 해주면 반짝거린다.
                nightColorAnimator.setEvaluator(new ArgbEvaluator());
                // 간격 앞 뒤를 어떻게 자를 것인지 그 간격을 지정한다.
                nightColorAnimator.setInterpolator(new AccelerateInterpolator());

                // 보다 자연스러운 애니메이션을 만들려면 evaluator 와 interpolator 를 사용하면 됨.
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet
                        .play(animator)
                        .with(colorAnimator)
                        .before(nightColorAnimator);
                animatorSet.start();
            }
        });
    }
}
