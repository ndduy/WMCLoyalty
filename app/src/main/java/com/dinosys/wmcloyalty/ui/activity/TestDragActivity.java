package com.dinosys.wmcloyalty.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.dinosys.wmcloyalty.R;
import com.dinosys.wmcloyalty.ui.activity.base.BaseActivity;
import com.dinosys.wmcloyalty.ui.model.PromotionModel;
import com.dinosys.wmcloyalty.util.AnimUtils;
import com.dinosys.wmcloyalty.util.widget.ElasticDragDismissFrameLayout;
import com.dinosys.wmcloyalty.util.widget.ParallaxScrimageView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TestDragActivity extends AppCompatActivity {

    public static final String SHARE_ELEMENT_NAME = "COVER_IMAGE_VIEW";
    @Bind(R.id.shot)
    ParallaxScrimageView imageView;
    @Bind(R.id.draggable_frame)
    ElasticDragDismissFrameLayout draggableFrame;
    @Bind(R.id.scroll)
    NestedScrollView scrollView;
    private ElasticDragDismissFrameLayout.SystemChromeFader chromeFader;
    private Transition.TransitionListener shotReturnHomeListener = new AnimUtils
            .TransitionListenerAdapter() {
        @Override
        public void onTransitionStart(Transition transition) {
            super.onTransitionStart(transition);
            // hide the fab as for some reason it jumps position??  TODO work out why
            //fab.setVisibility(View.INVISIBLE);
            // fade out the "toolbar" & list as we don't want them to be visible during return
            // animation
            /*back.animate()
                    .alpha(0f)
                    .setDuration(100)
                    .setInterpolator(AnimationUtils.loadInterpolator(DribbbleShot.this, android.R
                            .interpolator.linear_out_slow_in));*/
            imageView.setElevation(1f);
            /*back.setElevation(0f);
            commentsList.animate()
                    .alpha(0f)
                    .setDuration(50)
                    .setInterpolator(AnimationUtils.loadInterpolator(DribbbleShot.this, android.R
                            .interpolator.linear_out_slow_in));*/
        }
    };

    public static void viewPromotionDetail(BaseActivity context, PromotionModel model, View view) {
        Intent intent = new Intent(context, TestDragActivity.class);
        ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(context, view, SHARE_ELEMENT_NAME);
        ActivityCompat.startActivity(context, intent, compat.toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_drag);
        getWindow().getSharedElementReturnTransition().addListener(shotReturnHomeListener);
        ButterKnife.bind(this);
        ViewCompat.setTransitionName(imageView, SHARE_ELEMENT_NAME);
        chromeFader = new ElasticDragDismissFrameLayout.SystemChromeFader(getWindow()) {
            @Override
            public void onDragDismissed() {
                expandImageAndFinish();
                Log.d("HTSI", "onDragDismissed");
            }
        };

        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (v.getMaxScrollAmount() > 0) {
                    imageView.setOffset(scrollY);
                }

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        draggableFrame.addListener(chromeFader);
    }

    private void expandImageAndFinish() {
        if (imageView.getOffset() != 0f) {
            Animator expandImage = ObjectAnimator.ofFloat(imageView, ParallaxScrimageView.OFFSET,
                    0f);
            expandImage.setDuration(80);
            expandImage.setInterpolator(AnimationUtils.loadInterpolator(this, android.R
                    .interpolator.fast_out_slow_in));
            expandImage.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    finishAfterTransition();
                }
            });
            expandImage.start();

        } else {
            finishAfterTransition();

        }
    }
}
