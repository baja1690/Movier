package com.bz.movier.presentation.favorite;

import com.bz.movier.data.entities.MovieRealm;
import com.bz.movier.data.model.Movie;
import com.bz.movier.domain.interactor.DefaultSubscriber;
import com.bz.movier.domain.interactor.FavoriteMovieUseCase;
import com.bz.movier.domain.interactor.MovieUseCase;
import com.bz.movier.presentation.base.BasePresenter;
import com.bz.movier.common.util.NullUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.realm.RealmResults;

/**
 * Created by Cuong Pham on 11/11/2017.
 */

public class FavoritePresenter extends BasePresenter<FavoriteView> {
    MovieUseCase mMovieUseCase;
    FavoriteMovieUseCase mFavoriteMovieUseCase;

    @Inject
    public FavoritePresenter(MovieUseCase movieUseCase, FavoriteMovieUseCase favoriteMovieUseCase) {
        this.mMovieUseCase = movieUseCase;
        this.mFavoriteMovieUseCase = favoriteMovieUseCase;
    }

    private void updateData(List<Movie> movies) {
        mView.bindMovies(movies);
    }

    public void getFavoriteMovie(){
        try {
            mFavoriteMovieUseCase.execute(new FavoritePresenter.FavoriteSubscriber());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateMovieOnDb(Movie movie) {
        try {
            mMovieUseCase.updateMovie(new MovieRealm(movie));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private class FavoriteSubscriber extends DefaultSubscriber<RealmResults<MovieRealm>> {
        @Override
        public void onNext(RealmResults<MovieRealm> favorites) {
            super.onNext(favorites);
            List<Movie> movies = new ArrayList<>();
            if (!NullUtil.isNullOrEmpty(favorites)){
                for (MovieRealm m : favorites){
                    movies.add(new Movie(m));
                }
            }
            updateData(movies);
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }

        @Override
        public void onError(Throwable t) {
            super.onError(t);
        }
    }

    @Override
    public void onViewDetached() {
        mView = null;
    }

    @Override
    public void destroy() {
        super.destroy();
        closeRealm();
    }

    @Override
    public void closeRealm() {
        mMovieUseCase.closeDb();
    }
}
