package com.example.lawpavilion.ui.utils

import com.example.lawpavilion.data.database.entity.FolderLocal

object Database {
    fun getListOfCaseFolders(): List<FolderLocal> {
        return listOf(
            FolderLocal(),
            FolderLocal(),
            FolderLocal(),
            FolderLocal(),
            FolderLocal(),
            FolderLocal(),
            FolderLocal(),
            FolderLocal(),
            FolderLocal(),
            FolderLocal(),
            FolderLocal(),
            FolderLocal(),
            FolderLocal(),
            FolderLocal(),
            FolderLocal(),
            FolderLocal()
        )
    }
}