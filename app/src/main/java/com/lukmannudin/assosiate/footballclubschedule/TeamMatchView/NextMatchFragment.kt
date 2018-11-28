package com.lukmannudin.assosiate.footballclubschedule.TeamMatchView

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.lukmannudin.assosiate.footballclubschedule.APIRequest.ApiRepository
import com.lukmannudin.assosiate.footballclubschedule.Adapter.ScheduleAdapter
import com.lukmannudin.assosiate.footballclubschedule.Contract.ScheduleContract
import com.lukmannudin.assosiate.footballclubschedule.Model.Schedule
import com.lukmannudin.assosiate.footballclubschedule.Model.Team
import com.lukmannudin.assosiate.footballclubschedule.Presenter.ScheduleNextPresenter
import com.lukmannudin.assosiate.footballclubschedule.R
import com.lukmannudin.assosiate.footballclubschedule.TeamMatchNextMatchActivity
import com.lukmannudin.assosiate.footballclubschedule.invisible
import com.lukmannudin.assosiate.footballclubschedule.visible
import kotlinx.android.synthetic.main.fragment_second.view.*
import org.jetbrains.anko.support.v4.startActivity


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SecondFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class NextMatchFragment : Fragment(), ScheduleContract {
    override fun showLoading() {
        view?.indeterminateBar2?.visible()
        view?.swiperefresh2?.isRefreshing = false    }

    override fun hideLoading() {
        view?.indeterminateBar2?.invisible()
    }

    override fun showTeamList(data: List<Schedule>) {
        schedules.clear()
        schedules.addAll(data)
        adapter.notifyDataSetChanged()
    }

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    private lateinit var adapter: ScheduleAdapter
    private var schedules: MutableList<Schedule> = mutableListOf()

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
        val view = inflater.inflate(R.layout.fragment_second, container, false)
        lateinit var presenter: ScheduleNextPresenter
        val request = ApiRepository()
        val gson = Gson()

        presenter = ScheduleNextPresenter(this, request, gson)
        presenter.getScheduleList("")
        view.swiperefresh2.isRefreshing = false
        view.swiperefresh2.setOnRefreshListener {
            presenter.getScheduleList("")
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.club_list2.layoutManager = LinearLayoutManager(view.context)
        adapter = ScheduleAdapter(schedules, { schedules: Schedule -> partItemClicked(schedules) })
        view.club_list2.adapter = adapter

    }

    private fun partItemClicked(Schedules: Schedule) {
        startActivity<TeamMatchNextMatchActivity>(
            Team.TEAM_MATCH_EVENT_ID to Schedules.idEvent,
            Team.TEAM_HOME_ID to Schedules.idHomeTeam,
            Team.TEAM_AWAY_ID to Schedules.idAwayTeam,
            Team.TEAM_MATCH_EVENT_DATE to Schedules.dateEvent,
            Team.TEAM_HOME_NAME to Schedules.strHomeTeam,
            Team.TEAM_AWAY_NAME to Schedules.strAwayTeam
        )
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        if (context is OnFragmentInteractionListener) {
//            listener = context
//        } else {
//            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
//        }
//    }



    override fun onDetach() {
        super.onDetach()
        listener = null
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SecondFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NextMatchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
