package com.example.lawpavilion.data.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.lawpavilion.data.database.entity.JudgementLocal
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import timber.log.Timber
import javax.inject.Inject

@ProvidedTypeConverter
class Converter @Inject constructor(
    private val jsonAdapter: JsonAdapter<JudgementLocal>
) {
    @TypeConverter
    fun toJudgement(judgement: String): JudgementLocal? {
        Timber.d("${jsonAdapter.fromJson(judgement)}")
        return jsonAdapter.fromJson(judgement)
    }

    //convert seriesRates(Map<String, Map<String, Double>>) to String
    @TypeConverter
    fun fromJudgement(judgement: JudgementLocal): String {
        Timber.d(jsonAdapter.toJson(judgement))
        return jsonAdapter.toJson(judgement)
    }
}