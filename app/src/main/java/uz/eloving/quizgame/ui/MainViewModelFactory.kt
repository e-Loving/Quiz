package uz.eloving.quizgame.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uz.eloving.quizgame.repository.Repository

class MainViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainFragmentViewModel(repository) as T
    }
}