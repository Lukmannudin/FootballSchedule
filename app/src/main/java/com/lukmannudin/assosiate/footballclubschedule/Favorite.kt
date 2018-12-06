package com.lukmannudin.assosiate.footballclubschedule

data class Favorite(
//    val id: Long?,
    val teamMatchEventId:String,
    val teamMatchEventDate: String?,
    val teamHomeId: String?,
    val teamAwayId: String?,
    val teamHomeName: String?,
    val teamAwayName: String?,
    val teamHomeScore: String?,
    val teamAwayScore: String?,
    val strEvent: String?
//    val teamHomeBadge: String?,
//    val teamAwayBadge: String?,
//    val teamHomeGoalKeeper: String?,
//    val teamAwayGoalKeeper: String?,
//    val teamHomeDefense: String?,
//    val teamAwayDefense: String?,
//    val teamHomeMidfield: String?,
//    val teamAwayMidfield: String?,
//    val teamHomeForward: String?,
//    val teamAwayForward: String?,
//    val teamHomeSubstitutes: String?,
//    val teamAwaySubstitutes: String
    ) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val TEAM_HOME_ID: String = "TEAM_HOME_ID"
        const val TEAM_AWAY_ID: String = "TEAM_AWAY_ID"
        const val TEAM_MATCH_EVENT_ID: String = "TEAM_MATCH_EVENT_ID"
        const val TEAM_MATCH_EVENT_DATE: String = "TEAM_MATCH_EVENT_DATE"
        const val TEAM_HOME_NAME: String = "TEAM_HOME_NAME"
        const val TEAM_AWAY_NAME: String = "TEAM_AWAY_NAME"
        const val TEAM_HOME_SCORE: String = "TEAM_HOME_SCORE"
        const val TEAM_AWAY_SCORE: String = "TEAM_AWAY_SCORE"

        const val TABLE_TEAM_FAVORITE: String = "TABLE_TEAM_FAVORITE"
        const val TEAM_ID = "TEAM_ID"
        const val TEAM_NAME = "TEAM_NAME"
        const val TEAM_BADGE = "TEAM_BADGE"
        const val STR_EVENT = "STR_EVENT"
//        const val TEAM_HOME_BADGE: String = "TEAM_HOME_BADGE"
//        const val TEAM_AWAY_BADGE: String = "TEAM_AWAY_BADGE"
//        const val TEAM_HOME_GOALKEEPER: String = "TEAM_HOME_GOALKEEPER"
//        const val TEAM_AWAY_GOALKEEPER: String = "TEAM_AWAY_GOALKEEPER"
//        const val TEAM_HOME_DEFENSE: String = "TEAM_HOME_DEFENSE"
//        const val TEAM_AWAY_DEFENSE: String = "TEAM_AWAY_DEFENSE"
//        const val TEAM_HOME_MIDFIELD: String = "TEAM_HOME_MIDFIELD"
//        const val TEAM_AWAY_MIDFIELD: String = "TEAM_AWAY_MIDFIELD"
//        const val TEAM_HOME_FORWARD: String = "TEAM_HOME_FORWARD"
//        const val TEAM_AWAY_FORWARD: String = "TEAM_AWAY_FORWARD"
//        const val TEAM_HOME_SUBSTITUTES: String = "TEAM_HOME_SUBSTITUTES"
//        const val TEAM_AWAY_SUBSTITUTES: String = "TEAM_AWAY_SUBSTITUTES"
    }
}