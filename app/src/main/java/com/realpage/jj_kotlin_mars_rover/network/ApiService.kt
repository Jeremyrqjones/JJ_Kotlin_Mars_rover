package com.realpage.jj_kotlin_mars_rover.network

import com.realpage.jj_kotlin_mars_rover.data.RoverPhotoResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("curiosity/photos?sol=1000")
    fun getCuriosity(): Deferred<Response<RoverPhotoResponse>>


//    @GET("movie/{id}")
//    fun getMovieById(@Path("id") id:Int): Deferred<Response<Movie>>

}

//    BASE URL "https://api.nasa.gov/mars-photos/api/v1/rovers"

//    EXAMPLE QUERIES
//    https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=DEMO_KEY
//
//    https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&camera=fhaz&api_key=DEMO_KEY
//
//    https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&page=2&api_key=DEMO_KEY