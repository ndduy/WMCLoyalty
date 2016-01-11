package com.dinosys.wmcloyalty.ui.model;

/**
 * Created by Huynh
 * Since: 1/10/2016 - 15:38
 * Project: WMCLoyalty
 */
public class PromotionModel {

    private String mTitle;
    private String mHostName;
    private String mExpiredDate;
    private String mOldCost;
    private String mNewCost;

    public PromotionModel(String title,
                          String hostName,
                          String expiredDate,
                          String oldCost,
                          String newCost) {
        this.mTitle = title;
        this.mHostName = hostName;
        this.mExpiredDate = expiredDate;
        this.mOldCost = oldCost;
        this.mNewCost = newCost;
    }
}
