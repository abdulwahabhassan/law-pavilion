package com.example.lawpavilion.domain.model

import android.os.Parcelable


data class Folder(
    val title: String? = null,
    val date: String? = null,
    val code: String? = null,
    val type: String? = null,
    val intro: String? = null,
    val topic: String? = null,
    val text: String? = null,
    val extra: String? = null
)
