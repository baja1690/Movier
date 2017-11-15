package com.bz.movier.domain.interactor;

import com.bz.movier.data.AppRepository;
import com.bz.movier.data.entities.MovieRealm;
import com.bz.movier.data.model.MovieResponse;
import com.bz.movier.domain.executor.PostExecutionThread;
import com.bz.movier.domain.executor.ThreadExecutor;

import io.reactivex.Flowable;


public class MovieUseCase extends BaseUseCase<MovieUseCase.RequestValues,MovieResponse>{

    AppRepository mRepository;

    public MovieUseCase(ThreadExecutor threadExecutor,
                        PostExecutionThread postExecutionThread,
                        AppRepository repository) {
        super(threadExecutor, postExecutionThread);
        mRepository = repository;
    }

    @Override
    protected Flowable<MovieResponse> createResultApi(RequestValues requestValues) throws Exception {
        return mRepository.getMovieList(getRequestValues().page);
    }

    public boolean isExistInDatabase(Integer movieId) {
        return mRepository.isExistInDatabase(movieId);
    }

    public void closeDb() {
        mRepository.closeRealm();
    }

    public static final class RequestValues implements BaseUseCase.RequestValues {
        private int page;

        public RequestValues(int page) {
            this.page = page;
        }
    }

    public void updateMovie(MovieRealm movie){
        mRepository.updateMovie(movie);
    }
}