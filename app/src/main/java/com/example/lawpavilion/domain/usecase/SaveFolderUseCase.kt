package com.example.lawpavilion.domain.usecase

import com.example.lawpavilion.data.database.entity.FolderLocal
import com.example.lawpavilion.domain.repository.FolderRepository

class SaveFolderUseCase (private val folderRepository: FolderRepository) {
    suspend operator fun invoke(
        folderLocal: FolderLocal
    ) = folderRepository.saveFolder(folderLocal)

}