package com.dinosys.wmcloyalty.ui.fragment.base;

import android.content.Context;

/**
 * Created by Huynh
 * Since: 1/10/2016 - 17:40
 * Project: WMCLoyalty
 */
public interface IBaseView {
    void showLoading();
    void hideLoading();
    void showRetry();
    void hideRetry();
    Context getContext();
}
