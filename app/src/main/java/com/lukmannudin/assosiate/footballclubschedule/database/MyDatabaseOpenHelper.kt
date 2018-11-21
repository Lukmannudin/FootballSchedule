package com.lukmannudin.assosiate.footballclub.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.lukmannudin.assosiate.footballclubschedule.Favorite
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable(
            Favorite.TABLE_FAVORITE, true,
//            Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,

            Favorite.TEAM_MATCH_EVENT_ID to TEXT + PRIMARY_KEY ,
            Favorite.TEAM_MATCH_EVENT_DATE to TEXT,
            Favorite.TEAM_HOME_ID to TEXT,
            Favorite.TEAM_AWAY_ID to TEXT,
            Favorite.TEAM_HOME_NAME to TEXT,
            Favorite.TEAM_AWAY_NAME to TEXT,
            Favorite.TEAM_HOME_SCORE to TEXT,
            Favorite.TEAM_AWAY_SCORE to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(Favorite.TABLE_FAVORITE, true)
    }
}

val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)