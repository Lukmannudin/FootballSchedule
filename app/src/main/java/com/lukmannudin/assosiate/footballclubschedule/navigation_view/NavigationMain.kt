package com.lukmannudin.assosiate.footballclubschedule.navigation_view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.*
import com.google.gson.Gson
import com.lukmannudin.assosiate.footballclubschedule.APIRequest.ApiRepository
import com.lukmannudin.assosiate.footballclubschedule.Adapter.ScheduleAdapter
import com.lukmannudin.assosiate.footballclubschedule.Contract.ScheduleContract
import com.lukmannudin.assosiate.footballclubschedule.FragmentAdapter
import com.lukmannudin.assosiate.footballclubschedule.Model.Schedule
import com.lukmannudin.assosiate.footballclubschedule.Presenter.SchedulePresenter
import com.lukmannudin.assosiate.footballclubschedule.R
import com.lukmannudin.assosiate.footballclubschedule.TeamMatchListActivity
import kotlinx.android.synthetic.main.match_main_view.*
import kotlinx.android.synthetic.main.match_main_view.view.*
import kotlinx.android.synthetic.main.team_list.*
import org.jetbrains.anko.support.v4.startActivity

class NavigationMain : Fragment(), ScheduleContract {


    private lateinit var adapter: ScheduleAdapter
    private var schedules: MutableList<Schedule> = mutableListOf()
    private lateinit var presenter: SchedulePresenter
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.match_main_view, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        vpSchedule.adapter = FragmentAdapter(childFragmentManager)
        tlSchedule.setupWithViewPager(vpSchedule)

        view.rvSearchMatch.layoutManager = LinearLayoutManager(view.context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val request = ApiRepository()
        val gson = Gson()

        presenter = SchedulePresenter(this, request, gson)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        searchView.isIconified = true
    }

    private fun partItemClicked(Schedules: Schedule) {
        startActivity<TeamMatchListActivity>(
            "parcel" to Schedules
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        searchView.clearFocus()
        searchView.setQuery("", false);
        searchView.isIconified = true
    }



    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_main, menu)
        searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                vpSchedule.visibility = View.GONE
                tlSchedule.visibility = View.GONE
                presenter.getScheduleSearchList(p0)
                adapter = ScheduleAdapter(schedules, { schedules: Schedule -> partItemClicked(schedules) })
                rvSearchMatch.adapter = adapter
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if (!searchView.isIconified){
                    vpSchedule.visibility = View.GONE
                    tlSchedule.visibility = View.GONE
                }
                presenter.getScheduleSearchList(p0)
                adapter = ScheduleAdapter(schedules, { schedules: Schedule -> partItemClicked(schedules) })
                rvSearchMatch.adapter = adapter
                return true
            }
        })

        searchView.setOnCloseListener {
            searchView.onActionViewCollapsed()
            vpSchedule.visibility = View.VISIBLE
            tlSchedule.visibility = View.VISIBLE
            true
        }
    }


    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showTeamList(data: List<Schedule>) {
        schedules.clear()
        schedules.addAll(data)
        adapter.notifyDataSetChanged()
    }
}