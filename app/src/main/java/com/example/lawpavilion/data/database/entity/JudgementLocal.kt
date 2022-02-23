package com.example.lawpavilion.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class JudgementLocal(
    @PrimaryKey val code: String = "DEF101",
    @ColumnInfo val title: String?,
    @ColumnInfo val type: String?,
    @ColumnInfo val intro: String?,
    @ColumnInfo val text: String?,
    @ColumnInfo val extra: String?
)
