package abrayudhistira.cobafinal.ui.pemilik.view

import abrayudhistira.cobafinal.ui.PenyediaViewModel
import abrayudhistira.cobafinal.ui.navigasi.CostumeTopAppBar
import abrayudhistira.cobafinal.ui.navigasi.DestinasiNavigasi
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

object DestinasiUpdatePemilik : DestinasiNavigasi {
    override val route = "update_pemilik"
    override val titleRes = "Update Pemilik"
    const val idPemilikArg = "idPemilik"
    val routewithArgument = "$route/{$idPemilikArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdatePemilikView(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    onNavigate: () -> Unit,
    viewModel: UpdatePemilikViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiUpdatePemilik.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = onBack,
            )
        }
    ) { padding ->
        EntryPemilikBody(
            modifier = Modifier.padding(padding),
            pemilikUiState1 = viewModel.updateUiState,
            onPemilikValueChange = viewModel::updateInsertPemilikState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updatePemilik()
                    delay(600) // Wait for update operation to complete
                    withContext(Dispatchers.Main) {
                        onNavigate() // Navigate to the desired destination
                    }
                }
            }
        )
    }
}