package abrayudhistira.cobafinal.ui.manajerproperti.viewmodel

import abrayudhistira.cobafinal.model.ManajerProperti
import abrayudhistira.cobafinal.model.Pemilik
import abrayudhistira.cobafinal.repository.ManajerPropertyRepository
import abrayudhistira.cobafinal.repository.PemilikRepository
import abrayudhistira.cobafinal.ui.navigasi.DestinasiDetailManajemenProperti
import abrayudhistira.cobafinal.ui.navigasi.DestinasiDetailPemilik
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class DetailManajemenPropertiViewModel(
    savedStateHandle: SavedStateHandle,
    private val manajerPropertyRepository: ManajerPropertyRepository
) : ViewModel() {
    private val idManajer: Int = checkNotNull(savedStateHandle[DestinasiDetailManajemenProperti.idManajerArg])

    var detailManajemenPropertiUiState: DetailManajemenPropertiUiState by mutableStateOf(DetailManajemenPropertiUiState())
        private set

    init {
        getbyidManajemenProperti()
    }

    private fun getbyidManajemenProperti() {
        viewModelScope.launch {
            detailManajemenPropertiUiState = DetailManajemenPropertiUiState(isLoading = true)
            try {
                val result = manajerPropertyRepository.getbyidManajerProperti(idManajer.toString())
                detailManajemenPropertiUiState = DetailManajemenPropertiUiState(
                    detailManajemenPropertiUiEvent = result.toDetailManajemenPropertiUiEvent(),
                    isLoading = false
                )
            } catch (e: Exception) {
                detailManajemenPropertiUiState = DetailManajemenPropertiUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = e.message ?: "Unknown error occurred"
                )
            }
        }
    }
}

data class DetailManajemenPropertiUiState(
    val detailManajemenPropertiUiEvent: DetailManajemenPropertiUiEvent = DetailManajemenPropertiUiEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
){
    val isUiEventEmpty: Boolean
        get() = detailManajemenPropertiUiEvent == DetailManajemenPropertiUiEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailManajemenPropertiUiEvent != DetailManajemenPropertiUiEvent()
}

data class DetailManajemenPropertiUiEvent(
    val id_manajer: Int = 0,
    val nama_manajer: String = "",
    val kontak_manajer: String = ""
)

fun ManajerProperti.toDetailManajemenPropertiUiEvent(): DetailManajemenPropertiUiEvent{
    return DetailManajemenPropertiUiEvent(
        id_manajer = id_manajer,
        nama_manajer = nama_manajer,
        kontak_manajer = kontak_manajer,
    )
}
fun DetailManajemenPropertiUiEvent.toManajemenProperti(): ManajerProperti {
    return ManajerProperti(
        id_manajer = id_manajer,
        nama_manajer = nama_manajer,
        kontak_manajer = kontak_manajer,
    )
}