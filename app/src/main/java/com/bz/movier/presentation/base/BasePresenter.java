package com.bz.movier.presentation.base;

public abstract class BasePresenter<V> implements Presenter<V> {
    public static final String TAG = BasePresenter.class.getSimpleName();
    protected V mView;

    @Override
    public void onViewAttached(V view) {
        mView = view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }
}
