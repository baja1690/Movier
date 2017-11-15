package com.bz.movier.data;

import com.bz.movier.data.entities.MovieRealm;
import com.bz.movier.data.local.RealmRepository;
import com.bz.movier.data.model.MovieResponse;
import com.bz.movier.data.remote.retrofit.AppRemoteData;
import com.bz.movier.domain.repository.Repository;
import com.bz.movier.common.util.Config;

import io.reactivex.Flowable;
import io.realm.RealmResults;

/**
 * Created by Cuong Pham on 11/9/17.
 */

public class AppRepository implements Repository {
    private AppRemoteData mRemoteData;
    private RealmRepository mLocalRepository;

    public AppRepository(AppRemoteData remoteData, RealmRepository localRepository) {
        this.mRemoteData = remoteData;
        this.mLocalRepository = localRepository;
    }

    @Override
    public Flowable<MovieResponse> getMovieList(int page) {
        return mRemoteData.getListMovies(Config.API_KEY, page);
    }

    @Override
    public Flowable<RealmResults<MovieRealm>> getFavoriteMovies() {
        return mLocalRepository.getFavoriteMovies();
    }

    public Flowable<MovieRealm> updateFavoriteMovie(MovieRealm movie) {
        return mLocalRepository.updateMovie(movie);
    }

    public void updateMovie(MovieRealm movie){
        mLocalRepository.updateMovieDb(movie);
    }

    public boolean isExistInDatabase(Integer movieId) {
        return mLocalRepository.isExistInDatabase(movieId);
    }

    public void closeRealm(){
        mLocalRepository.closeRealm();
    }
}