package com.realpage.jj_kotlin_mars_rover.network

import com.realpage.jj_kotlin_mars_rover.data.Photo

class CuriosityRepository(private val api : ApiService) : BaseRepository() {
    suspend fun  getCuriosityPhotos() : List<Photo>?{

        val photoResponse = safeApiCall(
            call = {api.getCuriosity().await()},
            errorMessage = "Error Fetching Curiosity Photos"
        )

        return photoResponse?.results
    }

    suspend fun getImagesByRover(rover:String, sol:Int) : List<Photo>?{

        val photoResponse = safeApiCall(
            call = {api.getImagesByRover(rover, sol).await()},
            errorMessage = "Error Fetching images"
        )

        return photoResponse?.results
    }

    suspend fun getImagesBySpirit() : List<Photo>?{

        val photoResponse = safeApiCall(
            call = {api.getImagesBySpirit().await()},
            errorMessage = "Error Fetching images"
        )

        return photoResponse?.results
    }
}