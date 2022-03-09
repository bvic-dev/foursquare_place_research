package com.example.myapplication.data.remote.dto

data class IconDto(
    val prefix: String,
    val suffix: String
) {
    fun toUri(): String {
        return "${prefix}120$suffix"
    }
}
