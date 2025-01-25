package abrayudhistira.cobafinal.ui.property.viewmodel

import abrayudhistira.cobafinal.model.Properti
import abrayudhistira.cobafinal.repository.PropertiRepository
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class HomeUiState {
    data class Success(val properti: List<Properti>) : HomeUiState()
    object Error : HomeUiState()
    object Loading : HomeUiState()
}

class HomePropertyViewModel(private val propertiRepository : PropertiRepository ) : ViewModel() {

    var homeUiState : HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set
    init {
        Log.d("HomePropertyViewModel", "ViewModel diinisialisasi, memuat data...")
        getProperti()
    }
    fun getProperti() {
        viewModelScope.launch {
            homeUiState = HomeUiState.Loading
            try {
                val propertiList = propertiRepository.getProperty()
                homeUiState = HomeUiState.Success(propertiList)
            } catch (e:IOException) {
                homeUiState = HomeUiState.Error
            } catch (e:HttpException) {
                homeUiState = HomeUiState.Error
            }
        }
    }
//    fun getProperti() {
//        viewModelScope.launch {
//            homeUiState = HomeUiState.Loading
//            Log.d("HomePropertyViewModel", "Memulai pengambilan data...")
//
//            homeUiState = try {
//                val data = propertiRepository.getProperty()
//                Log.d("HomePropertyViewModel", "Data berhasil diambil: ${data.size} properti")
//                HomeUiState.Success(data)
//            } catch (e: Exception) {
//                Log.e("HomePropertyViewModel", "Error saat mengambil data: ${e.message}", e)
//                HomeUiState.Error
//            }
//        }
//    }
}