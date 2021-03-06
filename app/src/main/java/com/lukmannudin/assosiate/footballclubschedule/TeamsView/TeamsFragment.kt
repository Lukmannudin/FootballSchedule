package com.lukmannudin.assosiate.footballclubschedule.TeamsView

import android.R
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.google.gson.Gson
import com.lukmannudin.assosiate.footballclubschedule.APIRequest.ApiRepository
import com.lukmannudin.assosiate.footballclubschedule.Adapter.TeamsAdapter
import com.lukmannudin.assosiate.footballclubschedule.Contract.TeamsContract
import com.lukmannudin.assosiate.footballclubschedule.Model.Teams
import com.lukmannudin.assosiate.footballclubschedule.Presenter.TeamsPresenter
import com.lukmannudin.assosiate.footballclubschedule.R.array.league
import com.lukmannudin.assosiate.footballclubschedule.TeamDetailActivity
import com.lukmannudin.assosiate.footballclubschedule.TeamUtils
import com.lukmannudin.assosiate.footballclubschedule.invisible
import com.lukmannudin.assosiate.footballclubschedule.visible
import kotlinx.android.synthetic.main.team_list.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.onRefresh


class TeamsFragment : Fragment(), TeamsContract {


    private var teams: MutableList<Teams> = mutableListOf()
    private lateinit var presenter: TeamsPresenter
    private lateinit var adapter: TeamsAdapter
    private lateinit var spinner: Spinner
    private lateinit var searchView: SearchView
    private var leagueName: String = "English Premier League"


    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);

        val view = inflater.inflate(com.lukmannudin.assosiate.footballclubschedule.R.layout.team_list, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val spinnerItems = resources.getStringArray(league)
        val spinnerAdapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_dropdown_item, spinnerItems)
        team_spinner.adapter = spinnerAdapter
        team_spinner.visibility = View.VISIBLE
        adapter = TeamsAdapter(teams) {
            context?.startActivity<TeamDetailActivity>(
                TeamUtils.TEAM_INTENT to it
            )
        }
        rvTeam.layoutManager = LinearLayoutManager(activity)
        rvTeam.adapter = adapter
        val request = ApiRepository()
        val gson = Gson()

        presenter = TeamsPresenter(this, request, gson)
        presenter.getSearchTeamList("English Premier League")


        team_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = team_spinner.selectedItem.toString()
                presenter.getTeamList(leagueName)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }


    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }


    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        searchView.clearFocus()
        searchView.setQuery("", false);
        searchView.isIconified = true
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(com.lukmannudin.assosiate.footballclubschedule.R.menu.menu_main, menu)
        Log.i("ce",leagueName)
        searchView =
            menu?.findItem(com.lukmannudin.assosiate.footballclubschedule.R.id.action_search)?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                presenter.getSearchTeamList(query)
                team_spinner.visibility = View.GONE
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (!searchView.isIconified){
                    team_spinner.visibility = View.GONE
                }
                presenter.getSearchTeamList(newText)
                return true
            }
        })



        searchView.setOnCloseListener {
            searchView.onActionViewCollapsed()
            team_spinner.visibility = View.VISIBLE
            //                presenter.getTeamList(leagueName)
            if (searchView.isIconified) {
                presenter.getTeamList(leagueName)
            } else {
            }
            true
        }
    }


    override fun onResume() {
        super.onResume()
        team_spinner.visibility = View.VISIBLE
        team_swipeRefresh.onRefresh {
            presenter.getTeamList(leagueName)
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }


    override fun showLoading() {
        pbTeam?.visible()
    }

    override fun hideLoading() {
        pbTeam?.invisible()
    }

    override fun showTeamList(data: List<Teams>) {
        team_swipeRefresh?.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
        Log.i("league:", data[0].idLeague)
    }

}