package com.bz.movier.data.remote.retrofit;

import com.bz.movier.data.model.MovieResponse;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created by Cuong Pham on 11/9/17.
 */

public class AppRemoteData implements RestServiceEndPoint {
    private RestServiceEndPoint restApi;

    @Inject
    public AppRemoteData(RestServiceEndPoint restApi) {
        this.restApi = restApi;
    }
    @Override
    public Flowable<MovieResponse> getListMovies(String api_key, int page) {
        return restApi.getListMovies(api_key, page);
    }
}
