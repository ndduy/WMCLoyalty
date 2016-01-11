package com.dinosys.wmcloyalty.ui.activity.promotion;

import com.dinosys.wmcloyalty.ui.activity.base.BaseActivity;
import com.dinosys.wmcloyalty.ui.fragment.promotion.DetailPromotionFragment;
import com.dinosys.wmcloyalty.ui.fragment.base.BaseFragment;

/**
 * Created by Huynh
 * Since: 1/10/2016 - 15:24
 * Project: WMCLoyalty
 */
public class DetailPromotionActivity extends BaseActivity {

    @Override
    protected BaseFragment hostFragment() {
        return DetailPromotionFragment.newInstance("Example");
    }

    @Override
    protected void setupActivityComponent() {
        super.setupActivityComponent();
    }
}
