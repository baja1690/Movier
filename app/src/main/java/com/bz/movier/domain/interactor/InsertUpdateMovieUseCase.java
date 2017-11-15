package com.bz.movier.domain.interactor;

import com.bz.movier.data.AppRepository;
import com.bz.movier.data.entities.MovieRealm;
import com.bz.movier.data.model.Movie;
import com.bz.movier.domain.executor.PostExecutionThread;
import com.bz.movier.domain.executor.ThreadExecutor;

import io.reactivex.Flowable;

/**
 * Created by Cuong Pham on 11/13/17.
 */

public class InsertUpdateMovieUseCase extends BaseUseCase<InsertUpdateMovieUseCase.RequestValues,MovieRealm>{
    AppRepository mRepository;

    public InsertUpdateMovieUseCase(ThreadExecutor executor, PostExecutionThread postExecutionThread,
                                    AppRepository repository) {
        super(executor, postExecutionThread);
        mRepository = repository;
    }

    @Override
    protected Flowable<MovieRealm> createResultApi(InsertUpdateMovieUseCase.RequestValues requestValues) throws Exception {
        return mRepository.updateFavoriteMovie(requestValues.movie);
    }

    public static final class RequestValues implements BaseUseCase.RequestValues{
        protected MovieRealm movie;

        public RequestValues(Movie movie) {
            this.movie = new MovieRealm(movie);
        }

        public Movie getMovie() {
            return new Movie(movie);
        }
    }
}
