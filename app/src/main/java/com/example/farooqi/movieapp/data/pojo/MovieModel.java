package com.example.farooqi.movieapp.data.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by SAMSUNG on 3/12/2018.
 */

public class MovieModel implements Parcelable{

    int movieId;
    int voteCount;
    double voteAverage;
    double popularity;
    int[] genreId;
    String title;
    String posterPath;
    String overView;
    String releaseDate;

    public MovieModel(int movieId, int voteCount, double voteAverage,
                      double popularity, int[] genreId, String title, String posterPath,
                      String overView, String releaseDate) {
        this.movieId = movieId;
        this.voteCount = voteCount;
        this.voteAverage = voteAverage;
        this.popularity = popularity;
        this.genreId = genreId;
        this.title = title;
        this.posterPath = posterPath;
        this.overView = overView;
        this.releaseDate = releaseDate;
    }


    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public int[] getGenreId() {
        return genreId;
    }

    public void setGenreId(int[] genreId) {
        this.genreId = genreId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return "https://image.tmdb.org/t/p/w500" + posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOverView() {
        return overView;
    }

    public void setOverView(String overView) {
        this.overView = overView;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    protected MovieModel(Parcel in) {
        movieId = in.readInt();
        voteCount = in.readInt();
        voteAverage = in.readDouble();
        popularity = in.readDouble();
        genreId = in.createIntArray();
        title = in.readString();
        posterPath = in.readString();
        overView = in.readString();
        releaseDate = in.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(movieId);
        parcel.writeInt(voteCount);
        parcel.writeDouble(voteAverage);
        parcel.writeDouble(popularity);
        parcel.writeIntArray(genreId);
        parcel.writeString(title);
        parcel.writeString(posterPath);
        parcel.writeString(overView);
        parcel.writeString(releaseDate);

    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

}
