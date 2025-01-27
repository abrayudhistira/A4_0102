package abrayudhistira.cobafinal.ui.manajerproperti.viewmodel

import abrayudhistira.cobafinal.repository.ManajerPropertyRepository
import abrayudhistira.cobafinal.repository.PemilikRepository
import abrayudhistira.cobafinal.ui.navigasi.DestinasiUpdateManajemenProperti
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

class UpdateManajemenPropertiViewModel(
    savedStateHandle: SavedStateHandle,
    private val upManajemen: ManajerPropertyRepository
) : ViewModel() {
    var updateUiState by mutableStateOf(ManajemenPropertiUiState1())
        private set

    private val id_manajer: Int = checkNotNull(savedStateHandle[DestinasiUpdateManajemenProperti.idManajerArg])

    init {
        viewModelScope.launch {
            try {
                val updManajem = upManajemen.getbyidManajerProperti(id_manajer.toString())
                updateUiState = ManajemenPropertiUiState1(updManajem.toUiStateManajemenProp())
            } catch (e: Exception) {
                e.printStackTrace()
                updateUiState = ManajemenPropertiUiState1(error = "Failed to load category.")
            }
        }
    }

    fun updateInsertManajemenPropertiState(manajemenPropertiUiEvent: ManajemenPropertiUiEvent) {
        updateUiState = ManajemenPropertiUiState1(manajemenPropertiUiEvent = manajemenPropertiUiEvent)
    }

    fun updateManajemenProperti() {
        viewModelScope.launch {
            try {
                upManajemen.editManajerProperti(id_manajer.toString(), updateUiState.manajemenPropertiUiEvent.toManajemenProperti())
            } catch (e: Exception) {
                e.printStackTrace()
                updateUiState = updateUiState.copy(error = "Failed to update category.")
            }
        }
    }
}