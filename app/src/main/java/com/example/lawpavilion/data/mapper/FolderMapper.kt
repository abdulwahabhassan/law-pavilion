package com.example.lawpavilion.data.mapper

import com.example.lawpavilion.data.database.entity.FolderLocal
import com.example.lawpavilion.domain.BaseMapper
import com.example.lawpavilion.domain.model.Folder

class FolderMapper : BaseMapper<FolderLocal, Folder> {
    override fun mapToEntity(type: FolderLocal): Folder {
        return Folder(
            title = type.title,
            date = type.date,
            code = type.code,
            type = type.type,
            intro = type.intro,
            text = type.text,
            extra = type.extra,
            topic = type.topic
        )
    }

    override fun mapToDTO(type: Folder): FolderLocal {
        return FolderLocal(
            title = type.title,
            date = type.date,
            code = type.code,
            type = type.type,
            intro = type.intro,
            text = type.text,
            extra = type.extra,
            topic = type.topic
        )
    }
}