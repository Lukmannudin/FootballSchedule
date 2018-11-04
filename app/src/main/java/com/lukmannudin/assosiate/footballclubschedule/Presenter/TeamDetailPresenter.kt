package com.lukmannudin.assosiate.footballclubschedule.Presenter

import com.google.gson.Gson
import com.lukmannudin.assosiate.footballclubschedule.APIRequest.APITeamDetails
import com.lukmannudin.assosiate.footballclubschedule.APIRequest.ApiRepository
import com.lukmannudin.assosiate.footballclubschedule.Contract.TeamDetailContract
import com.lukmannudin.assosiate.footballclubschedule.TeamDetailResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TeamDetailPresenter(
    private val view: TeamDetailContract,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {
    fun getHomeTeamDetailList(idTeam: String?) {

//        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(APITeamDetails.getTeamDetail(idTeam)),
                TeamDetailResponse::class.java
            )

            uiThread {
                //                view.hideLoading()
                view.showHomeTeamDetailList(data.teamDetail)
            }
        }
    }

        fun getAwayTeamDetailList(idTeam: String?) {

//        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(APITeamDetails.getTeamDetail(idTeam)),
                TeamDetailResponse::class.java
            )

            uiThread {
                //                view.hideLoading()
                view.showAwayTeamDetailList(data.teamDetail)
            }
        }
    }
}