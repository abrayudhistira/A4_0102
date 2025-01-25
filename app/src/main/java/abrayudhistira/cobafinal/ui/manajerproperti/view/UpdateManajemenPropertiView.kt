package abrayudhistira.cobafinal.ui.manajerproperti.view

import abrayudhistira.cobafinal.ui.PenyediaViewModel
import abrayudhistira.cobafinal.ui.manajerproperti.viewmodel.UpdateManajemenPropertiViewModel
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

object DestinasiUpdateManajemenProperti : DestinasiNavigasi {
    override val route = "update_manajemen_properti"
    override val titleRes = "Update Manajemen Properti"
    const val idManajerArg = "idManajer"
    val routewithArgument = "$route/{$idManajerArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateManajemenPropertiView(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    onNavigate: () -> Unit,
    viewModel: UpdateManajemenPropertiViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiUpdateManajemenProperti.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = onBack,
            )
        }
    ) { padding ->
        EntryPemilikBody(
            modifier = Modifier.padding(padding),
            manajemenPropertiUiState1 = viewModel.updateUiState,
            onManajemenPropertiValueChange = viewModel::updateInsertManajemenPropertiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateManajemenProperti()
                    delay(600) // Wait for update operation to complete
                    withContext(Dispatchers.Main) {
                        onNavigate() // Navigate to the desired destination
                    }
                }
            }
        )
    }
}