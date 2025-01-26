package abrayudhistira.cobafinal.ui.property.viewmodel

import abrayudhistira.cobafinal.model.JenisProperti
import abrayudhistira.cobafinal.model.ManajerProperti
import abrayudhistira.cobafinal.model.Pemilik
import abrayudhistira.cobafinal.model.Properti
import abrayudhistira.cobafinal.repository.JenisPropertyRepository
import abrayudhistira.cobafinal.repository.ManajerPropertyRepository
import abrayudhistira.cobafinal.repository.PemilikRepository

import abrayudhistira.cobafinal.repository.PropertiRepository
import abrayudhistira.cobafinal.ui.navigasi.DestinasiDetailProperti
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.serialization.json.Json
import kotlinx.coroutines.launch

class DetailPropertyViewModel(
    savedStateHandle: SavedStateHandle,
    private val propertiRepository: PropertiRepository,
    private val jenisPropertyRepository: JenisPropertyRepository, // Tambahkan ini
    private val pemilikRepository: PemilikRepository, // Tambahkan ini
    private val manajerPropertyRepository: ManajerPropertyRepository // Tambahkan ini
) : ViewModel() {

    private val id_properti: Int = checkNotNull(savedStateHandle[DestinasiDetailProperti.idPropertiArg])

    var detailPropertyUiState: DetailPropertyUiState by mutableStateOf(DetailPropertyUiState())
        private set

    init {
        getByIdProperti()
    }

    fun getByIdProperti() {
        viewModelScope.launch {
            detailPropertyUiState = DetailPropertyUiState(isLoading = true)
            try {
                // Ambil data properti
                val jsonResponse = propertiRepository.getbyidProperti(id_properti.toString())
                println("JSON Response: $jsonResponse") // Log the JSON response
                val properti = Json.decodeFromString<Properti>(jsonResponse.toString())

                // Ambil data jenisProperti, pemilik, dan manajerProperti
                val jenisProperti = jenisPropertyRepository.getbyidJenisProperti(properti.id_jenis.toString())
                val pemilik = pemilikRepository.getbyidPemilik(properti.idPemilik.toString())
                val manajerProperti = manajerPropertyRepository.getbyidManajerProperti(properti.id_manajer.toString())

                // Update state
                detailPropertyUiState = DetailPropertyUiState(
                    detailPropertyUiEvent = properti.toDetailPropertyUiEvent(jenisProperti,pemilik,manajerProperti),
                    jenisProperti = jenisProperti,
                    pemilik = pemilik,
                    manajerProperti = manajerProperti,
                    isLoading = false
                )
            } catch (e: Exception) {
                println("Error: ${e.message}") // Log the error
                detailPropertyUiState = DetailPropertyUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = e.message ?: "Unknown error occurred"
                )
            }
        }
    }
}

data class DetailPropertyUiState(
    val detailPropertyUiEvent: DetailPropertyUiEvent = DetailPropertyUiEvent(),
    val jenisProperti: JenisProperti = JenisProperti(id_jenis = 0, nama_jenis = "", deskripsi_jenis = null),
    val pemilik: Pemilik = Pemilik(idPemilik = 0, nama_pemilik = "", kontak_pemilik = ""),
    val manajerProperti: ManajerProperti = ManajerProperti(id_manajer = 0, nama_manajer = "", kontak_manajer = ""),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
) {
    val isUiEventEmpty: Boolean
        get() = detailPropertyUiEvent == DetailPropertyUiEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailPropertyUiEvent != DetailPropertyUiEvent()
}

data class DetailPropertyUiEvent(
    val idProperti: Int = 0,
    val nama_properti: String = "",
    val deskripsi_properti: String = "",
    val lokasi: String = "",
    val Harga: String = "",
    val statusProperti: String = "",
    val idJenis: Int = 0,
    val idPemilik: Int = 0,
    val idManajer: Int = 0,
    val nama_jenis: String = "",
    val nama_pemilik: String = "",
    val nama_manajer: String = ""
)

fun Properti.toDetailPropertyUiEvent(
    jenisProperti: JenisProperti,
    pemilik: Pemilik,
    manajerProperti: ManajerProperti
): DetailPropertyUiEvent {
    return DetailPropertyUiEvent(
        idProperti = idProperti,
        nama_properti = nama_properti,
        deskripsi_properti = deskripsi_properti,
        lokasi = lokasi,
        Harga = Harga,
        statusProperti = statusProperti,
        idJenis = id_jenis,
        idPemilik = idPemilik,
        idManajer = id_manajer,
        nama_jenis = jenisProperti.nama_jenis,
        nama_pemilik = pemilik.nama_pemilik,
        nama_manajer = manajerProperti.nama_manajer
    )
}

fun DetailPropertyUiEvent.toProperti(): Properti {
    return Properti(
        idProperti = idProperti,
        nama_properti = nama_properti,
        deskripsi_properti = deskripsi_properti,
        lokasi = lokasi,
        Harga = Harga,
        statusProperti = statusProperti,
        id_jenis = idJenis,
        idPemilik = idPemilik,
        id_manajer = idManajer,
    )
}