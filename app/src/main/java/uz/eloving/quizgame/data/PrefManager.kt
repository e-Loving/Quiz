package uz.eloving.quizgame.data

import android.content.Context
import android.content.SharedPreferences

const val CACHE = "quizgame"
const val CONTINENT = "continent"
const val HIGH_SCORE_ALL = "high_score_all"
const val HIGH_SCORE_ASIA = "high_score_asia"
const val HIGH_SCORE_EUROPE = "high_score_europe"
const val HIGH_SCORE_NORTH_AMERICA = "high_score_north_america"
const val HIGH_SCORE_SOUTH_AMERICA = "high_score_south_america"

class PrefManager {
    companion object {
        private fun getInstance(ctx: Context): SharedPreferences {
            return ctx.getSharedPreferences(CACHE, Context.MODE_PRIVATE)
        }

        fun getContinent(ctx: Context): Int {
            return getInstance(ctx).getInt(CONTINENT, 0)
        }

        fun setContinent(ctx: Context, continent: Int) {
            getInstance(ctx).edit().putInt(CONTINENT, continent).apply()
        }

        fun getHighScoreAll(ctx: Context): Int {
            return getInstance(ctx).getInt(HIGH_SCORE_ALL, 0)
        }

        fun setHighScoreAll(ctx: Context, highScore: Int) {
            getInstance(ctx).edit().putInt(HIGH_SCORE_ALL, highScore).apply()
        }

        fun getHighScoreAsia(ctx: Context): Int {
            return getInstance(ctx).getInt(HIGH_SCORE_ASIA, 0)
        }

        fun setHighScoreAsia(ctx: Context, highScore: Int) {
            getInstance(ctx).edit().putInt(HIGH_SCORE_ASIA, highScore).apply()
        }

        fun getHighScoreEurope(ctx: Context): Int {
            return getInstance(ctx).getInt(HIGH_SCORE_EUROPE, 0)
        }

        fun setHighScoreEurope(ctx: Context, highScore: Int) {
            getInstance(ctx).edit().putInt(HIGH_SCORE_EUROPE, highScore).apply()
        }

        fun getHighScoreNorthAmerica(ctx: Context): Int {
            return getInstance(ctx).getInt(HIGH_SCORE_NORTH_AMERICA, 0)
        }

        fun setHighScoreNorthAmerica(ctx: Context, highScore: Int) {
            getInstance(ctx).edit().putInt(HIGH_SCORE_NORTH_AMERICA, highScore).apply()
        }

        fun getHighScoreSouthAmerica(ctx: Context): Int {
            return getInstance(ctx).getInt(HIGH_SCORE_SOUTH_AMERICA, 0)
        }

        fun setHighScoreSouthAmerica(ctx: Context, highScore: Int) {
            getInstance(ctx).edit().putInt(HIGH_SCORE_SOUTH_AMERICA, highScore).apply()
        }

    }
}