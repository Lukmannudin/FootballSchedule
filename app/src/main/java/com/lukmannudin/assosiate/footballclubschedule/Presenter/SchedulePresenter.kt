package com.lukmannudin.assosiate.footballclubschedule.Presenter

import android.util.Log
import com.google.gson.Gson
import com.lukmannudin.assosiate.footballclubschedule.APIRequest.APIScheduleTeam
import com.lukmannudin.assosiate.footballclubschedule.APIRequest.ApiRepository
import com.lukmannudin.assosiate.footballclubschedule.Contract.ScheduleContract
import com.lukmannudin.assosiate.footballclubschedule.CoroutineContextProvider
import com.lukmannudin.assosiate.footballclubschedule.Response.ScheduleResponse
import com.lukmannudin.assosiate.footballclubschedule.Response.ScheduleSearchResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import java.lang.Exception

class SchedulePresenter(
    private val view: ScheduleContract,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {


    fun getScheduleList(leagueID: String?, nameFile: String?) {
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            Log.i("CEK", leagueID)
            val data = gson.fromJson(
                apiRepository
                    .doRequest(APIScheduleTeam.getSchedule(leagueID, nameFile)).await(),
                ScheduleResponse::class.java

            )
            view.hideLoading()
            view.showTeamList(data.schedules)
        }
    }

    fun getScheduleDetails(idEvent: String?) {
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(APIScheduleTeam.getScheduleDetails(idEvent)).await(),
                ScheduleResponse::class.java
            )
            view.showTeamList(data.schedules)
        }
    }

    fun getScheduleSearchList(searchName: String?) {
       GlobalScope.launch(Dispatchers.Main) {
          view.showLoading()
            val data = gson.fromJson(
                apiRepository
                    .doRequest(APIScheduleTeam.getScheduleSearchDetails(searchName)).await(),
                ScheduleSearchResponse::class.java
            )

        view.hideLoading()
        try {
                 view.showTeamList(data.schedules)
        } catch (e:Exception){
            e.localizedMessage
        }
       }
    }
}
