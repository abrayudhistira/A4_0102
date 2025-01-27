package abrayudhistira.cobafinal.ui.jenisproperti.viewmodel

import abrayudhistira.cobafinal.model.JenisProperti
import abrayudhistira.cobafinal.model.Pemilik
import abrayudhistira.cobafinal.repository.JenisPropertyRepository
import abrayudhistira.cobafinal.repository.PemilikRepository
import abrayudhistira.cobafinal.ui.navigasi.DestinasiDetailJenisProperti
import abrayudhistira.cobafinal.ui.navigasi.DestinasiDetailPemilik
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class DetailJenisPropertiViewModel(
    savedStateHandle: SavedStateHandle,
    private val jenisPropertyRepository: JenisPropertyRepository
) : ViewModel() {
    private val idJenisProperti: Int = checkNotNull(savedStateHandle[DestinasiDetailJenisProperti.idJenisArg])

    var detailJenisUiState: DetailJenisUiState by mutableStateOf(DetailJenisUiState())
        private set

    init {
        getbyidJenisProperti()
    }

    private fun getbyidJenisProperti() {
        viewModelScope.launch {
            detailJenisUiState = DetailJenisUiState(isLoading = true)
            try {
                val result = jenisPropertyRepository.getbyidJenisProperti(idJenisProperti.toString())
                detailJenisUiState = DetailJenisUiState(
                    detailJenisUiEvent = result.toDetailJenisUiEvent(),
                    isLoading = false
                )
            } catch (e: Exception) {
                detailJenisUiState = DetailJenisUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = e.message ?: "Unknown error occurred"
                )
            }
        }
    }
}

data class DetailJenisUiState(
    val detailJenisUiEvent: DetailJenisUiEvent = DetailJenisUiEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
){
    val isUiEventEmpty: Boolean
        get() = detailJenisUiEvent == DetailJenisUiEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailJenisUiEvent != DetailJenisUiEvent()
}

data class DetailJenisUiEvent(
    val id_jenis: Int = 0,
    val nama_jenis: String = "",
    val deskripsi_jenis: String? = ""
)

fun JenisProperti.toDetailJenisUiEvent(): DetailJenisUiEvent{
    return DetailJenisUiEvent(
        id_jenis = id_jenis,
        nama_jenis = nama_jenis,
        deskripsi_jenis = deskripsi_jenis,
    )
}
fun DetailJenisUiEvent.toJenisProperti(): JenisProperti {
    return JenisProperti(
        id_jenis = id_jenis,
        nama_jenis = nama_jenis,
        deskripsi_jenis = deskripsi_jenis,
    )
}