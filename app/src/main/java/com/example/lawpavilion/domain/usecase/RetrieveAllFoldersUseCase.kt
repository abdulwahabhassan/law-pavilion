package com.example.lawpavilion.domain.usecase

import com.example.lawpavilion.domain.repository.FolderRepository

class RetrieveAllFoldersUseCase (private val folderRepository: FolderRepository) {
    suspend operator fun invoke() = folderRepository.retrieveFolders()

}