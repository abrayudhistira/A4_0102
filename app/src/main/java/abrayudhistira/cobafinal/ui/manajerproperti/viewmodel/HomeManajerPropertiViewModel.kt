package abrayudhistira.cobafinal.ui.manajerproperti.viewmodel

import abrayudhistira.cobafinal.model.JenisProperti
import abrayudhistira.cobafinal.model.ManajerProperti
import abrayudhistira.cobafinal.model.Properti
import abrayudhistira.cobafinal.repository.JenisPropertyRepository
import abrayudhistira.cobafinal.repository.ManajerPropertyRepository
import abrayudhistira.cobafinal.repository.PropertiRepository
import abrayudhistira.cobafinal.ui.pemilik.viewmodel.HomePemilikUiState
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class HomeManajerPropertiUiState {
    data class Success(val manajerProperti: List<ManajerProperti>) : HomeManajerPropertiUiState()
    object Error : HomeManajerPropertiUiState()
    object Loading : HomeManajerPropertiUiState()
}

class HomeManajerPropertiViewModel(private val manajerPropertyRepository: ManajerPropertyRepository ) : ViewModel() {

    var homemanajerPropertiUiState : HomeManajerPropertiUiState by mutableStateOf(HomeManajerPropertiUiState.Loading)
        private set
    init {
        Log.d("HomeManajerPropertiViewModel", "ViewModel diinisialisasi, memuat data...")
        getManajerProperti()
    }
    fun getManajerProperti() {
        viewModelScope.launch {
            homemanajerPropertiUiState = HomeManajerPropertiUiState.Loading
            try {
                val jenisPropertiList = manajerPropertyRepository.getManajerProperti()
                homemanajerPropertiUiState = HomeManajerPropertiUiState.Success(jenisPropertiList)
            } catch (e:IOException) {
                homemanajerPropertiUiState = HomeManajerPropertiUiState.Error
            } catch (e:HttpException) {
                homemanajerPropertiUiState = HomeManajerPropertiUiState.Error
            }
        }
    }
    fun deleteManajer(id_manajer: Int) {
        viewModelScope.launch {
            try {
                manajerPropertyRepository.deleteManajerProperti(id_manajer.toString())
                getManajerProperti()
            } catch (e: IOException) {
                homemanajerPropertiUiState = HomeManajerPropertiUiState.Error
            } catch (e: HttpException) {
                homemanajerPropertiUiState = HomeManajerPropertiUiState.Error
            }
        }
    }
}