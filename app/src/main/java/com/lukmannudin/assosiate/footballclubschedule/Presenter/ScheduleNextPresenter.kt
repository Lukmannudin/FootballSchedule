package com.lukmannudin.assosiate.footballclubschedule.Presenter

import com.google.gson.Gson
import com.lukmannudin.assosiate.footballclubschedule.APIRequest.APINextScheduleTeam
import com.lukmannudin.assosiate.footballclubschedule.APIRequest.ApiRepository
import com.lukmannudin.assosiate.footballclubschedule.Contract.ScheduleContract
import com.lukmannudin.assosiate.footballclubschedule.Response.ScheduleResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ScheduleNextPresenter(
    private val view: ScheduleContract,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {


    fun getScheduleList(league: String?) {
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(APINextScheduleTeam.getSchedule(league)).await(),
                ScheduleResponse::class.java
            )
            view.hideLoading()
            view.showTeamList(data.schedules)
        }
    }
}