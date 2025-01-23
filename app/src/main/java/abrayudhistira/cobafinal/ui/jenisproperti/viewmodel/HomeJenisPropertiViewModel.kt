package abrayudhistira.cobafinal.ui.jenisproperti.viewmodel

import abrayudhistira.cobafinal.model.JenisProperti
import abrayudhistira.cobafinal.model.Properti
import abrayudhistira.cobafinal.repository.JenisPropertyRepository
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

sealed class HomeJenisPropertiUiState {
    data class Success(val jenisProperti: List<JenisProperti>) : HomeJenisPropertiUiState()
    object Error : HomeJenisPropertiUiState()
    object Loading : HomeJenisPropertiUiState()
}

class HomeJenisPropertiViewModel(private val jenisPropertyRepository: JenisPropertyRepository ) : ViewModel() {

    var homeJenisPropertiUiState : HomeJenisPropertiUiState by mutableStateOf(HomeJenisPropertiUiState.Loading)
        private set
    init {
        Log.d("HomePropertyViewModel", "ViewModel diinisialisasi, memuat data...")
        getJenisProperti()
    }
    fun getJenisProperti() {
        viewModelScope.launch {
            homeJenisPropertiUiState = HomeJenisPropertiUiState.Loading
            try {
                val jenisPropertiList = jenisPropertyRepository.getJenisProperty()
                homeJenisPropertiUiState = HomeJenisPropertiUiState.Success(jenisPropertiList)
            } catch (e:IOException) {
                homeJenisPropertiUiState = HomeJenisPropertiUiState.Error
            } catch (e:HttpException) {
                homeJenisPropertiUiState = HomeJenisPropertiUiState.Error
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