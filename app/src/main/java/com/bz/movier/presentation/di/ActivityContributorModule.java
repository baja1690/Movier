package com.bz.movier.presentation.di;

import com.bz.movier.presentation.main.MainActivity;
import com.bz.movier.presentation.main.di.MainFragmentContributeModule;
import com.bz.movier.presentation.main.di.MainModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Cuong Pham on 11/9/17.
 */
@Module
public abstract class ActivityContributorModule {
    @PerActivity
    @ContributesAndroidInjector(modules = {MainModule.class, MainFragmentContributeModule.class})
    abstract MainActivity bindMainActivityInjector();
}
