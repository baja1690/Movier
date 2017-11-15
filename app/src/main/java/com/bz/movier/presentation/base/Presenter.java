package com.bz.movier.presentation.base;

public interface Presenter<V> {
    /**
     * @param view View data type
     */
    void onViewAttached(V view);

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onResume() method.
     */
    void resume();

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) pause() method.
     */
    void pause();

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) destroy() method.
     */
    void destroy();

    void onViewDetached();

    void closeRealm();
}
