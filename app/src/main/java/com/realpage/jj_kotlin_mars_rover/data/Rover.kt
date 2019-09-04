package com.realpage.jj_kotlin_mars_rover.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Rover (
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "landing_date") val landing_date: String,
    @Json(name = "launch_date") val launch_date: String,
    @Json(name = "status") val status: String,
    @Json(name = "max_sol") val max_sol: Int,
    @Json(name = "max_date") val max_date: String,
    @Json(name = "total_photos") val total_photos: Int
//    @Json(name = "cameras") val cameras: List<Camera>
)