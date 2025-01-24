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
        private set

    // Update the UI state with the new category event
    fun updateInsertPemilikState(pemilikUiEvent: PemilikUiEvent) {
        uiState = PemilikUiState1(pemilikUiEvent = pemilikUiEvent)
    }

    // Insert the location by calling the r epository
    fun insertpemilik() {
        viewModelScope.launch {
            try {
                // Call the insertLokasi method on the actual LokasiRepository
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
    val id_pemilik: Int = 0,
    val nama_pemilik: String = "",
    val kontak_pemilik: String = ""
)

// Extension function to convert KategoriUiEvent to Kategori (Model class)
fun PemilikUiEvent.toPmlk(): Pemilik = Pemilik(
    id_pemilik = id_pemilik,
    nama_pemilik = nama_pemilik,
    kontak_pemilik = kontak_pemilik
)

// Extension function to convert Kategori model to KategoriUiEvent
fun Pemilik.toUiStatePmlk(): PemilikUiEvent = PemilikUiEvent(
    id_pemilik = id_pemilik,
    nama_pemilik = nama_pemilik,
    kontak_pemilik = kontak_pemilik
)