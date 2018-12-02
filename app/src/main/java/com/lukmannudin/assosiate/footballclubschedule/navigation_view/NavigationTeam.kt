package com.lukmannudin.assosiate.footballclubschedule.navigation_view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lukmannudin.assosiate.footballclubschedule.Model.Teams
import com.lukmannudin.assosiate.footballclubschedule.R
import com.lukmannudin.assosiate.footballclubschedule.TeamMatchView.FavoriteTeamsFragment
import com.lukmannudin.assosiate.footballclubschedule.TeamUtils


class NavigationTeam : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var listener: FavoriteTeamsFragment.OnFragmentInteractionListener? = null

    companion object {

        fun newInstance(dataTeam: Teams): NavigationTeam {

            val args = Bundle()
            args.putString(TeamUtils.TEAM_OVERVIEW, dataTeam.teamDescription)
            val fragment = NavigationTeam()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
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
}