package uz.eloving.quizgame.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.eloving.quizgame.model.ModelCountryResponse
import uz.eloving.quizgame.repository.Repository

class MainFragmentViewModel(private val repository: Repository) : ViewModel() {
    val myResponse: MutableLiveData<ModelCountryResponse> = MutableLiveData()
    fun getCountry() {
        viewModelScope.launch {
            val response: ModelCountryResponse = repository.getCountry("uz")
            myResponse.value = response
        }
    }
}