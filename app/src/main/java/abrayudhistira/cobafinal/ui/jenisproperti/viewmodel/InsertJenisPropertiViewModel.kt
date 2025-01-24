package abrayudhistira.cobafinal.ui.jenisproperti.viewmodel

import abrayudhistira.cobafinal.model.JenisProperti
import abrayudhistira.cobafinal.model.Pemilik
import abrayudhistira.cobafinal.repository.JenisPropertyRepository
import abrayudhistira.cobafinal.repository.PemilikRepository
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class InsertJenisPopertiViewModel(
    jenisPropertiViewModel: SavedStateHandle,
    private val jenisProp: JenisPropertyRepository
) : ViewModel() {
    // Define the UI state variable for Insert operation
    var uiState by mutableStateOf(JenisPropertiUiState1())
        private set

    // Update the UI state with the new category event
    fun updateInsertJenisPropertiState(jenisPropertiUiEvent: JenisPropertiUiEvent) {
        uiState = JenisPropertiUiState1(jenisPropertiUiEvent = jenisPropertiUiEvent)
    }

    // Insert the location by calling the r epository
    fun insertjenisPemilik() {
        viewModelScope.launch {
            try {
                // Call the insertLokasi method on the actual LokasiRepository
                jenisProp.insertJenisProperti(uiState.jenisPropertiUiEvent.toJenisProperti())
            } catch (e: Exception) {
                // Handle the error (e.g., log or update the UI state with an error message)
                e.printStackTrace()
            }
        }
    }
}

data class JenisPropertiUiState1(
    val jenisPropertiUiEvent: JenisPropertiUiEvent = JenisPropertiUiEvent(),
    val error: String? = null
)

// Define the event class representing a category
data class JenisPropertiUiEvent(
    val idJenis: Int = 0,
    val nama_jenis: String = "",
    val deskripsi_jenis: String? = ""
)

// Extension function to convert KategoriUiEvent to Kategori (Model class)
fun JenisPropertiUiEvent.toJenisProperti(): JenisProperti = JenisProperti(
    idJenis = idJenis,
    nama_jenis = nama_jenis,
    deskripsi_jenis = deskripsi_jenis
)

// Extension function to convert Kategori model to KategoriUiEvent
fun JenisProperti.toUiStateJenisProp(): JenisPropertiUiEvent = JenisPropertiUiEvent(
    idJenis = idJenis,
    nama_jenis = nama_jenis,
    deskripsi_jenis = deskripsi_jenis
)