package com.bz.movier.domain.interactor;


import android.support.annotation.NonNull;

import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by Cuong Pham on 7/31/17.
 */

public interface UseCase {
    void execute(@NonNull DisposableSubscriber subscriber) throws Exception;
}
