package com.dinosys.wmcloyalty.di.module;

import android.content.Context;

import com.dinosys.wmcloyalty.app.LoyaltyApplication;
import com.dinosys.wmcloyalty.data.executor.JobExecutor;
import com.dinosys.wmcloyalty.domain.executor.ThreadExecutor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by htsi on 1/16/16.
 */
@Module
public class ApplicationModule {

    private final LoyaltyApplication mLoyaltyApplication;

    public ApplicationModule(LoyaltyApplication loyaltyApplication) {
        this.mLoyaltyApplication = loyaltyApplication;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.mLoyaltyApplication;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }
}
