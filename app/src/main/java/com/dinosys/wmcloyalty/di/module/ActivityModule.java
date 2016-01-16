package com.dinosys.wmcloyalty.di.module;

import android.support.v7.app.AppCompatActivity;

import com.dinosys.wmcloyalty.di.base.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by htsi.
 * Since: 1/16/16 on 4:35 PM
 * Project: WMCLoyalty
 */
@Module
public class ActivityModule {

    private final AppCompatActivity mAppCompatActivity;

    public ActivityModule(AppCompatActivity appCompatActivity) {
        this.mAppCompatActivity = appCompatActivity;
    }

    @Provides
    @PerActivity
    AppCompatActivity activity() {
        return this.mAppCompatActivity;
    }
}
