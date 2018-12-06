package com.lukmannudin.assosiate.footballclubschedule.Model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Teams(

    @SerializedName("idTeam")
    var teamId: String? = null,

    @SerializedName("strTeam")
    var teamName: String? = null,

    @SerializedName("strTeamBadge")
    var teamBadge: String? = null,

    @SerializedName("intFormedYear")
    var teamFormedYear: String? = null,

    @SerializedName("strStadium")
    var teamStadium: String? = null,

    @SerializedName("strDescriptionEN")
    var teamDescription: String? = null,

    @SerializedName("idLeague")
    var idLeague: String? = null,

    @SerializedName("strManager")
    var strManager: String? = null
) : Parcelable