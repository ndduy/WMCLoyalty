package com.dinosys.wmcloyalty.app;

import com.dinosys.wmcloyalty.di.component.ApplicationComponent;
import com.dinosys.wmcloyalty.di.component.DaggerApplicationComponent;
import com.dinosys.wmcloyalty.di.module.ApplicationModule;
import com.dinosys.wmcloyalty.util.UIUtil;

/**
 * Created by Huynh
 * Since: 1/9/2016 - 08:10
 * Project: TinhTeLite
 */
public class LoyaltyApplication extends android.app.Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        //  Set default font to Roboto
        UIUtil.setDefaultFont(this, "DEFAULT", Constants.APP_DEFAULT_FONT);
        UIUtil.setDefaultFont(this, "MONOSPACE", Constants.APP_DEFAULT_LIGHT_FONT);
        UIUtil.setDefaultFont(this, "SERIF", Constants.APP_DEFAULT_FONT);
        UIUtil.setDefaultFont(this, "SANS_SERIF", Constants.APP_DEFAULT_FONT);

        initInjector();
    }

    private void initInjector() {
        this.mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
