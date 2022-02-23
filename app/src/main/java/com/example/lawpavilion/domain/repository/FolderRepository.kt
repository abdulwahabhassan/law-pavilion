package com.example.lawpavilion.domain.repository

import com.example.lawpavilion.data.database.entity.FolderLocal
import com.example.lawpavilion.data.database.entity.JudgementLocal
import com.example.lawpavilion.domain.model.Folder

interface FolderRepository {

    suspend fun retrieveFolders(): com.example.lawpavilion.ui.utils.Result<List<Folder>>

    suspend fun saveFolder(folderLocal: FolderLocal): com.example.lawpavilion.ui.utils.Result<Long>

    suspend fun retrieveFolder(folderCode: String): com.example.lawpavilion.ui.utils.Result<Folder>

}