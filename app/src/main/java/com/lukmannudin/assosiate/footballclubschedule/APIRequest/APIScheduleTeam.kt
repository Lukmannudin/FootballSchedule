package com.lukmannudin.assosiate.footballclubschedule.APIRequest

import android.net.Uri
import android.util.Log
import com.lukmannudin.assosiate.footballclubschedule.BuildConfig
import com.lukmannudin.assosiate.footballclubschedule.R.array.league

object APIScheduleTeam {
    fun getSchedule(leagueID: String?):String {
       Log.i("LEAGUEID",leagueID)
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("eventspastleague.php")
            .appendQueryParameter("id",leagueID.toString())
            .build()
            .toString()
    }

    fun getScheduleDetails(idEvent: String?):String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("lookupevent.php")
            .appendQueryParameter("id",idEvent)
            .build()
            .toString()
    }
}