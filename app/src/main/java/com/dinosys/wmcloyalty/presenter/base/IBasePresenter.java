package com.dinosys.wmcloyalty.presenter.base;


/**
 * Created by Huynh
 * Since: 1/10/2016 - 17:36
 * Project: WMCLoyalty
 */
public interface IBasePresenter<ViewType> {

    void setView(ViewType view);

    void destroyView();
}
