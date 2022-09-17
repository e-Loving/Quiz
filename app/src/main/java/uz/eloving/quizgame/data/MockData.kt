package uz.eloving.quizgame.data

import uz.eloving.quizgame.R

class MockData {
    companion object {
        val leveData = arrayListOf(
            DataLevel(R.drawable.iv_student, 500, R.drawable.iv_student_degree),
            DataLevel(R.drawable.iv_tourist, 600, R.drawable.iv_tourist_degree),
            DataLevel(R.drawable.iv_teacher, 700, R.drawable.iv_teacher_degree),
            DataLevel(R.drawable.iv_mafia, 800, R.drawable.iv_mafia_degree),
            DataLevel(R.drawable.iv_alien, 1000, R.drawable.iv_alien_degree)
        )
        val continentData = arrayListOf(
            DataContinent(R.drawable.cv_all, true),
            DataContinent(R.drawable.cv_europe),
            DataContinent(R.drawable.cv_asia),
            DataContinent(R.drawable.cv_north_america),
            DataContinent(R.drawable.cv_south_america),
        )
    }
}