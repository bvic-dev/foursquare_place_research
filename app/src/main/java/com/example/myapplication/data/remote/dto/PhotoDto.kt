package com.example.myapplication.data.remote.dto

data class PhotoDto(
    val classifications: List<String>,
    val created_at: String,
    val height: Int,
    val prefix: String,
    val suffix: String,
    val width: Int
) {
    fun getUri(): String {
        return "${prefix}original$suffix"
    }
}
