package com.example.lawpavilion.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lawpavilion.data.database.entity.FolderLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface FolderLocalDao {

    //get a particular folder in database
    @Query("SELECT * FROM folderlocal WHERE code Like (:folderCode)")
    suspend fun getFolder(folderCode: String): FolderLocal

    //get all folders in database
    @Query("SELECT * FROM folderlocal")
    suspend fun getAllFolders(): List<FolderLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllFolders(plants: List<FolderLocal>)
}