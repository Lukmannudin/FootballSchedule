package com.lukmannudin.assosiate.footballclubschedule.APIRequest

import android.net.Uri
import android.util.Log
import com.lukmannudin.assosiate.footballclubschedule.BuildConfig
import com.lukmannudin.assosiate.footballclubschedule.R.array.league

object APIScheduleTeam {
    private lateinit var paramenterType:String
    fun getSchedule(leagueID: String?, fileName:String?):String {
       if (fileName.equals("eventspastleague")){
           paramenterType = "id"
       } else {
           paramenterType = "e"
       }
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath(fileName+".php")
            .appendQueryParameter(paramenterType,leagueID.toString())
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

    fun getScheduleSearchDetails(searchName: String?):String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("searchevents.php")
            .appendQueryParameter("e",searchName)
            .build()
            .toString()
    }
}