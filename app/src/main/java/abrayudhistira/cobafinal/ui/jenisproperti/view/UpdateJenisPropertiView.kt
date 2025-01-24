package abrayudhistira.cobafinal.ui.jenisproperti.view

import abrayudhistira.cobafinal.ui.PenyediaViewModel
import abrayudhistira.cobafinal.ui.jenisproperti.viewmodel.UpdateJenisPropertiViewModel
import abrayudhistira.cobafinal.ui.navigasi.CostumeTopAppBar
import abrayudhistira.cobafinal.ui.navigasi.DestinasiNavigasi
import abrayudhistira.cobafinal.ui.pemilik.view.EntryPemilikBody
import abrayudhistira.cobafinal.ui.pemilik.viewmodel.UpdatePemilikViewModel
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object DestinasiUpdateJenisProperti : DestinasiNavigasi {
    override val route = "update_jenis_property"
    override val titleRes = "Update Property"
    const val idJenisArg = "idJenis"
    val routewithArgument = "$route/{$idJenisArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateJenisPropertiView(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    onNavigate: () -> Unit,
    viewModel: UpdateJenisPropertiViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiUpdateJenisProperti.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = onBack,
            )
        }
    ) { padding ->
        EntryJenisPropertiBody(
            modifier = Modifier.padding(padding),
            jenisPropertiUiState1 = viewModel.updateJenisUiState,
            onJenisPropertiValueChange = viewModel::updateInsertJenisPropertiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateJenisProperti()
                    delay(600) // Wait for update operation to complete
                    withContext(Dispatchers.Main) {
                        onNavigate() // Navigate to the desired destination
                    }
                }
            }
        )
    }
}