package com.bz.movier.domain.interactor;

import android.util.Log;

import io.reactivex.subscribers.DisposableSubscriber;

public class DefaultSubscriber<T> extends DisposableSubscriber<T> {

    @Override
    public void onNext(T t) {

    }

    //TODO: implement some thing with this error
    @Override
    public void onError(Throwable t) {
        Log.d("Tag","Error: "+t.toString());
    }

    @Override
    public void onComplete() {

    }
}
