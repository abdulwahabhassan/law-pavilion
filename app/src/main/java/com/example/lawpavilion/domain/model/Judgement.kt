package com.example.lawpavilion.domain.model

data class Judgement(
    val code: String,
    val title: String? = null,
    val type: String? = null,
    val intro: String? = null,
    val text: String? = null,
    val extra: String? = null
)
