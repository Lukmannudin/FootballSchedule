package com.lukmannudin.assosiate.footballclubschedule.Response

import com.google.gson.annotations.SerializedName
import com.lukmannudin.assosiate.footballclubschedule.Model.Schedule

data class ScheduleResponse(
    @SerializedName("events")
    val schedules: List<Schedule>
)