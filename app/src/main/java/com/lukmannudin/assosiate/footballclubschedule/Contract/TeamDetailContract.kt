package com.lukmannudin.assosiate.footballclubschedule.Contract

import com.lukmannudin.assosiate.footballclubschedule.Model.TeamDetail

interface TeamDetailContract {
    fun showLoading()
    fun hideLoading()
    fun showHomeTeamDetailList(data: List<TeamDetail>)
    fun showAwayTeamDetailList(data: List<TeamDetail>)

}