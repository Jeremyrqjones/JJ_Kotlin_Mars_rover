package com.realpage.jj_kotlin_mars_rover.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.realpage.jj_kotlin_mars_rover.data.Photo
import com.realpage.jj_kotlin_mars_rover.network.ApiBuilder
import com.realpage.jj_kotlin_mars_rover.network.CuriosityRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CuriosityViewModel : ViewModel(){

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    private val repository : CuriosityRepository = CuriosityRepository(ApiBuilder.apiService)


    val curiosityPhotosLiveData = MutableLiveData<List<Photo>>()

    fun fetchCuriosityPhotos(){
        scope.launch {
            val curiosityPhotos = repository.getCuriosityPhotos()
            curiosityPhotosLiveData.postValue(curiosityPhotos)
        }
    }

    fun fetchImagesByRover(rover: String, sol: Int){
            scope.launch { withContext(Dispatchers.Unconfined) {
                val roverPhotos = repository.getImagesByRover(rover, sol)
                curiosityPhotosLiveData.postValue(null)
                curiosityPhotosLiveData.postValue(roverPhotos)
            }
        }
    }

    fun fetchImagesBySpirit(){
        scope.launch {
            val roverPhotos = repository.getImagesBySpirit()
            if(!roverPhotos.isNullOrEmpty()) {
                curiosityPhotosLiveData.postValue(null)
                curiosityPhotosLiveData.postValue(roverPhotos)
            }
        }
    }


    fun cancelAllRequests() = coroutineContext.cancel()
}