package com.dinosys.wmcloyalty.domain.executor;

import rx.Scheduler;

/**
 * Created by htsi.
 * Since: 1/16/16 on 3:40 PM
 * Project: WMCLoyalty
 */
public interface PostExcutionThread {
    Scheduler getScheduler();
}
