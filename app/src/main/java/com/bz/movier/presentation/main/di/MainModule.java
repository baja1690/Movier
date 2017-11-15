package com.bz.movier.presentation.main.di;

import com.bz.movier.data.AppRepository;
import com.bz.movier.data.local.RealmRepository;
import com.bz.movier.data.remote.retrofit.AppRemoteData;
import com.bz.movier.domain.executor.PostExecutionThread;
import com.bz.movier.domain.executor.ThreadExecutor;
import com.bz.movier.domain.interactor.FavoriteMovieUseCase;
import com.bz.movier.domain.interactor.InsertUpdateMovieUseCase;
import com.bz.movier.domain.interactor.MovieUseCase;
import com.bz.movier.presentation.di.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Cuong Pham on 11/9/17.
 */

@Module
public class MainModule {
    public MainModule() {
    }

    @Provides
    @PerActivity
    public AppRepository provideGenreRepository(AppRemoteData remoteData, RealmRepository realmRepository) {
        return new AppRepository(remoteData, realmRepository);
    }

    @Provides
    @PerActivity
    public MovieUseCase provideMovieUseCase(ThreadExecutor threadExecutor,
                                            PostExecutionThread postExecutionThread,
                                            AppRepository repository) {
        return new MovieUseCase(threadExecutor, postExecutionThread, repository);
    }

    @Provides
    @PerActivity
    public InsertUpdateMovieUseCase provideInsertUpdateMovieUseCase(ThreadExecutor threadExecutor,
                                                        PostExecutionThread postExecutionThread,
                                                        AppRepository repository) {
        return new InsertUpdateMovieUseCase(threadExecutor, postExecutionThread, repository);
    }

    @Provides
    @PerActivity
    public FavoriteMovieUseCase provideFavoriteMovieUseCase(ThreadExecutor threadExecutor,
                                            PostExecutionThread postExecutionThread,
                                            AppRepository repository) {
        return new FavoriteMovieUseCase(threadExecutor, postExecutionThread, repository);
    }
}
