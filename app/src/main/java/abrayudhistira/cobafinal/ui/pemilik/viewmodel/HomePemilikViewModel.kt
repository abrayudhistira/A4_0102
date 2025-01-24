package abrayudhistira.cobafinal.ui.pemilik.viewmodel

import abrayudhistira.cobafinal.model.JenisProperti
import abrayudhistira.cobafinal.model.Pemilik
import abrayudhistira.cobafinal.repository.JenisPropertyRepository
import abrayudhistira.cobafinal.repository.PemilikRepository
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

class HomePemilikViewModel(private val pemilikRepository: PemilikRepository) : ViewModel() {

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
                val jenisPropertiList = pemilikRepository.getPemilik()
                homePemilikUiState = HomePemilikUiState.Success(jenisPropertiList)
            } catch (e: IOException) {
                homePemilikUiState = HomePemilikUiState.Error
            } catch (e: HttpException) {
                homePemilikUiState = HomePemilikUiState.Error
            }
        }
    }
    fun getbyidPemilik(
        id_pemilik: Int,
        onSuccess: (Pemilik) -> Unit,
        onError: () -> Unit) {
        viewModelScope.launch {
            try {
                val pemilik = pemilikRepository.getbyidPemilik(id_pemilik.toString())
                onSuccess(pemilik)
            } catch (e: IOException) {
                onError()
            } catch (e: HttpException) {
                onError()
            }
        }
    }
    fun deletePemilik(id_pemilik: Int) {
        viewModelScope.launch {
            try {
                pemilikRepository.deletePemilik(id_pemilik.toString())
                getPemilik()
            } catch (e: IOException) {
                homePemilikUiState = HomePemilikUiState.Error
            } catch (e: HttpException) {
                homePemilikUiState = HomePemilikUiState.Error
            }
        }
    }
}