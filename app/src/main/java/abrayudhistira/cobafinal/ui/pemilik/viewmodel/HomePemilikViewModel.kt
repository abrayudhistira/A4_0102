package abrayudhistira.cobafinal.ui.pemilik.viewmodel

import abrayudhistira.cobafinal.model.JenisProperti
import abrayudhistira.cobafinal.model.Pemilik
import abrayudhistira.cobafinal.repository.JenisPropertyRepository
import abrayudhistira.cobafinal.repository.PemilikRespository
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class HomePemilikUiState {
    data class Success(val pemilik: List<Pemilik>) : HomePemilikUiState()
    object Error : HomePemilikUiState()
    object Loading : HomePemilikUiState()
}

class HomePemilikViewModel(private val pemilikRespository: PemilikRespository) : ViewModel() {

    var homePemilikUiState: HomePemilikUiState by mutableStateOf(HomePemilikUiState.Loading)
        private set

    init {
        Log.d("HomePropertyViewModel", "ViewModel diinisialisasi, memuat data...")
        getPemilik()
    }

    fun getPemilik() {
        viewModelScope.launch {
            homePemilikUiState = HomePemilikUiState.Loading
            try {
                val jenisPropertiList = pemilikRespository.getPemilik()
                homePemilikUiState = HomePemilikUiState.Success(jenisPropertiList)
            } catch (e: IOException) {
                homePemilikUiState = HomePemilikUiState.Error
            } catch (e: HttpException) {
                homePemilikUiState = HomePemilikUiState.Error
            }
        }
    }
}