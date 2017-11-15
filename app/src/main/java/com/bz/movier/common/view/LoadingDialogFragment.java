package com.bz.movier.common.view;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by Cuong Pham on 11/15/17.
 */

public class LoadingDialogFragment extends DialogFragment {

    public static final String TAG = "loading_dialog";

    private String mLoadingMessage;

    /**
     * newInstance
     *
     * @return instance of LoadingDialogFragment class
     */
    public static LoadingDialogFragment newInstance() {
        LoadingDialogFragment fragment = new LoadingDialogFragment();
        return fragment;
    }

    /**
     * show diaglog
     *
     * @param manager FragmentManager
     */
    public static void showLoading(FragmentManager manager, String loadingMessage) {
        newInstance().setMessage(loadingMessage).show(manager, TAG);
    }

    /**
     * dismiss dialog
     *
     * @param manager FragmentManager
     */
    public static void dismissLoading(FragmentManager manager) {
        if (manager != null) {
            Fragment fragment = manager.findFragmentByTag(TAG);
            if (fragment != null) {
                ((LoadingDialogFragment) fragment).dismissAllowingStateLoss();
            }
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ProgressDialog dialog = new ProgressDialog(getActivity(), getTheme());
        dialog.setMessage(mLoadingMessage);
        dialog.setIndeterminate(true);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(this, tag);
        ft.commitAllowingStateLoss();
    }

    public LoadingDialogFragment setMessage(String loadingMessage) {
        mLoadingMessage = loadingMessage;
        return this;
    }

}