package com.example.farooqi.movieapp.data;

abstract public class Preferences {

    public static final String IN_THEATRE_MOVIE_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed&language=en-US&page=";
    public static final String IN_THEATRE_MOVIE_URL_ONE = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed&language=en-US&page=1";

    public static final String IN_COMMING_MOVIE_URL = "";
    public static final String IN_COMMING_MOVIE_URL_ONE = "https://api.themoviedb.org/3/movie/upcoming?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed&language=en-US&page=1";

    public static final String MOVIE_BASE_URL = "https://api.themoviedb.org/3/movie/";
    public static final String MOVIE_Footer_URL = "?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed&language=en-US&append_to_response=videos,credits";

    public static final String MOVIE_ID_KEY = "movie_id";

}
