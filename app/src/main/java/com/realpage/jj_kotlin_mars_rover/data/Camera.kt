package com.realpage.jj_kotlin_mars_rover.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Camera (
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "rover_id") val rover_id: Int,
    @Json(name = "full_name") val full_name: String

)