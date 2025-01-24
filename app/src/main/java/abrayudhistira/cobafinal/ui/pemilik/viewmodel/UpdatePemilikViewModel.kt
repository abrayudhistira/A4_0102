package abrayudhistira.cobafinal.ui.pemilik.viewmodel

import abrayudhistira.cobafinal.repository.PemilikRepository
import abrayudhistira.cobafinal.ui.navigasi.DestinasiUpdatePemilik
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UpdatePemilikViewModel(
    savedStateHandle: SavedStateHandle,
    private val upPemilik: PemilikRepository
) : ViewModel() {
    var updateUiState by mutableStateOf(PemilikUiState1())
        private set

    private val idPemilik: Int = checkNotNull(savedStateHandle[DestinasiUpdatePemilik.idPemilikArg])

    init {
        viewModelScope.launch {
            try {
                val updPemilik = upPemilik.getbyidPemilik(idPemilik.toString())
                updateUiState = PemilikUiState1(updPemilik.toUiStatePmlk())
            } catch (e: Exception) {
                e.printStackTrace()
                updateUiState = PemilikUiState1(error = "Failed to load category.")
            }
        }
    }

    fun updateInsertPemilikState(pemilikUiEvent: PemilikUiEvent) {
        updateUiState = PemilikUiState1(pemilikUiEvent = pemilikUiEvent)
    }

    fun updatePemilik() {
        viewModelScope.launch {
            try {
                upPemilik.editPemilik(idPemilik.toString(), updateUiState.pemilikUiEvent.toPmlk())
            } catch (e: Exception) {
                e.printStackTrace()
                updateUiState = updateUiState.copy(error = "Failed to update category.")
            }
        }
    }
}