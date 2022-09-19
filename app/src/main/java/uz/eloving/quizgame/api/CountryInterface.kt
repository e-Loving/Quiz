package uz.eloving.quizgame.api

import retrofit2.http.GET
import retrofit2.http.Path
import uz.eloving.quizgame.model.ModelCountryResponse

interface CountryInterface {
    @GET("{country_code}")
    suspend fun getCountry(@Path("country_code") country_code: String): ModelCountryResponse
}