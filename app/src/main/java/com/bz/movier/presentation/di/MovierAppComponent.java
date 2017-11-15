package com.bz.movier.presentation.di;

import com.bz.movier.presentation.MovierApplication;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * Created by Cuong Pham on 11/9/17.
 */
@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, NetModule.class, ActivityContributorModule.class})
public interface MovierAppComponent extends AndroidInjector<MovierApplication> {
}
