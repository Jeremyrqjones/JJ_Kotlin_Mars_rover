package com.realpage.jj_kotlin_mars_rover.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RoverPhotoResponse (
    @Json(name = "photos") val results: List<Photo>
)