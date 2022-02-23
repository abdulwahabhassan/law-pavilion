package com.example.lawpavilion.domain.model

data class Folder(
    val title: String = "Mailantarki v. Tongo",
    val date: String = "Delivered on 20-10-2019",
    val code: String = "(2019) LPELR - 42466 (SC)",
    val judgement: Judgement
)
