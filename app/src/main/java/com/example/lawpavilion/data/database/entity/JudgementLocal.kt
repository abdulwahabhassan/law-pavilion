package com.example.lawpavilion.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class JudgementLocal(
    val code: String,
    val title: String,
    val type: String,
    val intro: String,
    val text: String,
    val extra: String
)
