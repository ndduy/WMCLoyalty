package com.dinosys.wmcloyalty.ui.activity.welcome;

import android.view.View;

import com.dinosys.wmcloyalty.R;
import com.dinosys.wmcloyalty.ui.activity.base.BaseActivity;
import com.dinosys.wmcloyalty.ui.activity.promotion.DetailPromotionActivity;
import com.dinosys.wmcloyalty.ui.adapter.welcome.PromotionPagerAdapter;
import com.dinosys.wmcloyalty.ui.fragment.base.BaseFragment;
import com.dinosys.wmcloyalty.ui.fragment.welcome.WelcomeFragment;
import com.dinosys.wmcloyalty.ui.model.PromotionModel;

public class WelcomeActivity
        extends BaseActivity
        implements PromotionPagerAdapter.OnPromotionItemClickListener{

    @Override
    protected BaseFragment hostFragment() {
        return WelcomeFragment.newInstance();
    }

    @Override
    protected void setupActivityComponent() {
        super.setupActivityComponent();
    }

    @Override
    public void onPromotionItemClicked(PromotionModel model, View view) {
        DetailPromotionActivity.viewPromotionDetail(this, model, view.findViewById(R.id.imgCover));
    }
}
