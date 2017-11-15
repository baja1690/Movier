package com.bz.movier.data.local;


import com.bz.movier.data.entities.MovieRealm;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Cuong Pham on 11/10/17.
 */

public class RealmRepository {
    private Realm mRealm;

    @Inject
    public RealmRepository(Realm realm) {
        this.mRealm = realm;
    }

    public Flowable<MovieRealm> updateMovie(MovieRealm movie) {
        mRealm = Realm.getDefaultInstance();
        return mRealm.copyToRealmOrUpdate(movie).asFlowable();
    }

    public void updateMovieDb(MovieRealm movie) {
        mRealm = Realm.getDefaultInstance();
        try {
            mRealm.executeTransaction(realm -> {
                MovieRealm m = mRealm.copyToRealmOrUpdate(movie);
            });
        }catch (Exception e){

        }
    }

    public Flowable<RealmResults<MovieRealm>> getFavoriteMovies(){
        mRealm = Realm.getDefaultInstance();
        return mRealm.where(MovieRealm.class)
                .equalTo("like",true)
                .findAllAsync().asFlowable();
    }

    public boolean isExistInDatabase(Integer movieId) {
        if (mRealm == null) mRealm = Realm.getDefaultInstance();
        return mRealm.where(MovieRealm.class)
                .equalTo("like",true)
                .equalTo("id",movieId)
                .findFirst() != null;
    }

    public void closeRealm(){
        mRealm.close();
    }
}