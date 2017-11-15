package com.bz.movier.presentation.main.di;

import com.bz.movier.presentation.di.PerFragment;
import com.bz.movier.presentation.favorite.FavoriteFragment;
import com.bz.movier.presentation.nowshowing.NowShowingFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Cuong Pham on 8/21/17.
 */
@Module
public abstract class MainFragmentContributeModule {
    @PerFragment
    @ContributesAndroidInjector
    abstract NowShowingFragment contributeNowShowingFragmentInjector();

    @PerFragment
    @ContributesAndroidInjector
    abstract FavoriteFragment contributeFavoriteFragmentInjector();
}
