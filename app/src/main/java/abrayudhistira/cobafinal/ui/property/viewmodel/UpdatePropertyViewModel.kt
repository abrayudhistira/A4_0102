package abrayudhistira.cobafinal.ui.property.viewmodel

import abrayudhistira.cobafinal.model.JenisProperti
import abrayudhistira.cobafinal.model.ManajerProperti
import abrayudhistira.cobafinal.model.Pemilik
import abrayudhistira.cobafinal.repository.JenisPropertyRepository
import abrayudhistira.cobafinal.repository.ManajerPropertyRepository
import abrayudhistira.cobafinal.repository.PemilikRepository
import abrayudhistira.cobafinal.repository.PropertiRepository
import abrayudhistira.cobafinal.ui.properti.viewmodel.PropertiUiEvent
import abrayudhistira.cobafinal.ui.properti.viewmodel.PropertiUiState
import abrayudhistira.cobafinal.ui.properti.viewmodel.StatusProperti
import abrayudhistira.cobafinal.ui.properti.viewmodel.toProperti
import abrayudhistira.cobafinal.ui.property.view.DestinasiUpdateProperti
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UpdatePropertiViewModel(
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

    // Ambil idProperti dari SavedStateHandle
    private val idProperti: Int = checkNotNull(savedStateHandle[DestinasiUpdateProperti.idPropertiArg])

    // Fungsi untuk mengambil data dropdown dan data properti
    fun fetchData() {
        viewModelScope.launch {
            try {
                // Ambil data dropdown
                jenisPropertiList = jenisPropertyRepository.getJenisProperty()
                pemilikList = pemilikRepository.getPemilik()
                manajerList = manajerPropertyRepository.getManajerProperti()

                // Ambil data properti berdasarkan ID
                val properti = propertiRepository.getbyidProperti(idProperti.toString())
                uiState = uiState.copy(
                    propertiUiEvent = PropertiUiEvent(
                        idProperti = properti.idProperti,
                        namaProperti = properti.nama_properti,
                        deskripsiProperti = properti.deskripsi_properti,
                        lokasi = properti.lokasi,
                        harga = properti.Harga,
                        statusProperti = StatusProperti.valueOf(properti.statusProperti),
                        idJenis = properti.id_jenis,
                        idPemilik = properti.idPemilik,
                        idManajer = properti.id_manajer
                    )
                )
            } catch (e: Exception) {
                uiState = uiState.copy(error = "Gagal mengambil data: ${e.message}")
            }
        }
    }

    // Fungsi untuk update state dari form input
    fun updatePropertiState(event: PropertiUiEvent) {
        uiState = uiState.copy(propertiUiEvent = event)
    }

    // Fungsi untuk update properti
    fun updateProperti() {
        viewModelScope.launch {
            try {
                propertiRepository.editProperti(idProperti.toString(), uiState.propertiUiEvent.toProperti())
                uiState = uiState.copy(error = null) // Reset error state
            } catch (e: Exception) {
                uiState = uiState.copy(error = "Gagal mengupdate properti: ${e.message}")
            }
        }
    }
}