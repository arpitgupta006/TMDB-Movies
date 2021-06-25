package com.arpit.newsapp3

import com.arpit.tmdbtopmovies.ResponseMovie
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val base_url = "https://api.themoviedb.org/"
const val api_key = "87549592e55f41b986f248237a219d90#"


interface APIService {
    @GET("3/movie/top_rated?api_key=$api_key")
     suspend fun getMovies() : Response<ResponseMovie>
}

object  moviesService {
    val apiService : APIService
//    val apiService : APIService
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(APIService::class.java)
    }
}



