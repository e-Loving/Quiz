package uz.eloving.quizgame.data

import uz.eloving.quizgame.R
import uz.eloving.quizgame.model.ModelCardViewContinent
import uz.eloving.quizgame.model.ModelCardViewLevel

class MockData {
    companion object {
        val leveData = arrayListOf(
            ModelCardViewLevel(R.drawable.iv_student, 500, R.drawable.iv_student_degree),
            ModelCardViewLevel(R.drawable.iv_tourist, 600, R.drawable.iv_tourist_degree),
            ModelCardViewLevel(R.drawable.iv_teacher, 700, R.drawable.iv_teacher_degree),
            ModelCardViewLevel(R.drawable.iv_mafia, 800, R.drawable.iv_mafia_degree),
            ModelCardViewLevel(R.drawable.iv_alien, 1000, R.drawable.iv_alien_degree)
        )
        val continentData = arrayListOf(
            ModelCardViewContinent(R.drawable.cv_all, true),
            ModelCardViewContinent(R.drawable.cv_europe),
            ModelCardViewContinent(R.drawable.cv_asia),
            ModelCardViewContinent(R.drawable.cv_north_america),
            ModelCardViewContinent(R.drawable.cv_south_america),
        )
        val asia = arrayListOf(
            "AE", "AF", "AM", "AZ", "BD",
            "BH", "BN", "BT", "CC", "CN",
            "CX", "CY", "GE", "HK", "ID",
            "IL", "IN", "IO", "IQ", "IR",
            "JO", "JP", "KG", "KH", "KP",
            "KR", "KW", "KZ", "LA", "LB",
            "LK", "MM", "MN", "MO", "MV",
            "MY", "NP", "OM", "PH", "PK",
            "PS", "QA", "RU", "SA", "SG",
            "SY", "TH", "TJ", "TL", "TM",
            "TR", "TW", "UZ", "VN", "YE",
        )
        val europe = arrayListOf(
            "AD", "AL", "AM", "AT", "AX",
            "AZ", "BA", "BE", "BG", "BY",
            "CH", "CY", "CZ", "DE", "DK",
            "EE", "ES", "FI", "FO", "FR",
            "GB", "GE", "GG", "GI", "GR",
            "HR", "HU", "IE", "IM", "IS",
            "IT", "JE", "KZ", "LI", "LT",
            "LU", "LV", "MC", "MD", "ME",
            "MK", "MT", "NL", "NO", "PL",
            "PT", "RO", "RS", "RU", "SE",
            "SI", "SJ", "SK", "SM", "TR",
            "UA", "VA",
        )
        val north_america = arrayListOf(
            "AG", "AI", "AW", "BB", "BL",
            "BM", "BQ", "BS", "BZ", "CA",
            "CR", "CU", "CW", "DM", "DO",
            "GD", "GL", "GP", "GT", "HN",
            "HT", "JM", "KN", "KY", "LC",
            "MF", "MQ", "MS", "MX", "NI",
            "PA", "PM", "PR", "SV", "SX",
            "TC", "TT", "UM", "US", "VC",
            "VG", "VI",
        )
        val south_america = arrayListOf(
            "AR", "BO", "BR", "CL", "CO",
            "EC", "FK", "GF", "GY", "PE",
            "PY", "SR", "UY", "VE",
        )
    }
}