package com.lukmannudin.assosiate.footballclubschedule.TeamMatchView

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.google.gson.Gson
import com.lukmannudin.assosiate.footballclubschedule.*
import com.lukmannudin.assosiate.footballclubschedule.APIRequest.ApiRepository
import com.lukmannudin.assosiate.footballclubschedule.Adapter.ScheduleAdapter
import com.lukmannudin.assosiate.footballclubschedule.Contract.ScheduleContract
import com.lukmannudin.assosiate.footballclubschedule.Model.Schedule
import com.lukmannudin.assosiate.footballclubschedule.Presenter.ScheduleNextPresenter
import kotlinx.android.synthetic.main.match_fragment.*
import kotlinx.android.synthetic.main.match_fragment.view.*
import org.jetbrains.anko.support.v4.startActivity


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class NextMatchFragment : Fragment(), ScheduleContract {


    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    private lateinit var adapter: ScheduleAdapter
    private var schedules: MutableList<Schedule> = mutableListOf()
    private lateinit var presenter: ScheduleNextPresenter
    private lateinit var leagueName: String
    private lateinit var leagueID: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.match_fragment, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        sLastMatch.adapter = spinnerAdapter


        val request = ApiRepository()
        val gson = Gson()
        presenter = ScheduleNextPresenter(this, request, gson)
        sLastMatch.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = sLastMatch.selectedItem.toString()
                when (leagueName) {
                    Utils.ENGLISH_PREMIER_LEAGUE_NAME -> {
                        leagueID = Utils.ENGLISH_PREMIER_LEAGUE_ID
                    }
                    Utils.ENGLISH_LEAGUE_CHAMPIONSHIP_NAME -> {
                        leagueID = Utils.ENGLISH_LEAGUE_CHAMPIONSHIP_ID
                    }
                    Utils.FRENCH_LIGUE_1_NAME -> {
                        leagueID = Utils.FRENCH_LIGUE_1_ID
                    }
                    Utils.GERMAN_BUNDESLIGA_NAME -> {
                        leagueID = Utils.GERMAN_BUNDESLIGA_ID
                    }
                    Utils.ITALIAN_SERIE_A_NAME -> {
                        leagueID = Utils.ITALIAN_SERIE_A_ID
                    }
                    Utils.SPANISH_LA_LIGA_NAME -> {
                        leagueID = Utils.SPANISH_LA_LIGA_ID
                    }

                }
                presenter.getScheduleList(leagueID)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }


        swiperefresh.isRefreshing = false
        swiperefresh.setOnRefreshListener {
            presenter.getScheduleList(leagueID)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.club_list.layoutManager = LinearLayoutManager(view.context)
        adapter = ScheduleAdapter(schedules, { schedules: Schedule -> partItemClicked(schedules) })
        view.club_list.adapter = adapter

    }

    private fun partItemClicked(Schedules: Schedule) {
        startActivity<TeamMatchNextMatchActivity>(
            TeamUtils.TEAM_MATCH_EVENT_ID to Schedules.idEvent,
            TeamUtils.TEAM_HOME_ID to Schedules.idHomeTeam,
            TeamUtils.TEAM_AWAY_ID to Schedules.idAwayTeam,
            TeamUtils.TEAM_MATCH_EVENT_DATE to Schedules.dateEvent,
            TeamUtils.TEAM_HOME_NAME to Schedules.strHomeTeam,
            TeamUtils.TEAM_AWAY_NAME to Schedules.strAwayTeam
        )
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NextMatchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun showLoading() {
        indeterminateBar?.visible()
        swiperefresh?.isRefreshing = false
    }

    override fun hideLoading() {
        indeterminateBar?.invisible()
    }

    override fun showTeamList(data: List<Schedule>) {
        schedules.clear()
        schedules.addAll(data)
        adapter.notifyDataSetChanged()
    }
}
