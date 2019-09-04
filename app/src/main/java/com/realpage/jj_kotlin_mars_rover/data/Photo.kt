package com.realpage.jj_kotlin_mars_rover.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Photo (
    @Json(name = "id") val id: Int,
    @Json(name = "sol") val sol: Int,
    @Json(name = "camera") val camera: Camera,
    @Json(name = "img_src") val img_src: String,
    @Json(name = "earth_date") val earth_date: String,
    @Json(name = "rover") val rover: Rover
)