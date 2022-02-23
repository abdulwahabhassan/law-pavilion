package com.example.lawpavilion.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FolderLocal(
    @PrimaryKey val code: String = "(2019) LPELR - 42466 (SC)",
    @ColumnInfo val title: String = "Mailantarki v. Tongo",
    @ColumnInfo val date: String = "Delivered on 20-10-2019",
    @ColumnInfo(name = "judgement_local") val judgementLocal: JudgementLocal
)
