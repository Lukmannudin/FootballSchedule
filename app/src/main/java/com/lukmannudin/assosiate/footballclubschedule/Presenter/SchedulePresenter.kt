package com.lukmannudin.assosiate.footballclubschedule.Presenter

import com.google.gson.Gson
import com.lukmannudin.assosiate.footballclubschedule.APIRequest.APIScheduleTeam
import com.lukmannudin.assosiate.footballclubschedule.APIRequest.ApiRepository
import com.lukmannudin.assosiate.footballclubschedule.Contract.ScheduleContract
import com.lukmannudin.assosiate.footballclubschedule.Response.ScheduleResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class SchedulePresenter(private val view: ScheduleContract,
                        private val apiRepository: ApiRepository,
                        private val gson: Gson
                    ){


    fun getScheduleList(league: String?){

        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(APIScheduleTeam.getSchedule(league)),
                ScheduleResponse::class.java
            )


            uiThread {
                view.hideLoading()
                view.showTeamList(data.schedules)

            }
        }
    }

    fun getScheduleDetails(idEvent: String?){

//        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(APIScheduleTeam.getScheduleDetails(idEvent)),
                ScheduleResponse::class.java
            )


            uiThread {
//                view.hideLoading()
                    view.showTeamList(data.schedules)

            }
        }
    }
}