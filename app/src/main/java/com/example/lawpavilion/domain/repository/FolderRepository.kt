package com.example.lawpavilion.domain.repository

import com.example.lawpavilion.data.database.entity.FolderLocal
import com.example.lawpavilion.domain.model.Folder
import com.example.lawpavilion.ui.utils.Result

interface FolderRepository {

    suspend fun retrieveFolders(): Result<List<Folder>>

    suspend fun retrieveFolder(folderCode: String): Result<Folder>

}