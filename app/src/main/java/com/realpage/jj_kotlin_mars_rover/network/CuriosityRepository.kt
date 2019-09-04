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
}