package abrayudhistira.cobafinal.ui.property.view

import abrayudhistira.cobafinal.ui.PenyediaViewModel
import abrayudhistira.cobafinal.ui.navigasi.CostumeTopAppBar
import abrayudhistira.cobafinal.ui.navigasi.DestinasiNavigasi
import abrayudhistira.cobafinal.ui.property.viewmodel.UpdatePropertiViewModel
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object DestinasiUpdateProperti : DestinasiNavigasi {
    override val route = "update_properti"
    override val titleRes = "Update Properti"
    const val idPropertiArg = "idProperti"
    val routewithArgument = "$route/{$idPropertiArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdatePropertiView(
    onBack: () -> Unit, // Navigasi kembali ke halaman sebelumnya
    onNavigate: () -> Unit, // Navigasi ke halaman lain setelah update
    modifier: Modifier = Modifier,
    viewModel: UpdatePropertiViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    // Fetch data saat pertama kali di-load
    LaunchedEffect(Unit) {
        viewModel.fetchData()
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            CostumeTopAppBar(
                title = "Edit Properti",
                canNavigateBack = true,
                navigateUp = onBack // Gunakan onBack untuk navigasi kembali
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    coroutineScope.launch {
                        viewModel.updateProperti()
                        delay(600) // Tunggu proses update selesai
                        withContext(Dispatchers.Main) {
                            onNavigate() // Navigasi ke halaman lain setelah update
                        }
                    }
                },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Update Properti"
                )
            }
        }
    ) { innerPadding ->
        EntryPropertiBody(
            uiState = viewModel.uiState,
            jenisPropertiList = viewModel.jenisPropertiList,
            pemilikList = viewModel.pemilikList,
            manajerList = viewModel.manajerList,
            onPropertiValueChange = { event -> viewModel.updatePropertiState(event) },
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateProperti()
                    delay(600) // Tunggu proses update selesai
                    withContext(Dispatchers.Main) {
                        onNavigate() // Navigasi ke halaman lain setelah update
                    }
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}