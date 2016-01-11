package com.dinosys.wmcloyalty.ui.activity.promotion;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.transition.Slide;
import android.view.View;

import com.dinosys.wmcloyalty.ui.activity.base.BaseActivity;
import com.dinosys.wmcloyalty.ui.fragment.base.BaseFragment;
import com.dinosys.wmcloyalty.ui.fragment.promotion.DetailPromotionFragment;
import com.dinosys.wmcloyalty.ui.model.PromotionModel;

/**
 * Created by Huynh
 * Since: 1/10/2016 - 15:24
 * Project: WMCLoyalty
 */
public class DetailPromotionActivity extends BaseActivity {

    public static final String SHARE_ELEMENT_NAME = "COVER_IMAGE_VIEW";

    public static void viewPromotionDetail(BaseActivity context, PromotionModel model, View view) {
        Intent intent = new Intent(context, DetailPromotionActivity.class);
        ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(context, view, SHARE_ELEMENT_NAME);
        ActivityCompat.startActivity(context, intent, compat.toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivityTransitions();
        //supportPostponeEnterTransition();
    }

    @Override
    protected BaseFragment hostFragment() {
        return DetailPromotionFragment.newInstance("Example");
    }

    @Override
    protected void setupActivityComponent() {
        super.setupActivityComponent();
    }

    private void initActivityTransitions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide transition = new Slide();
            transition.excludeTarget(android.R.id.statusBarBackground, true);
            getWindow().setEnterTransition(transition);
            getWindow().setReturnTransition(transition);
        }
    }
}
