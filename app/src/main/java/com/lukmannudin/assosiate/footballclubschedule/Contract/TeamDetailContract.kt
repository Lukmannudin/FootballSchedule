package com.lukmannudin.assosiate.footballclubschedule.Contract

import com.lukmannudin.assosiate.footballclubschedule.Model.TeamDetail

interface TeamDetailContract {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetailList(data: List<TeamDetail>)
}