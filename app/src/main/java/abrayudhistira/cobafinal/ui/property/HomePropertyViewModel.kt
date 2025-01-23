package abrayudhistira.cobafinal.ui.property

import abrayudhistira.cobafinal.model.Properti
import abrayudhistira.cobafinal.repository.PropertiRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

sealed class HomeUiState {
    data class Success(val properti: List<Properti>) : HomeUiState()
    object Error : HomeUiState()
    object Loading : HomeUiState()
}

class HomePropertyViewModel() : ViewModel() {

    private val repository = PropertiRepository()

    fun getProperti(): LiveData<List<Properti>> {
        return repository.getProperti()
    }
}