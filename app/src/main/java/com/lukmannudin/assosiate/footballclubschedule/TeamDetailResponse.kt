package com.lukmannudin.assosiate.footballclubschedule

import com.google.gson.annotations.SerializedName
import com.lukmannudin.assosiate.footballclubschedule.Model.TeamDetail

data class TeamDetailResponse(
    @SerializedName("teams")
    val teamDetail: List<TeamDetail>
)