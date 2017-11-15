package com.bz.movier.domain.repository;

import com.bz.movier.data.entities.MovieRealm;
import com.bz.movier.data.model.MovieResponse;

import io.reactivex.Flowable;
import io.realm.RealmResults;

/**
 * Created by Cuong Pham on 8/14/17.
 */

public interface Repository {
    Flowable<MovieResponse> getMovieList(int page);

    Flowable<RealmResults<MovieRealm>> getFavoriteMovies();
}
