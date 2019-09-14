package com.realpage.jj_kotlin_mars_rover.network

import com.realpage.jj_kotlin_mars_rover.data.RoverPhotoResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("curiosity/photos?sol=1000&page=1")
    fun getCuriosity(): Deferred<Response<RoverPhotoResponse>>


    @GET("{rover}/photos?")
    fun getImagesByRover(@Path("rover") rover:String, @Query("sol") sol:Int): Deferred<Response<RoverPhotoResponse>>

    @GET("spirit/photos?earth_date=2015-6-3&camera=fhaz")
    fun getImagesBySpirit(): Deferred<Response<RoverPhotoResponse>>

}

//    BASE URL "https://api.nasa.gov/mars-photos/api/v1/rovers"

//    EXAMPLE QUERIES
//    https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=DEMO_KEY
//
//    https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&camera=fhaz&api_key=DEMO_KEY
//
//    https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&page=2&api_key=DEMO_KEY
//    https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&page=2&api_key=DEMO_KEY
//    https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?earth_date=2015-6-3&api_key=DEMO_KEY
