package abrayudhistira.cobafinal.ui.property

import abrayudhistira.cobafinal.model.Properti
import abrayudhistira.cobafinal.repository.PropertiRepository
import android.util.Log
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
        Log.d("HomePropertyViewModel", "ViewModel diinisialisasi, memuat data...")
        getProperti()
    }
//    fun getProperti() {
//        viewModelScope.launch {
//            prpViewModel = HomeUiState.Loading
//            prpViewModel = try {
//                HomeUiState.Success(prop.getProperty())
//            } catch (e:Exception) {
//                HomeUiState.Error
//            }
//        }
//    }
    fun getProperti() {
        viewModelScope.launch {
            prpViewModel = HomeUiState.Loading
            Log.d("HomePropertyViewModel", "Memulai pengambilan data...")

            prpViewModel = try {
                val data = prop.getProperty()
                Log.d("HomePropertyViewModel", "Data berhasil diambil: ${data.size} properti")
                HomeUiState.Success(data)
            } catch (e: Exception) {
                Log.e("HomePropertyViewModel", "Error saat mengambil data: ${e.message}", e)
                HomeUiState.Error
            }
        }
    }
}