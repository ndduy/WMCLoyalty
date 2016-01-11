package com.dinosys.wmcloyalty.ui.fragment.welcome;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.dinosys.wmcloyalty.R;
import com.dinosys.wmcloyalty.presenter.welcome.PromotionPagerPresenter;
import com.dinosys.wmcloyalty.ui.adapter.welcome.PromotionPagerAdapter;
import com.dinosys.wmcloyalty.ui.fragment.base.BaseFragment;
import com.dinosys.wmcloyalty.ui.model.PromotionModel;
import com.dinosys.wmcloyalty.ui.view.welcome.IPromotionPagerView;
import com.dinosys.wmcloyalty.util.widget.ZoomOutPageTransformer;
import com.dinosys.wmcloyalty.util.widget.viewpager.CircleIndicator;

import java.util.Collection;

import butterknife.Bind;
import jp.wasabeef.blurry.Blurry;

/**
 * Created by Huynh
 * Since: 1/9/2016 - 08:04
 * Project:
 */
public class WelcomeFragment extends BaseFragment implements IPromotionPagerView {

    private static final TimeInterpolator sDecelerator = new DecelerateInterpolator();
    private static final TimeInterpolator sAccelerator = new AccelerateInterpolator();
    private static final int ANIM_DURATION = 500;

    @Bind(R.id.bg)
    ImageView mBg;

    @Bind(R.id.textOwner)
    View mTextOwner;

    @Bind(R.id.textCompanyName)
    View mTextCompanyName;

    @Bind(R.id.btnSignIn)
    Button mBtnSignIn;

    @Bind(R.id.viewPagePromotions)
    ViewPager mViewPagePros;

    @Bind(R.id.indicator)
    CircleIndicator mIndicator;

    @Bind(R.id.viewLoading)
    ProgressBar mViewLoading;

    private PromotionPagerPresenter mPromotionPagerPresenter = new PromotionPagerPresenter();

    private PromotionPagerAdapter.OnPromotionItemClickListener mOnPromotionItemClickListener;

    public static WelcomeFragment newInstance() {
        return new WelcomeFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PromotionPagerAdapter.OnPromotionItemClickListener) {
            this.mOnPromotionItemClickListener = (PromotionPagerAdapter.OnPromotionItemClickListener)context;
        } else {
            throw new IllegalStateException("Activity must implements OnPromotionItemClickListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    protected void onScreenVisible() {
        super.onScreenVisible();
        // UI 1st Scene:
        // Implement like FlashScreen
        // Waiting in 3 seconds then run animation
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                runEnterAnimation();
            }
        }, 3000);
    }

    private void runEnterAnimation() {
        // translate signIn Button from the bottom
        // & companyName TextView from the top at the same time
        mBtnSignIn.setTranslationY(mBtnSignIn.getHeight() * 2);
        mBtnSignIn.animate().setDuration(ANIM_DURATION)
                .setInterpolator(sDecelerator)
                .alpha(1)
                .translationY(0).withStartAction(new Runnable() {
            @Override
            public void run() {
                mTextCompanyName.animate().setDuration(ANIM_DURATION)
                        .setInterpolator(sAccelerator)
                        .alpha(0).withStartAction(new Runnable() {
                    @Override
                    public void run() {
                        mTextOwner.setTranslationY(-mTextOwner.getHeight() * 2);
                        mTextOwner.animate().setDuration(ANIM_DURATION)
                                .setInterpolator(sDecelerator)
                                .alpha(1)
                                .translationY(0).withStartAction(new Runnable() {
                            @Override
                            public void run() {
                                // UI 2nd Scene:
                                // Setup main UI
                                setupUI();
                            }
                        });
                    }
                });
            }
        });
    }

    private void setupUI() {
        // blur background
        Blurry.with(getActivity())
                .radius(25)
                .sampling(8)
                .color(Color.argb(66, 255, 255, 255))
                .capture(mBg)
                .into(mBg);
        // setup view page promotions
        setupViewPage();
    }

    private void setupViewPage() {
        // set properties ViewPager' appearance like Gallery
        mViewPagePros.setOffscreenPageLimit(3);
        mViewPagePros.setClipToPadding(false);
        mViewPagePros.setPageMargin(-100);
        mViewPagePros.setPadding(80, 0, 100, 0);
        mViewPagePros.setPageTransformer(false, new ZoomOutPageTransformer());

        this.initialize();
        this.loadPromotionPages();
    }

    private void initialize() {
        this.mPromotionPagerPresenter.setView(this);
    }

    private void loadPromotionPages() {
        this.mPromotionPagerPresenter.initialize();
    }

    /**
     * Implements IPromotionPagerView's methods.
     */

    @Override
    public void renderPromotionViewPager(Collection<PromotionModel> promotionModels) {
        // UI 3rd Scene:
        // set adapter & handle item clicked
        PromotionPagerAdapter adapter = new PromotionPagerAdapter(getContext(), promotionModels);
        adapter.setOnItemClickListener(this.mOnPromotionItemClickListener);

        mViewPagePros.setAdapter(adapter);
        // set ScaleXY for animating
        mViewPagePros.setScaleX(0.2f);
        mViewPagePros.setScaleY(0.2f);
        mViewPagePros.animate().setDuration(ANIM_DURATION)
                .setInterpolator(sDecelerator)
                .alpha(1)
                .scaleX(1).scaleY(1).withStartAction(new Runnable() {
            @Override
            public void run() {
                // enable Circle Indicator
                mIndicator.setViewPager(mViewPagePros);
                mIndicator.setVisibility(View.VISIBLE);
                // set alpha to 0 & scale XY
                mIndicator.setAlpha(0);
                mIndicator.setScaleX(0.0f);
                mIndicator.setScaleY(0.0f);
                // then animating
                mIndicator.animate().setDuration(ANIM_DURATION)
                        .setInterpolator(sDecelerator)
                        .alpha(1)
                        .scaleY(1.0f).scaleX(1.0f);
            }
        });
    }

    @Override
    public void viewPromotion(PromotionModel model, View view) {
    }

    @Override
    public void showLoading() {
        mViewLoading.setVisibility(View.VISIBLE);
        mViewLoading.setAlpha(0.0f);
        mViewLoading.setScaleX(0.0f);
        mViewLoading.setScaleY(0.0f);
        mViewLoading.animate().setDuration(ANIM_DURATION)
        .alpha(1.0f).setInterpolator(sDecelerator)
        .scaleX(1.0f).scaleY(1.0f)
        ;
    }

    @Override
    public void hideLoading() {
        mViewLoading.setVisibility(View.GONE);
    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public Context getContext() {
        return getActivity().getApplicationContext();
    }
}
