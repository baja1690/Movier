package com.bz.movier.presentation.nowshowing;

import com.bz.movier.common.util.NullUtil;
import com.bz.movier.data.entities.MovieRealm;
import com.bz.movier.data.model.Movie;
import com.bz.movier.data.model.MovieResponse;
import com.bz.movier.domain.interactor.DefaultSubscriber;
import com.bz.movier.domain.interactor.FavoriteMovieUseCase;
import com.bz.movier.domain.interactor.MovieUseCase;
import com.bz.movier.presentation.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.realm.RealmResults;

/**
 * Created by Cuong Pham on 11/9/17.
 */

public class NowShowingPresenter extends BasePresenter<NowShowingView>{
    public static final int INIT_PAGE = 1;
    MovieUseCase mMovieUseCase;
    private MovieResponse mMovieResponse;
    private FavoriteMovieUseCase mFavoriteMovieUseCase;
    private boolean isLoading = false;
    private List<Movie> movies;


    @Inject
    public NowShowingPresenter(MovieUseCase movieUseCase, FavoriteMovieUseCase favoriteMovieUseCase) {
        this.mMovieUseCase = movieUseCase;
        this.mFavoriteMovieUseCase = favoriteMovieUseCase;
    }

    @Override
    public void onViewAttached(NowShowingView view) {
        super.onViewAttached(view);
        if (!NullUtil.isNullOrEmpty(movies)){
            mView.reBindMovies(movies);
            movies = null;
        }
    }

    public void onOrientationChange(List<Movie> movies){
        this.movies = movies;
    }

    public void getMovies(){
        try {
            int page = (mMovieResponse == null || NullUtil.isNullOrEmpty(mMovieResponse.getMovies()))? INIT_PAGE : (mMovieResponse.getPage()+ 1);
            isLoading = true;
            mView.showBottomLoading();
            mMovieUseCase.setRequestValues(new MovieUseCase.RequestValues(page));
            mMovieUseCase.execute(new MovieSubscriber());
        } catch (Exception e) {
            e.printStackTrace();
            isLoading = false;
            mView.hideBottomLoading();
        }
    }

    public void updateData(MovieResponse movieResponse){
        mMovieResponse = movieResponse;
        mView.bindMovies(movieResponse);
    }

    public void loadMore() {
        if (isLoading == false && mMovieResponse != null && mMovieResponse.getTotalPages() > mMovieResponse.getPage()) {
            getMovies();
        }
    }

    public void updateMovieOnDb(Movie movie) {
        try {
            //mInsertUpdateMovieUseCase.setRequestValues(new InsertUpdateMovieUseCase.RequestValues(movie));
            //mInsertUpdateMovieUseCase.execute(new InsertUpdateMovieUseCaseSubscriber());
            mMovieUseCase.updateMovie(new MovieRealm(movie));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void handleUpdate(MovieRealm movie) {
        if (movie!=null) {
            mView.updateMovie(new Movie(movie));
        }
    }

    private class MovieSubscriber extends DefaultSubscriber<MovieResponse>{
        @Override
        public void onNext(MovieResponse movieResponse) {
            super.onNext(movieResponse);
            List<Movie> movies = movieResponse.getMovies();
            for (Movie movie : movies) {
                movie.setLike(mMovieUseCase.isExistInDatabase(movie.getId()));
            }
            updateData(movieResponse);
        }

        @Override
        public void onComplete() {
            mView.hideBottomLoading();
            isLoading = false;
            super.onComplete();
        }

        @Override
        public void onError(Throwable t) {
            mView.hideBottomLoading();
            isLoading = false;
            super.onError(t);
            mView.onLoadingError();
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
           // updateData(movies);
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