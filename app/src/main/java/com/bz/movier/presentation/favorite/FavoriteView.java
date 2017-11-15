package com.bz.movier.presentation.favorite;

import com.bz.movier.data.model.Movie;
import com.bz.movier.presentation.base.BaseView;

import java.util.List;

/**
 * Created by Cuong Pham on 11/11/2017.
 */

public interface FavoriteView extends BaseView {
    void bindMovies(List<Movie> movies);
}
