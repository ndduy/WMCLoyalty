package com.dinosys.wmcloyalty.di.component;

import android.support.v7.app.AppCompatActivity;

import com.dinosys.wmcloyalty.di.base.PerActivity;
import com.dinosys.wmcloyalty.di.module.ActivityModule;

import dagger.Component;

/**
 * Created by htsi.
 * Since: 1/16/16 on 5:22 PM
 * Project: WMCLoyalty
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    AppCompatActivity activity();
}
