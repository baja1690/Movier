package com.bz.movier.presentation.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import dagger.android.support.AndroidSupportInjection;

/**
 * Created by Cuong Pham on 11/8/17.
 */

public abstract class BaseFragment extends Fragment {
    protected View mView;

    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public Context getContext() {
        return super.getContext();
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onViewBindFinished(view, savedInstanceState);
    }

    public void onViewBindFinished(View view, @Nullable Bundle savedInstanceState) {

    }
}
