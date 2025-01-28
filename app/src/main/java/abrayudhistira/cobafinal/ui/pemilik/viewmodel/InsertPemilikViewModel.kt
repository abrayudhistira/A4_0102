package abrayudhistira.cobafinal.ui.pemilik.viewmodel

import abrayudhistira.cobafinal.model.Pemilik
import abrayudhistira.cobafinal.repository.PemilikRepository
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class InsertPemilikViewModel(
    pemilikViewModel: SavedStateHandle,
    private val pmlk: PemilikRepository
) : ViewModel() {
    // Define the UI state variable for Insert operation
    var uiState by mutableStateOf(PemilikUiState1())

    // Update the UI state with the new category event
    fun updateInsertPemilikState(pemilikUiEvent: PemilikUiEvent) {
        uiState = PemilikUiState1(pemilikUiEvent = pemilikUiEvent)
    }

    // Insert the location by calling the r epository
    fun insertpemilik() {
        viewModelScope.launch {
            try {
                // Check if any field is empty
                if (uiState.pemilikUiEvent.nama_pemilik.isEmpty() || uiState.pemilikUiEvent.kontak_pemilik.isEmpty()) {
                    uiState = uiState.copy(error = "Semua field harus diisi!")
                    return@launch
                }

                // Proceed with the insert operation if validation passes
                pmlk.insertPemilik(uiState.pemilikUiEvent.toPmlk())
            } catch (e: Exception) {
                // Handle the error (e.g., log or update the UI state with an error message)
                e.printStackTrace()
            }
        }
    }
}

data class PemilikUiState1(
    val pemilikUiEvent: PemilikUiEvent = PemilikUiEvent(),
    val error: String? = null
)

// Define the event class representing a category
data class PemilikUiEvent(
    val idPemilik: Int = 0,
    val nama_pemilik: String = "",
    val kontak_pemilik: String = ""
)

// Extension function to convert KategoriUiEvent to Kategori (Model class)
fun PemilikUiEvent.toPmlk(): Pemilik = Pemilik(
    idPemilik = idPemilik,
    nama_pemilik = nama_pemilik,
    kontak_pemilik = kontak_pemilik
)

// Extension function to convert Kategori model to KategoriUiEvent
fun Pemilik.toUiStatePmlk(): PemilikUiEvent = PemilikUiEvent(
    idPemilik = idPemilik,
    nama_pemilik = nama_pemilik,
    kontak_pemilik = kontak_pemilik
)