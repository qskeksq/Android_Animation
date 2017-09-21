# Animation
  1. 뷰 애니메이션
  2. 프로퍼티 애니메이션
  3. 액티비티 애니메이션
  4. 프래그먼트 애니메이션
  5. 어댑터뷰 애니메이션
  
![](https://github.com/qskeksq/Animation/blob/master/pic/sunset.gif)
![](https://github.com/qskeksq/Animation/blob/master/pic/windmill.gif)

## 1. Animation

  - View 애니메이션은 모두 andorid.view.animation 를 상속 받아서 만들어 진다. 따라서 AnimationSet, AnimationListener, AnimationUtils, AnimationDrawable
모두 Animation 타입으로 사용 가능하며, 뷰 애니메이션 관련 메소드는 모두 이 객체에서 찾아 사용

  - fromXDelta, fromYDelta, toXDelta, toYDelta 단위
    - "정수" : 정수pixel 만큼 움직인다는뜻.
    - "%" : 자기 자신의 %만큼 움직인다는 뜻. 100%는 자기 자신의 100%만큼의 거리를 ~하겠다는 뜻.
    - "%p" : 자기 부모 뷰의 %만큼 움직인다는 뜻. 50%p는 자기 부모 뷰의 50%만큼의 거리를 ~하겠다는 뜻.

  - pivotX,Y : x, y 좌표의 중심점. 단위는 %. 위와 동이랗게 p가 없는 %는 자기 자신의 (0, 0)에서의 %를 뜻한다.

#### 구현

```java
// xml로 구현
Animation animation = AnimationUtils.loadAnimation(컨텍스트, R.anim.애니메이션)
위젯.startAnimatino(animation)

// 이렇게 하면 View 애니메이션에서도 여러 애니메이션을 동시 적용할 수 있다.
// 순서에 따라 모양이 달라지니 조심하자! rotate+trans 와 trans+rotate 가 다르다
AnimationSet set = new AnimationSet(true);
set.addAnimation(animation);
set.addAnimation(animation2);
위젯.startAnimation(set);
```

```java
// 코드에서 구현
TranslateAnimation anim = new TranslateAnimation(0, mTargetView.getWidth(), 0, mTargetView.getHeight());
anim.setDuration(3000);
위젯.startAnimation(anim);
```

```java
// 동작 코드
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
```

#### translate

```java

<translate
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:fromXDelta="0"
    android:fromYDelta="0"
    android:toXDelta="100%"
    android:toYDelta="100%"
    android:fillAfter="true"
    android:duration="3000">
</translate>
```

#### Rotate

```java
<rotate
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:fromDegrees="0"
    android:toDegrees="720"
    android:pivotX="50%"
    android:pivotY="50%"
    android:fillAfter="true"
    android:duration="3000">
</rotate>
```

#### Alpha

```java
<alpha
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:fromAlpha="1.0"
    android:toAlpha="0.0"
    android:fillAfter="true"
    android:duration="3000">
</alpha>
```

#### Scale

```java
<scale
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:fromXScale="1.0"
    android:fromYScale="1.0"
    android:toYScale="5.0"
    android:toXScale="5.0"
    android:pivotX="50%"
    android:pivotY="50%"
    android:fillAfter="true"
    android:duration="3000">
</scale>
```

#### Set

- offSet : 이전 애니메이션이 끝날때까지 기다리도록 이전 애니메이션 지속 시간을 적어준다.

```java

<set 
xmlns:android="http://schemas.android.com/apk/res/android" 
android:interpolator="@android:anim/accelerate_interpolator">
    <alpha
        android:fromAlpha="0.0"
        android:toAlpha="1.0"
        android:duration="2000" />
    <scale
        android:fromXScale="0.5" android:toXScale="1.5"
        android:fromYScale="0.5" android:toYScale="1.5"
        android:pivotX="50%" android:pivotY="50%"
        android:duration="2000" />
    <scale 
        android:fromXScale="1.5" android:toXScale="1.0"
        android:fromYScale="1.5" android:toYScale="1.0"
        android:pivotX="50%" android:pivotY="50%"
        android:startOffset="2000"
        android:duration="2000" />
</set>
```



## 2. ObjectAnimator

  - Animator 은 android.animation 에 정의되어 있다. 뷰의 애니메이션이 아닌, 애니메이션 클래스 자체로 존재한다. 하위에 ObjectAnimator와 같은 객체가 구현되어 있다. AnimatorSet
AnimatorListener, ObjectAnimator 모두 Animator 타입이며, 객체 관련 애니메이션 사용

#### Vertical Sunset

```java
// 뷰가 생성될 때 설정 - onViewCreated
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
                    // 앞, 뒤 설정 가능
                    .before(nightColorAnimator);
            animatorSet.start();
        }
    });
}
```
#### Parabola Sunset

```java

```

#### 조이스틱(동적 애니메이션)

```java
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

    AnimatorSet set = new AnimatorSet();
    set.playTogether(aniX, aniY);
    set.setDuration(1000);
    set.setInterpolator(new BounceInterpolator());
    set.start();
}
```

