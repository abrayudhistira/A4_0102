package abrayudhistira.cobafinal.ui.properti.viewmodel

import abrayudhistira.cobafinal.model.JenisProperti
import abrayudhistira.cobafinal.model.ManajerProperti
import abrayudhistira.cobafinal.model.Pemilik
import abrayudhistira.cobafinal.model.Properti
import abrayudhistira.cobafinal.repository.JenisPropertyRepository
import abrayudhistira.cobafinal.repository.ManajerPropertyRepository
import abrayudhistira.cobafinal.repository.PemilikRepository
import abrayudhistira.cobafinal.repository.PropertiRepository
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class InsertPropertiViewModel(
    savedStateHandle: SavedStateHandle,
    private val propertiRepository: PropertiRepository,
    private val jenisPropertyRepository: JenisPropertyRepository,
    private val pemilikRepository: PemilikRepository,
    private val manajerPropertyRepository: ManajerPropertyRepository
) : ViewModel() {

    // State untuk UI
    var uiState by mutableStateOf(PropertiUiState())
        private set

    // State untuk dropdown
    var jenisPropertiList by mutableStateOf<List<JenisProperti>>(emptyList())
    var pemilikList by mutableStateOf<List<Pemilik>>(emptyList())
    var manajerList by mutableStateOf<List<ManajerProperti>>(emptyList())

    // Fungsi untuk mengambil data dropdown
    fun fetchDropdownData() {
        viewModelScope.launch {
            try {
                jenisPropertiList = jenisPropertyRepository.getJenisProperty()
                pemilikList = pemilikRepository.getPemilik()
                manajerList = manajerPropertyRepository.getManajerProperti()
            } catch (e: Exception) {
                // Handle error
                uiState = uiState.copy(error = "Gagal mengambil data dropdown: ${e.message}")
            }
        }
    }

    // Fungsi untuk update state dari form input
    fun updatePropertiState(event: PropertiUiEvent) {
        uiState = uiState.copy(propertiUiEvent = event)
    }

    // Fungsi untuk insert properti
    fun insertProperti() {
        viewModelScope.launch {
            try {
                propertiRepository.insertProperti(uiState.propertiUiEvent.toProperti())
                uiState = uiState.copy(error = null) // Reset error state
            } catch (e: Exception) {
                uiState = uiState.copy(error = "Gagal menyimpan properti: ${e.message}")
            }
        }
    }
}

// State untuk UI
data class PropertiUiState(
    val propertiUiEvent: PropertiUiEvent = PropertiUiEvent(),
    val error: String? = null
)

// Event untuk form input
data class PropertiUiEvent(
    val idProperti: Int = 0,
    val namaProperti: String = "",
    val deskripsiProperti: String = "",
    val lokasi: String = "",
    val harga: String = "",
    val statusProperti: StatusProperti = StatusProperti.Tersedia,
    val idJenis: Int = 0,
    val idPemilik: Int = 0,
    val idManajer: Int = 0,
    val error: String? = null
)

// Extension function untuk mengubah PropertiUiEvent menjadi Properti
fun PropertiUiEvent.toProperti(): Properti = Properti(
    idProperti = idProperti,
    nama_properti = namaProperti,
    deskripsi_properti = deskripsiProperti,
    lokasi = lokasi,
    Harga = harga,
    statusProperti = statusProperti.name,
    id_jenis = idJenis,
    idPemilik = idPemilik,
    id_manajer = idManajer
)
// Enum class untuk status properti
enum class StatusProperti {
    Tersedia, Tersewa, Dijual
}