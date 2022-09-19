package uz.eloving.quizgame.repository

import uz.eloving.quizgame.api.RetrofitInstance
import uz.eloving.quizgame.model.ModelCountry
import uz.eloving.quizgame.model.ModelCountryResponse

class Repository {
    suspend fun getCountry(country_code: String): ModelCountryResponse {
        return RetrofitInstance.api.getCountry(country_code)
    }
}