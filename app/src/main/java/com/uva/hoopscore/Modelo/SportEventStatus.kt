package com.uva.hoopscore.Modelo

import com.google.gson.annotations.SerializedName

data class SportEventStatus(
    @SerializedName("away_score")
    val away_score: Int,
    @SerializedName("home_score")
    val home_score: Int,
    @SerializedName("match_status")
    val match_status: String,
    @SerializedName("period_scores")
    val period_scores: List<PeriodScore>,
    @SerializedName("status")
    val status: String,
    @SerializedName("winner_id")
    val winner_id: String,
)