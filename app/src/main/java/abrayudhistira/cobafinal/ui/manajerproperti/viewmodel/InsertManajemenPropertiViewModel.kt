package abrayudhistira.cobafinal.ui.manajerproperti.viewmodel

import abrayudhistira.cobafinal.model.JenisProperti
import abrayudhistira.cobafinal.model.ManajerProperti
import abrayudhistira.cobafinal.repository.JenisPropertyRepository
import abrayudhistira.cobafinal.repository.ManajerPropertyRepository
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class InsertManajemenPopertiViewModel(
    manajemenPropertiViewModel: SavedStateHandle,
    private val manajProp: ManajerPropertyRepository
) : ViewModel() {
    // Define the UI state variable for Insert operation
    var uiState by mutableStateOf(ManajemenPropertiUiState1())
        private set

    // Update the UI state with the new category event
    fun updateInsertManajemenPropertiState(manajemenPropertiUiEvent: ManajemenPropertiUiEvent) {
        uiState = ManajemenPropertiUiState1(manajemenPropertiUiEvent = manajemenPropertiUiEvent)
    }

    // Insert the location by calling the r epository
    fun insertmanajemenProperti() {
        viewModelScope.launch {
            try {
                // Call the insertLokasi method on the actual LokasiRepository
                manajProp.insertManajerProperti(uiState.manajemenPropertiUiEvent.toManajemenProperti())
            } catch (e: Exception) {
                // Handle the error (e.g., log or update the UI state with an error message)
                e.printStackTrace()
            }
        }
    }
}

data class ManajemenPropertiUiState1(
    val manajemenPropertiUiEvent: ManajemenPropertiUiEvent = ManajemenPropertiUiEvent(),
    val error: String? = null
)

// Define the event class representing a category
data class ManajemenPropertiUiEvent(
    val id_manajer: Int = 0,
    val nama_manajer: String = "",
    val kontak_manajer: String = ""
)

// Extension function to convert KategoriUiEvent to Kategori (Model class)
fun ManajemenPropertiUiEvent.toManajemenProperti(): ManajerProperti = ManajerProperti(
    id_manajer = id_manajer,
    nama_manajer = nama_manajer,
    kontak_manajer = kontak_manajer
)

// Extension function to convert Kategori model to KategoriUiEvent
fun ManajerProperti.toUiStateManajemenProp(): ManajemenPropertiUiEvent = ManajemenPropertiUiEvent(
    id_manajer = id_manajer,
    nama_manajer = nama_manajer,
    kontak_manajer = kontak_manajer
)