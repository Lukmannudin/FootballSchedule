package com.lukmannudin.assosiate.footballclubschedule.TeamsView

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.lukmannudin.assosiate.footballclubschedule.*
import com.lukmannudin.assosiate.footballclubschedule.APIRequest.ApiRepository
import com.lukmannudin.assosiate.footballclubschedule.Adapter.PlayerAdapter
import com.lukmannudin.assosiate.footballclubschedule.Contract.PlayerContract
import com.lukmannudin.assosiate.footballclubschedule.Model.Players
import com.lukmannudin.assosiate.footballclubschedule.Model.Teams
import com.lukmannudin.assosiate.footballclubschedule.Presenter.PlayerPresenter
import kotlinx.android.synthetic.main.activity_team_player.*
import org.jetbrains.anko.support.v4.startActivity


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [BlankFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [BlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class PlayerListFragment : Fragment() ,PlayerContract{
    private var players: MutableList<Players> = mutableListOf()
    private lateinit var presenter: PlayerPresenter
    private lateinit var adapter: PlayerAdapter
    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showTeamList(data: List<Players>) {
//        team_swipeRefresh.isRefreshing = false
        players.clear()
        players.addAll(data)
        adapter.notifyDataSetChanged()
    }

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        Log.i("CEK",param1+" "+param2)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_team_player, container, false)
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val request = ApiRepository()
        val gson = Gson()
        presenter = PlayerPresenter(this, request, gson)
        presenter.getPlayers(param1)
        adapter = PlayerAdapter(players, {players:Players -> partItemClicked(players) })

        rvTeamPlayer.layoutManager = LinearLayoutManager(view?.context)
        rvTeamPlayer.adapter = adapter
    }

    private fun partItemClicked(players: Players) {
//        Log.i("CEK",players.strDescriptionEN)
         startActivity<PlayerDetailActivity>(
//             TeamUtils.TEAM_INTENT to players
            PlayerUtils.PLAYER_NAME to players.strPlayer,
             PlayerUtils.PLAYER_BADGE to players.strThumb,
             PlayerUtils.PLAYER_WEIGHT to players.strWeight,
             PlayerUtils.PLAYER_HEIGHT to players.strHeight,
             PlayerUtils.PLAYER_DESCRIPTION to players.strDescriptionEN

         )
    }
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
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(teams: Teams?) =
            PlayerListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, teams?.teamName)
                }
            }
    }
}
