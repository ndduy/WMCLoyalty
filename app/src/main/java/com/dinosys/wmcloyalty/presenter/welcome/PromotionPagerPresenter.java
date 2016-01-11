package com.dinosys.wmcloyalty.presenter.welcome;

import android.os.Handler;

import com.dinosys.wmcloyalty.R;
import com.dinosys.wmcloyalty.presenter.base.IBasePresenter;
import com.dinosys.wmcloyalty.ui.model.PromotionModel;
import com.dinosys.wmcloyalty.ui.view.welcome.IPromotionPagerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Huynh
 * Since: 1/10/2016 - 17:53
 * Project: WMCLoyalty
 */
public class PromotionPagerPresenter implements IBasePresenter<IPromotionPagerView> {

    private IPromotionPagerView mPromotionPagerView;

    @Override
    public void setView(IPromotionPagerView view) {
        this.mPromotionPagerView = view;
    }

    public void initialize() {
        this.mPromotionPagerView.showLoading();
        this.loadPromotionsData();
    }

    private void loadPromotionsData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                PromotionPagerPresenter.this.mPromotionPagerView.hideLoading();
                PromotionPagerPresenter.this.mPromotionPagerView.renderPromotionViewPager(populateData());
            }
        }, 3000);
    }

    private List<PromotionModel> populateData() {
        List<PromotionModel> promotionModels = new ArrayList<>();
        String newCost = getString(R.string.new_cost);
            for (int i=0; i<5; i++) {
                String title = getString(R.string.pro_title);
                String hostname = getString(R.string.host_name);
                String expiredDate = getString(R.string.expired_date);
                String oldCost = getString(R.string.old_cost);
                PromotionModel promotionModel = new PromotionModel(title, hostname, expiredDate, oldCost, newCost);
            promotionModels.add(promotionModel);
        }
        return promotionModels;
    }

    private String getString(int id) {
        return this.mPromotionPagerView.getContext().getString(id);
    }
}
