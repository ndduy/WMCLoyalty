package com.dinosys.wmcloyalty.ui.fragment.promotion;

import android.os.Bundle;

import com.dinosys.wmcloyalty.ui.fragment.base.BaseFragment;

/**
 * Created by Huynh
 * Since: 1/10/2016 - 15:24
 * Project: WMCLoyalty
 */
public class DetailPromotionFragment extends BaseFragment {

    public static final String KEY_INTENT_EXTRA_PROMOTION_TITLE = "PROMOTION_TITLE";
    public static DetailPromotionFragment newInstance(String title) {
        DetailPromotionFragment fragment = new DetailPromotionFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_INTENT_EXTRA_PROMOTION_TITLE, title);
        fragment.setArguments(bundle);
        return fragment;
    }
}
