package com.realpage.jj_kotlin_mars_rover.data

//import androidx.room.ColumnInfo
//import androidx.room.Entity
//import androidx.room.PrimaryKey


//@Entity(tableName = "posts")
data class Post(
//    @PrimaryKey @ColumnInfo(name = "id")
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
) {
    override fun toString() = title
}