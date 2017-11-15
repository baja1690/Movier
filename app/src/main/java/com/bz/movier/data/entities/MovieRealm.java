package com.bz.movier.data.entities;

import com.bz.movier.data.model.Movie;

import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Cuong Pham on 11/8/17.
 */
public class MovieRealm extends RealmObject{
    private Boolean adult;
    private String backdropPath;
    @Ignore
    private List<Integer> genreIds = null;
    @PrimaryKey
    private Integer id;
    private String originalLanguage;
    private String originalTitle;
    private String overview;
    private String releaseDate;
    private String posterPath;
    private Double popularity;
    private String title;
    private Boolean video;
    private Double voteAverage;
    private Integer voteCount;
    private boolean like;

    public MovieRealm() {
    }

    public MovieRealm(Movie movie) {
        convertFromRealmTonEntities(movie);
    }

    public void convertFromRealmTonEntities(Movie movie){
        this.id = movie.getId();
        this.adult = movie.getAdult();
        this.backdropPath = movie.getBackdropPath();
        this.title = movie.getTitle();
        this.voteCount = movie.getVoteCount();
        this.like = movie.isLike();
        this.posterPath = movie.getPosterPath();
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }
}
