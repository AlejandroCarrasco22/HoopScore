package com.uva.hoopscore.Modelo

import com.google.gson.annotations.SerializedName

data class StatisticsXX(
    @SerializedName("assists")
    val assists: Int,
    @SerializedName("ball_possession")
    val ball_possession: Int,
    @SerializedName("biggest_lead")
    val biggest_lead: Int,
    @SerializedName("defensive_rebounds")
    val defensive_rebounds: Int,
    @SerializedName("fouls")
    val fouls: Int,
    @SerializedName("free_throw_attempts_successful")
    val free_throw_attempts_successful: Int,
    @SerializedName("free_throw_attempts_total")
    val free_throw_attempts_total: Int,
    @SerializedName("leader_assists")
    val leader_assists: Int,
    @SerializedName("leader_assists_player")
    val leader_assists_player: String,
    @SerializedName("leader_points")
    val leader_points: Int,
    @SerializedName("leader_points_player")
    val leader_points_player: String,
    @SerializedName("leader_rebounds")
    val leader_rebounds: Int,
    @SerializedName("leader_rebounds_player")
    val leader_rebounds_player: String,
    @SerializedName("offensive_rebounds")
    val offensive_rebounds: Int,
    @SerializedName("rebounds")
    val rebounds: Int,
    @SerializedName("shots_blocked")
    val shots_blocked: Int,
    @SerializedName("steals")
    val steals: Int,
    @SerializedName("team_leads")
    val team_leads: Int,
    @SerializedName("team_rebounds")
    val team_rebounds: Int,
    @SerializedName("team_turnovers")
    val team_turnovers: Int,
    @SerializedName("three_point_attempts_successful")
    val three_point_attempts_successful: Int,
    @SerializedName("three_point_attempts_total")
    val three_point_attempts_total: Int,
    @SerializedName("time_spent_in_lead")
    val time_spent_in_lead: Int,
    @SerializedName("timeouts")
    val timeouts: Int,
    @SerializedName("turnovers")
    val turnovers: Int,
    @SerializedName("two_point_attempts_successful")
    val two_point_attempts_successful: Int,
    @SerializedName("two_point_attempts_total")
    val two_point_attempts_total: Int
)