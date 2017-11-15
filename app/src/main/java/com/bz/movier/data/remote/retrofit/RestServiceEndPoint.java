package com.bz.movier.data.remote.retrofit;

import com.bz.movier.data.model.MovieResponse;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestServiceEndPoint {
   @GET("3/movie/now_playing")
   Flowable<MovieResponse> getListMovies(@Query("api_key") String api_key, @Query("page") int page);
}
