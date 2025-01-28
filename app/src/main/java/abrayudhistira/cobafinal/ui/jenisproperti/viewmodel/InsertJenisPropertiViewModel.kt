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

    fun insertjenisPemilik(): Boolean {
        val event = uiState.jenisPropertiUiEvent
        if (event.nama_jenis.isBlank() || event.deskripsi_jenis.isNullOrBlank()) {
            uiState = uiState.copy(error = "Please fill all fields")
            return false
        }

        viewModelScope.launch {
            try {
                jenisProp.insertJenisProperti(event.toJenisProperti())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return true
    }
}

data class JenisPropertiUiState1(
    val jenisPropertiUiEvent: JenisPropertiUiEvent = JenisPropertiUiEvent(),
    val error: String? = null
)

// Define the event class representing a category
data class JenisPropertiUiEvent(
    val id_jenis: Int = 0,
    val nama_jenis: String = "",
    val deskripsi_jenis: String? = ""
)

// Extension function to convert KategoriUiEvent to Kategori (Model class)
fun JenisPropertiUiEvent.toJenisProperti(): JenisProperti = JenisProperti(
    id_jenis = id_jenis,
    nama_jenis = nama_jenis,
    deskripsi_jenis = deskripsi_jenis
)

// Extension function to convert Kategori model to KategoriUiEvent
fun JenisProperti.toUiStateJenisProp(): JenisPropertiUiEvent = JenisPropertiUiEvent(
    id_jenis = id_jenis,
    nama_jenis = nama_jenis,
    deskripsi_jenis = deskripsi_jenis
)