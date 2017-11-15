package com.bz.movier.presentation.nowshowing;

import com.bz.movier.data.model.Movie;
import com.bz.movier.data.model.MovieResponse;
import com.bz.movier.presentation.base.BaseView;

import java.util.List;

/**
 * Created by Cuong Pham on 11/9/17.
 */

public interface NowShowingView extends BaseView{

    void bindMovies(MovieResponse movieResponse);

    void showBottomLoading();

    void hideBottomLoading();

    void updateMovie(Movie movie);

    void reBindMovies(List<Movie> movies);

    void onLoadingError();
}
