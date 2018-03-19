package com.example.farooqi.movieapp.data.pojo;

public class MovieDetailModel {
    public int movieId;
    public int voteCount;
    public int runTime;
    public double voteAverage;
    public double popularity;
    public String title;
    public String imdbId;
    public String posterPath;
    public String overView;
    public String releaseDate;
    public String homePage;
    public String status;
    public String tagLine;
    public String videoKey;
    public String[] countries;
    public String[] genreName;
    public String[] companies;

    public MovieDetailModel() {}

    public MovieDetailModel(int movieId, int voteCount, int runTime, double voteAverage,
                            double popularity, String title, String imdbId, String posterPath,
                            String overView, String releaseDate, String homePage, String status,
                            String tagLine, String videoKey, String[] countries, String[] genreName,
                            String[] companies) {
        this.movieId = movieId;
        this.voteCount = voteCount;
        this.runTime = runTime;
        this.voteAverage = voteAverage;
        this.popularity = popularity;
        this.title = title;
        this.imdbId = imdbId;
        this.posterPath = posterPath;
        this.overView = overView;
        this.releaseDate = releaseDate;
        this.homePage = homePage;
        this.status = status;
        this.tagLine = tagLine;
        this.videoKey = videoKey;
        this.countries = countries;
        this.genreName = genreName;
        this.companies = companies;
    }

    public String getPosterPath() {
        return "https://image.tmdb.org/t/p/w500" + posterPath;
    }
}
