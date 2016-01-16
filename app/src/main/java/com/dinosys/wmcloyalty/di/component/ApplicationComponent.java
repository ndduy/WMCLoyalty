package com.dinosys.wmcloyalty.di.component;

import android.content.Context;

import com.dinosys.wmcloyalty.di.module.ApplicationModule;
import com.dinosys.wmcloyalty.domain.executor.ThreadExecutor;
import com.dinosys.wmcloyalty.ui.activity.base.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by htsi on 1/16/16.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(BaseActivity baseActivity);

    Context context();

    ThreadExecutor threadExecutor();
}
