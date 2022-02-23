package com.example.lawpavilion.data.repository

import android.net.ConnectivityManager
import com.example.lawpavilion.ui.utils.Result
import com.example.lawpavilion.data.database.dao.FolderLocalDao
import com.example.lawpavilion.data.database.entity.FolderLocal
import com.example.lawpavilion.data.mapper.FolderMapper
import com.example.lawpavilion.domain.model.Folder
import com.example.lawpavilion.domain.repository.FolderRepository
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

class FolderRepositoryImpl @Inject constructor(
    private val folderLocalDao: FolderLocalDao,
    private val folderMapper: FolderMapper
): FolderRepository {
    override suspend fun retrieveFolders(): Result<List<Folder>> {
        return try {
            Result.Success(
                folderMapper.mapToEntitiesList(
                    folderLocalDao.getAllFolders()
                )
            )
        } catch(e: Exception) {
            Timber.d(e.localizedMessage)
            Result.Error(e)
        }

    }

    override suspend fun saveFolder(folderLocal: FolderLocal): Result<Long> {
        return try {
            Result.Success(
                folderLocalDao.insertFolder(folderLocal)
            )
        } catch(e: Exception) {
            Timber.d(e.localizedMessage)
            Result.Error(e)
        }
    }

    override suspend fun retrieveFolder(folderCode: String): Result<Folder> {
        return try {
            Result.Success(
                folderMapper.mapToEntity(
                    folderLocalDao.getFolder(folderCode)
                )
            )

        } catch (e: Exception) {
            Timber.d(e.localizedMessage)
            Result.Error(e)
        }
    }
}