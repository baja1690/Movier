package com.bz.movier.presentation.main.di;

import com.bz.movier.presentation.main.MainActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;


@Subcomponent(modules = {MainFragmentContributeModule.class, MainModule.class})
public interface MainSubComponent extends AndroidInjector<MainActivity>{
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity> {}
}
