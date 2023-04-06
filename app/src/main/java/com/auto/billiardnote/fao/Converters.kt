package com.auto.billiardnote.fao

import androidx.room.TypeConverter
import com.auto.billiardnote.ui.home.draw.Balls
import com.auto.billiardnote.ui.home.draw.StraightLine
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromString(value: String): List<String> {
        return Gson().fromJson(value, object : TypeToken<List<String>>() {}.type)
    }

    @TypeConverter
    fun fromArrayList(list: List<String>): String {
        return Gson().toJson(list)
    }
    @TypeConverter
    fun fromLine(line: StraightLine): String {
        return Gson().toJson(line)
    }

    @TypeConverter
    fun toLine(lineString: String): StraightLine {
        return Gson().fromJson(lineString, object : TypeToken<StraightLine>() {}.type)
    }

    @TypeConverter
    fun fromBalls(balls: Balls): String {
        return Gson().toJson(balls)
    }

    @TypeConverter
    fun toBalls(ballsString: String): Balls {
        return Gson().fromJson(ballsString, object : TypeToken<Balls>() {}.type)
    }

//    @TypeConverter
//    fun fromBalls(balls: Balls): String {
//        val listType = object : TypeToken<List<String>>() {
//
//        }.type
//        return Gson().toJson(balls, listType)
//    }
//
//    @TypeConverter
//    fun toBalls(balls: String): Balls {
//        return Gson().fromJson(balls, object : TypeToken<Balls>() {}.type)
//    }
}