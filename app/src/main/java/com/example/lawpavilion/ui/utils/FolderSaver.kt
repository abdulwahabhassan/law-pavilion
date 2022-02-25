package com.example.lawpavilion.ui.utils

import androidx.compose.runtime.saveable.mapSaver
import com.example.lawpavilion.domain.model.Folder


val FolderSaver = run {
    val titleKey = "Title"
    val dateKey = "Date"
    val codeKey = "Code"
    val typeKey = "Type"
    val introKey = "Intro"
    val topicKey = "Topic"
    val textKey = "Text"
    val extraKey = "Extra"

    mapSaver(
        save = { mapOf(
            titleKey to it.title,
            dateKey to it.date,
            codeKey to it.code,
            typeKey to it.type,
            introKey to it.intro,
            topicKey to it.topic,
            textKey to it.text,
            extraKey to it.extra
        ) },
        restore = {
            Folder(
                it[titleKey] as String?,
                it[dateKey] as String?,
                it[codeKey] as String?,
                it[typeKey] as String?,
                it[introKey] as String?,
                it[topicKey] as String?,
                it[textKey] as String?,
                it[extraKey] as String?
        ) }
    )
}