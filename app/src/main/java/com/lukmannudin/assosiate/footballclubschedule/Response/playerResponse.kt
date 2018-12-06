package com.lukmannudin.assosiate.footballclubschedule.Response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lukmannudin.assosiate.footballclubschedule.Model.Players

data class playerResponse(
    @SerializedName("player")
    val player: List<Players>

)