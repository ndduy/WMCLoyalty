package com.dinosys.wmcloyalty.ui.view.welcome;

import android.view.View;

import com.dinosys.wmcloyalty.ui.fragment.base.IBaseView;
import com.dinosys.wmcloyalty.ui.model.PromotionModel;

import java.util.Collection;

/**
 * Created by Huynh
 * Since: 1/10/2016 - 17:40
 * Project: WMCLoyalty
 */
public interface IPromotionPagerView extends IBaseView {

    /**
     * Adapt list of promotions on AdapterView
     * @param promotionModels contains information
     */
    void renderPromotionViewPager(Collection<PromotionModel> promotionModels);

    /**
     * Show promotion details.
     * @param model contains information
     * @param view using for animating
     */
    void viewPromotion(PromotionModel model, View view);
}
