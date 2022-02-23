package com.example.lawpavilion.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.lawpavilion.data.database.dao.FolderLocalDao
import com.example.lawpavilion.data.database.entity.FolderLocal
import com.example.lawpavilion.data.database.entity.JudgementLocal

@Database(entities = [FolderLocal::class, JudgementLocal::class], version = 1)
@TypeConverters(Converter::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun folderLocalDao(): FolderLocalDao
}