package com.lukmannudin.assosiate.footballclubschedule.Contract

import com.lukmannudin.assosiate.footballclubschedule.Model.Teams

interface TeamsContract {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Teams>)
}
