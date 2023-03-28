package com.auto.billiardnote.fao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.auto.billiardnote.ui.home.draw.Ball
import com.auto.billiardnote.ui.home.draw.StraightLine

@Entity
class NoteInfo {
    @PrimaryKey
    var nid = 0

    @ColumnInfo(name = "name")
    var name: String? = null

    @ColumnInfo(name = "straight_line")
    var straightLine: StraightLine? = null

    @ColumnInfo(name = "balls")
    var balls: HashSet<Ball>? = null

    @ColumnInfo(name = "memo")
    var memo: String? = null
}