package com.lukmannudin.assosiate.footballclubschedule.navigation_view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.util.Log
import android.view.*
import com.lukmannudin.assosiate.footballclubschedule.Adapter.TeamFragmentAdapter
import com.lukmannudin.assosiate.footballclubschedule.FragmentAdapter
import com.lukmannudin.assosiate.footballclubschedule.Model.Teams
import com.lukmannudin.assosiate.footballclubschedule.R
import com.lukmannudin.assosiate.footballclubschedule.TeamMatchView.FavoriteTeamsFragment
import com.lukmannudin.assosiate.footballclubschedule.TeamUtils
import kotlinx.android.synthetic.main.match_main_view.*
import kotlinx.android.synthetic.main.team_main.*
import kotlinx.android.synthetic.main.team_main.view.*


class NavigationTeam : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var listener: FavoriteTeamsFragment.OnFragmentInteractionListener? = null

    companion object {

        fun newInstance(dataTeam: Teams): NavigationTeam {

            val args = Bundle()
            args.putString(TeamUtils.TEAM_OVERVIEW, dataTeam.teamDescription)
//            args.putInt("value", value)
            val fragment = NavigationTeam()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
        val data = arguments?.getString(TeamUtils.TEAM_OVERVIEW)
        Log.i("TEST",data)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.team_main, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

//    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
////        super.onCreateOptionsMenu(menu, inflater)
//        menuInflater
//    }
}