package com.lukmannudin.assosiate.footballclubschedule.APIRequest

import android.net.Uri
import com.lukmannudin.assosiate.footballclubschedule.BuildConfig

object APINextScheduleTeam {

    fun getSchedule(league: String?): String {
    //        println(league)
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("eventsnextleague.php")
            .appendQueryParameter("id", "4328")
            .build()
            .toString()
    }
}