package com.lukmannudin.assosiate.footballclubschedule.Presenter

import com.google.gson.Gson
import com.lukmannudin.assosiate.footballclubschedule.APIRequest.ApiRepository
import com.lukmannudin.assosiate.footballclubschedule.Contract.ScheduleContract
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class SchedulePresenterTest {
    @Mock
    private
    lateinit var view: ScheduleContract

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: TeamsPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = SchedulePresenter(view,apiRepository,gson)
    }

    @Test
    fun getScheduleList() {
    }

    @Test
    fun getScheduleDetails() {

    }
}