package abrayudhistira.cobafinal.ui.manajerproperti.view

import abrayudhistira.cobafinal.ui.PenyediaViewModel
import abrayudhistira.cobafinal.ui.manajerproperti.viewmodel.InsertManajemenPopertiViewModel
import abrayudhistira.cobafinal.ui.manajerproperti.viewmodel.ManajemenPropertiUiEvent
import abrayudhistira.cobafinal.ui.manajerproperti.viewmodel.ManajemenPropertiUiState1
import abrayudhistira.cobafinal.ui.navigasi.CostumeTopAppBar
import abrayudhistira.cobafinal.ui.navigasi.DestinasiNavigasi
import abrayudhistira.cobafinal.ui.pemilik.viewmodel.InsertPemilikViewModel
import abrayudhistira.cobafinal.ui.pemilik.viewmodel.PemilikUiEvent
import abrayudhistira.cobafinal.ui.pemilik.viewmodel.PemilikUiState1
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

object DestinasiEntryManajemenProperti : DestinasiNavigasi {
    override val route = "entry_manajemen_properti"
    override val titleRes = "Entry Manajemen Properti"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertViewManajemenProperti(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertManajemenPopertiViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiEntryManajemenProperti.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        EntryPemilikBody(
            manajemenPropertiUiState1 = viewModel.uiState,
            onManajemenPropertiValueChange = {event -> viewModel.updateInsertManajemenPropertiState(event) },
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertmanajemenProperti()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun EntryPemilikBody(
    manajemenPropertiUiState1: ManajemenPropertiUiState1,
    onManajemenPropertiValueChange: (ManajemenPropertiUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInputManajemenProperti(
            manajemenPropertiUiEvent = manajemenPropertiUiState1.manajemenPropertiUiEvent,
            onValueChange = onManajemenPropertiValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            Text(text = "Simpan", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInputManajemenProperti(
    manajemenPropertiUiEvent: ManajemenPropertiUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (ManajemenPropertiUiEvent) -> Unit,
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = manajemenPropertiUiEvent.nama_manajer,
            onValueChange = { onValueChange(manajemenPropertiUiEvent.copy(nama_manajer = it)) },
            label = { Text("Nama Manajer Properti") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true,
            shape = MaterialTheme.shapes.medium,
            colors = MaterialTheme.colorScheme.run {
                TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = primary,
                    unfocusedBorderColor = onSurfaceVariant.copy(alpha = 0.5f)
                )
            }
        )
        OutlinedTextField(
            value = manajemenPropertiUiEvent.kontak_manajer,
            onValueChange = { onValueChange(manajemenPropertiUiEvent.copy(kontak_manajer = it)) },
            label = { Text("Nomor Kontak / HP") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true,
            shape = MaterialTheme.shapes.medium,
            colors = MaterialTheme.colorScheme.run {
                TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = primary,
                    unfocusedBorderColor = onSurfaceVariant.copy(alpha = 0.5f)
                )
            }
        )

        // Instructions Text
        if (enabled) {
            Text(
                text = "Isi Semua Data!",
                style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.secondary),
                modifier = Modifier.padding(12.dp)
            )
        }

        // Divider with softer color
        Divider(
            thickness = 1.dp,
            modifier = Modifier.padding(12.dp),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
        )
    }
}