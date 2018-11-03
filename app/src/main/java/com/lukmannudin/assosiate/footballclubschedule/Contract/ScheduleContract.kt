package com.lukmannudin.assosiate.footballclubschedule.Contract

import com.lukmannudin.assosiate.footballclubschedule.Model.Schedule

interface ScheduleContract {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Schedule>)
}