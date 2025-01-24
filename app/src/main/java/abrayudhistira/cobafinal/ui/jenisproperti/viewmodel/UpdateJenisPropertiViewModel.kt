package abrayudhistira.cobafinal.ui.jenisproperti.viewmodel

import abrayudhistira.cobafinal.repository.JenisPropertyRepository
import abrayudhistira.cobafinal.repository.PemilikRepository
import abrayudhistira.cobafinal.ui.navigasi.DestinasiDetailJenisProperti
import abrayudhistira.cobafinal.ui.navigasi.DestinasiUpdateJenisProperti
import abrayudhistira.cobafinal.ui.navigasi.DestinasiUpdatePemilik
import abrayudhistira.cobafinal.ui.pemilik.viewmodel.PemilikUiEvent
import abrayudhistira.cobafinal.ui.pemilik.viewmodel.PemilikUiState1
import abrayudhistira.cobafinal.ui.pemilik.viewmodel.toPmlk
import abrayudhistira.cobafinal.ui.pemilik.viewmodel.toUiStatePmlk
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UpdateJenisPropertiViewModel(
    savedStateHandle: SavedStateHandle,
    private val upJenis: JenisPropertyRepository
) : ViewModel() {
    var updateJenisUiState by mutableStateOf(JenisPropertiUiState1())
        private set

    private val idJenis: Int = checkNotNull(savedStateHandle[DestinasiUpdateJenisProperti.idJenisArg])

    init {
        viewModelScope.launch {
            try {
                val updJenis = upJenis.getbyidJenisProperti(idJenis.toString())
                updateJenisUiState = JenisPropertiUiState1(updJenis.toUiStateJenisProp())
            } catch (e: Exception) {
                e.printStackTrace()
                updateJenisUiState = JenisPropertiUiState1(error = "Failed to load category.")
            }
        }
    }

    fun updateInsertJenisPropertiState(jenisPropertiUiEvent: JenisPropertiUiEvent) {
        updateJenisUiState = JenisPropertiUiState1(jenisPropertiUiEvent = jenisPropertiUiEvent)
    }

    fun updateJenisProperti() {
        viewModelScope.launch {
            try {
                upJenis.editJenisProperti(idJenis.toString(), updateJenisUiState.jenisPropertiUiEvent.toJenisProperti())
            } catch (e: Exception) {
                e.printStackTrace()
                updateJenisUiState = updateJenisUiState.copy(error = "Failed to update category.")
            }
        }
    }
}