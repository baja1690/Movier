package com.bz.movier.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Cuong Pham on 11/8/17.
 */

public class MovieResponse {
    @SerializedName("page")
    private Integer page;
    @SerializedName("results")
    private List<Movie> movies = null;
    @SerializedName("total_pages")
    private Integer totalPages;
    @SerializedName("total_results")
    private Integer totalResults;

    public MovieResponse() {

    }

    public Integer getPage() {
        return page;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public Integer getTotalResults() {
        return totalResults;
    }
}
