package com.dinosys.wmcloyalty.ui.fragment.promotion;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dinosys.wmcloyalty.R;
import com.dinosys.wmcloyalty.ui.activity.promotion.DetailPromotionActivity;
import com.dinosys.wmcloyalty.ui.fragment.base.BaseFragment;
import com.dinosys.wmcloyalty.util.widget.framelayout.ElasticDragDismissFrameLayout;

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

    @Bind(R.id.draggable_frame)
    ElasticDragDismissFrameLayout mDragDismissFrameLayout;

    //@Bind(R.id.scroll)
    //NestedScrollView mScrollView;

    private ElasticDragDismissFrameLayout.SystemChromeFader chromeFader;

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
    public void onResume() {
        super.onResume();
        mDragDismissFrameLayout.addListener(chromeFader);
    }

    @Override
    public void onDestroyView() {
        mDragDismissFrameLayout.removeListener(chromeFader);
        super.onDestroyView();
    }

    @Override
    protected void onScreenVisible() {
        super.onScreenVisible();
        setupUI();
    }

    private void setupUI() {
        ViewCompat.setTransitionName(imgCover, DetailPromotionActivity.SHARE_ELEMENT_NAME);
        chromeFader = new ElasticDragDismissFrameLayout.SystemChromeFader(getActivity().getWindow()) {
            @Override
            public void onDragDismissed() {
                ActivityCompat.finishAfterTransition(getActivity());
            }
        };
    }
}
