package com.example.myapplication.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.myapplication.data.util.JsonParser
import com.example.myapplication.domain.model.Category
import com.example.myapplication.domain.model.Geocodes
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class PlaceConverters(
    private val jsonParser: JsonParser
) {
    @TypeConverter
    fun fromGeocodesJson(json: String): Geocodes {
        return jsonParser.fromJson<Geocodes>(
            json,
            object : TypeToken<Geocodes>() {}.type
        ) ?: Geocodes(0.0, 0.0)
    }

    @TypeConverter
    fun toGeocodesJson(geocodes: Geocodes): String {
        return jsonParser.toJson(
            geocodes,
            object : TypeToken<Geocodes>() {}.type
        ) ?: ""
    }

    @TypeConverter
    fun fromCategoryJson(json: String): Category {
        return jsonParser.fromJson<Category>(
            json,
            object : TypeToken<Category>() {}.type
        ) ?: Category(icon = "", name = "")
    }

    @TypeConverter
    fun toCategoryJson(category: Category): String {
        return jsonParser.toJson(
            category,
            object : TypeToken<Category>() {}.type
        ) ?: ""
    }
}
