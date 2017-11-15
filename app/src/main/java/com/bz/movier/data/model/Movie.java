package com.bz.movier.data.model;

import com.bz.movier.data.entities.MovieRealm;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Cuong Pham on 11/8/17.
 */

public class Movie {
    @SerializedName("adult")
    private Boolean adult;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("genre_ids")
    private List<Integer> genreIds = null;
    @SerializedName("id")
    private Integer id;
    @SerializedName("original_language")
    private String originalLanguage;
    @SerializedName("original_title")
    private String originalTitle;
    @SerializedName("overview")
    private String overview;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("popularity")
    private Double popularity;
    @SerializedName("title")
    private String title;
    @SerializedName("video")
    private Boolean video;
    @SerializedName("vote_average")
    private Double voteAverage;
    @SerializedName("vote_count")
    private Integer voteCount;
    private boolean like;

    public Movie() {
    }

    public Movie(String backdropPath, Integer voteCount) {
        this.posterPath = backdropPath;
        this.voteCount = voteCount;
    }

    public Movie(MovieRealm movie){
        convertToRealmObject(movie);
    }

    public void convertToRealmObject(MovieRealm movie){
        this.id = movie.getId();
        this.adult = movie.getAdult();
        this.backdropPath = movie.getBackdropPath();
        this.title = movie.getTitle();
        this.voteCount = movie.getVoteCount();
        this.like = movie.isLike();
        this.posterPath = movie.getPosterPath();
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public Boolean getAdult() {
        return adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public Integer getId() {
        return id;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public Double getPopularity() {
        return popularity;
    }

    public String getTitle() {
        return title;
    }

    public Boolean getVideo() {
        return video;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public Integer getVoteCount() {
        return voteCount;
    }
}
