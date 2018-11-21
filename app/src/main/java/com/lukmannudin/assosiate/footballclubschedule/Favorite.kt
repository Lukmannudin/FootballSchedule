package com.lukmannudin.assosiate.footballclubschedule

data class Favorite(val id: Long?, val teamMatchEventId:String, val teamHomeId: String?,val teamAwayId: String?) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val TEAM_HOME_ID: String = "TEAM_HOME_ID"
        const val TEAM_AWAY_ID: String = "TEAM_AWAY_ID"
        const val TEAM_MATCH_EVENT_ID: String = "TEAM_MATCH_EVENT_ID"
//        const val TEAM_MATCH_EVENT: String = "TEAM_MATCH_EVENT"
//        const val TEAM_HOME_NAME: String = "TEAM_HOME_NAME"
//        const val TEAM_AWAY_NAME: String = "TEAM_AWAY_NAME"
//        const val TEAM_HOME_SCORE: String = "TEAM_"
    }
}