package uz.eloving.quizgame.data

import uz.eloving.quizgame.R
import uz.eloving.quizgame.utils.AdapterLevelData

class MockData {
    companion object {
        val data = arrayListOf(
            AdapterLevelData(R.drawable.iv_student, 500, R.drawable.iv_student_degree),
            AdapterLevelData(R.drawable.iv_tourist, 600, R.drawable.iv_tourist_degree),
            AdapterLevelData(R.drawable.iv_teacher, 700, R.drawable.iv_teacher_degree),
            AdapterLevelData(R.drawable.iv_mafia, 800, R.drawable.iv_mafia_degree),
            AdapterLevelData(R.drawable.iv_alien, 1000, R.drawable.iv_alien_degree)
        )
    }
}