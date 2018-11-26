package com.lukmannudin.assosiate.footballclubschedule.Fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lukmannudin.assosiate.footballclub.database.database
import com.lukmannudin.assosiate.footballclubschedule.Adapter.FavoritesAdapter
import com.lukmannudin.assosiate.footballclubschedule.Favorite
import com.lukmannudin.assosiate.footballclubschedule.FavoritesDetailActivity
import com.lukmannudin.assosiate.footballclubschedule.Model.Schedule
import com.lukmannudin.assosiate.footballclubschedule.R.color.colorAccent
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class FavoriteTeamsFragment : Fragment(), AnkoComponent<Context> {


    private var schedules: MutableList<Schedule> = mutableListOf()


    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

//    private lateinit var adapter: ScheduleAdapter
//    private var schedules: MutableList<Schedule> = mutableListOf()

    private var favorites: MutableList<Favorite> = mutableListOf()
    private lateinit var adapter: FavoritesAdapter
    private lateinit var listTeam: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = FavoritesAdapter(favorites, { favorites: Favorite -> partItemClicked(favorites) })
        listTeam.adapter = adapter
        swipeRefresh.onRefresh {
            showFavorite()
        }
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }

    private fun partItemClicked(Favorites: Favorite) {
        startActivity(
            intentFor<FavoritesDetailActivity>(
                "teamMatchEventId" to Favorites.teamMatchEventId,
                "teamHomeId" to Favorites.teamHomeId,
                "teamAwayId" to Favorites.teamAwayId,
                "eventMatchDate" to Favorites.teamMatchEventDate
            )
        )
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(requireContext()))
    }

    private fun showFavorite() {
        favorites.clear()
        swipeRefresh.isRefreshing = false
        context?.database?.use {
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
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
         * @return A new instance of fragment ThirdFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FavoriteTeamsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(
                    colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light
                )

                listTeam = recyclerView {
                    lparams(width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(ctx)
                }
            }
        }
    }

}
