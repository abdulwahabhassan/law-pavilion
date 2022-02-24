package com.example.lawpavilion.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class FolderLocal(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo val code: String?,
    @ColumnInfo val title: String?,
    @ColumnInfo val date: String?,
    @ColumnInfo val type: String?,
    @ColumnInfo val intro: String?,
    @ColumnInfo val topic: String?,
    @ColumnInfo val text: String?,
    @ColumnInfo val extra: String?
)
