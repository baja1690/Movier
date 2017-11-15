package com.bz.movier.domain.executor;

import java.util.concurrent.Executor;

/**
 * Created by hungndl on 9/29/16.
 * Executor implementation can be based on different frameworks or techniques of asynchronous
 * execution, but every implementation will execute the UseCase out of the UI thread.
 */
public interface ThreadExecutor extends Executor {
    /**
     * Executes a {@link Runnable}
     *
     * @param runnable The class that implements {@link Runnable} interface.
     */
    void execute(final Runnable runnable);
}