package com.auto.billiardnote.fao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.auto.billiardnote.ui.home.draw.Balls
import com.auto.billiardnote.ui.home.draw.StraightLine

@Entity(tableName = "note")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int =0,
    @ColumnInfo(name = "line") val straightLine: StraightLine,
    @ColumnInfo(name = "balls") val balls: Balls,
    @ColumnInfo(name = "memo") val memo: String,
    @ColumnInfo(name = "name") var name: String
)
