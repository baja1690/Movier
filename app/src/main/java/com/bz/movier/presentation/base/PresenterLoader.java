package com.bz.movier.presentation.base;

import android.content.Context;
import android.support.v4.content.Loader;

/**
 * Created by Cuong Pham on 11/13/17.
 */

public class PresenterLoader<T extends BasePresenter> extends Loader<T> {
    private T presenter;

    /**
     * Stores away the application context associated with context.
     * Since Loaders can be used across multiple activities it's dangerous to
     * store the context directly; always use {@link #getContext()} to retrieve
     * the Loader's Context, don't use the constructor argument directly.
     * The Context returned by {@link #getContext} is safe to use across
     * Activity instances.
     *
     * @param context used to retrieve the application context.
     */
    public PresenterLoader(Context context, T presenter) {
        super(context);
        this.presenter = presenter;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if (presenter!=null){
            deliverResult(presenter);
            return;
        }
    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
        deliverResult(presenter);
    }

    @Override
    public void deliverResult(T data) {
        super.deliverResult(data);
    }


    @Override
    protected void onReset() {
        if (presenter!=null) {
            presenter.destroy();
            presenter = null;
        }
    }

    public T getPresenter(){
        return presenter;
    }
}
