package com.example.lawpavilion.data.mapper

import com.example.lawpavilion.data.database.entity.JudgementLocal
import com.example.lawpavilion.domain.BaseMapper
import com.example.lawpavilion.domain.model.Judgement

class JudgementMapper : BaseMapper<JudgementLocal, Judgement> {
    override fun mapToEntity(type: JudgementLocal): Judgement {
        return Judgement(
            code = type.code,
            title = type.title,
            type = type.type,
            intro = type.intro,
            text = type.text,
            extra = type.extra
        )
    }

    override fun mapToDTO(type: Judgement): JudgementLocal {
        return JudgementLocal(
            code = type.code,
            title = type.title,
            type = type.type,
            intro = type.intro,
            text = type.text,
            extra = type.extra
        )
    }
}