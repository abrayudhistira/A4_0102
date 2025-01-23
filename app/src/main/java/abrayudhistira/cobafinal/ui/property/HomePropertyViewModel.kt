package abrayudhistira.cobafinal.ui.property

import abrayudhistira.cobafinal.model.Properti
import abrayudhistira.cobafinal.repository.PropertiRepository
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

sealed class HomeUiState {
    data class Success(val properti: List<Properti>) : HomeUiState()
    object Error : HomeUiState()
    object Loading : HomeUiState()
}

class HomePropertyViewModel(private val prop: PropertiRepository) : ViewModel() {

    var prpViewModel : HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set
    init {
        getProperti()
    }
    fun getProperti() {
        viewModelScope.launch {
            prpViewModel = HomeUiState.Loading
            prpViewModel = try {
                HomeUiState.Success(prop.getProperty())
            } catch (e:Exception) {
                HomeUiState.Error
            } catch (e:Exception) {
                HomeUiState.Error
            }
        }
    }
}