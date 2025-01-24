package abrayudhistira.cobafinal.ui.pemilik.viewmodel

import abrayudhistira.cobafinal.model.Pemilik
import abrayudhistira.cobafinal.repository.PemilikRepository
import abrayudhistira.cobafinal.ui.navigasi.DestinasiDetailPemilik
import abrayudhistira.cobafinal.ui.navigasi.DestinasiNavigasi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class DetailPemilikViewModel(
    savedStateHandle: SavedStateHandle,
    private val pemilikRepository: PemilikRepository
) : ViewModel() {
    private val id_pemilik: Int = checkNotNull(savedStateHandle[DestinasiDetailPemilik.idPemilikArg])

    var detailPemilikUiState: DetailPemilikUiState by mutableStateOf(DetailPemilikUiState())
        private set

    init {
        getbyidPemilik()
    }

    private fun getbyidPemilik() {
        viewModelScope.launch {
            detailPemilikUiState = DetailPemilikUiState(isLoading = true)
            try {
                val result = pemilikRepository.getbyidPemilik(id_pemilik.toString())
                detailPemilikUiState = DetailPemilikUiState(
                    detailPemilikUiEvent = result.toDetailPemilikUiEvent(),
                    isLoading = false
                )
            } catch (e: Exception) {
                detailPemilikUiState = DetailPemilikUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = e.message ?: "Unknown error occurred"
                )
            }
        }
    }
}

data class DetailPemilikUiState(
    val detailPemilikUiEvent: DetailPemilikUiEvent = DetailPemilikUiEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
){
    val isUiEventEmpty: Boolean
        get() = detailPemilikUiEvent == DetailPemilikUiEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailPemilikUiEvent != DetailPemilikUiEvent()
}

data class DetailPemilikUiEvent(
    val idPemilik: Int = 0,
    val nama_pemilik: String = "",
    val kontak_pemilik: String = ""
)

fun Pemilik.toDetailPemilikUiEvent(): DetailPemilikUiEvent{
    return DetailPemilikUiEvent(
        idPemilik = idPemilik,
        nama_pemilik = nama_pemilik,
        kontak_pemilik = kontak_pemilik,
    )
}
fun DetailPemilikUiEvent.toPemilik(): Pemilik{
    return Pemilik(
        idPemilik = idPemilik,
        nama_pemilik = nama_pemilik,
        kontak_pemilik = kontak_pemilik,
    )
}