package com.example.myapplication.data.remote.dto

import com.example.myapplication.domain.model.Category

data class CategoryDto(
    val icon: IconDto,
    val id: Int,
    val name: String
) {
    fun toCategory(): Category {
        return Category(
            icon = icon.toUri(),
            name = name
        )
    }
}
