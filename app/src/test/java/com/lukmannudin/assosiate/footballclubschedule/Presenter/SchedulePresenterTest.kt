package com.lukmannudin.assosiate.footballclubschedule.Presenter

import com.google.gson.Gson
import com.lukmannudin.assosiate.footballclubschedule.APIRequest.APIScheduleTeam
import com.lukmannudin.assosiate.footballclubschedule.APIRequest.ApiRepository
import com.lukmannudin.assosiate.footballclubschedule.Contract.ScheduleContract
import com.lukmannudin.assosiate.footballclubschedule.Model.Schedule
import com.lukmannudin.assosiate.footballclubschedule.Response.ScheduleResponse
import com.lukmannudin.assosiate.footballclubschedule.TestContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class SchedulePresenterTest {
    @Mock
    private
    lateinit var view: ScheduleContract

    @Mock
    private
    lateinit var gson:Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    @Mock
    private
    lateinit var presenter: SchedulePresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = SchedulePresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getScheduleList() {
        val schedule : MutableList<Schedule> = mutableListOf()
        val response = ScheduleResponse(schedule)
        val league = "English Premiere League"

        GlobalScope.launch {
            `when`(gson.fromJson(apiRepository
                .doRequest(APIScheduleTeam.getSchedule(league)).await(),
                ScheduleResponse::class.java
            )).thenReturn(response)

            presenter.getScheduleList(league, "eventspastleague")

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showTeamList(schedule)
            Mockito.verify(view).hideLoading()
        }
    }

    @Test
    fun getScheduleDetails() {
    }
}