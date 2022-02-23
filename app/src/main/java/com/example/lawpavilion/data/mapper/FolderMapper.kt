package com.example.lawpavilion.data.mapper

import com.example.lawpavilion.data.database.entity.FolderLocal
import com.example.lawpavilion.domain.BaseMapper
import com.example.lawpavilion.domain.model.Folder
import com.example.lawpavilion.domain.model.Judgement

class FolderMapper (
    private val judgementMapper: JudgementMapper
        ) : BaseMapper<FolderLocal, Folder> {
    override fun mapToEntity(type: FolderLocal): Folder {
        return Folder(
            title = type.title,
            date = type.date,
            code = type.code,
            judgement = judgementMapper.mapToEntity(type.judgementLocal)
        )
    }

    override fun mapToDTO(type: Folder): FolderLocal {
        return FolderLocal(
            title = type.title,
            date = type.date,
            code = type.code,
            judgementLocal = judgementMapper.mapToDTO(type.judgement)
        )
    }
}