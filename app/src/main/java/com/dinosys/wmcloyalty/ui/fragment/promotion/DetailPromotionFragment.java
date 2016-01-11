package com.dinosys.wmcloyalty.ui.fragment.promotion;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dinosys.wmcloyalty.R;
import com.dinosys.wmcloyalty.ui.activity.promotion.DetailPromotionActivity;
import com.dinosys.wmcloyalty.ui.fragment.base.BaseFragment;

import butterknife.Bind;

/**
 * Created by Huynh
 * Since: 1/10/2016 - 15:24
 * Project: WMCLoyalty
 */
public class DetailPromotionFragment extends BaseFragment {

    public static final String KEY_INTENT_EXTRA_PROMOTION_TITLE = "PROMOTION_TITLE";
    @Bind(R.id.imgCover)
    ImageView imgCover;

    public static DetailPromotionFragment newInstance(String title) {
        DetailPromotionFragment fragment = new DetailPromotionFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_INTENT_EXTRA_PROMOTION_TITLE, title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_promotion, container, false);
    }

    @Override
    protected void onScreenVisible() {
        super.onScreenVisible();
        ViewCompat.setTransitionName(imgCover, DetailPromotionActivity.SHARE_ELEMENT_NAME);
    }
}
