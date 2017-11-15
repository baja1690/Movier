package com.bz.movier.domain.interactor;

import com.bz.movier.data.AppRepository;
import com.bz.movier.data.entities.MovieRealm;
import com.bz.movier.domain.executor.PostExecutionThread;
import com.bz.movier.domain.executor.ThreadExecutor;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import io.realm.RealmResults;

/**
 * Created by Cuong Pham on 11/13/17.
 */

public class FavoriteMovieUseCase extends BaseUseCase<FavoriteMovieUseCase.RequestValues,RealmResults<MovieRealm>>{
    AppRepository mRepository;

    public FavoriteMovieUseCase(ThreadExecutor threadExecutor,
                        PostExecutionThread postExecutionThread,
                        AppRepository repository) {
        super(threadExecutor, postExecutionThread);
        mRepository = repository;
    }

    @Override
    protected Flowable<RealmResults<MovieRealm>> createResultApi(RequestValues requestValues) throws Exception {
        return mRepository.getFavoriteMovies();
    }

    @Override
    public void execute(DisposableSubscriber subscriber) throws Exception {
        this.createResultApi(null)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public static final class RequestValues implements BaseUseCase.RequestValues {

    }

}
