package com.haroldcalayan.gorest.data.source.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.haroldcalayan.gorest.data.model.Category

class CategoryConverter {

    companion object {

        private val gson = Gson()

        @TypeConverter
        @JvmStatic
        fun from(model: Category) = if(model == null) "" else gson.toJson(model)

        @TypeConverter
        @JvmStatic
        fun to(string: String) = gson.fromJson(string, Category::class.java)

    }
}