package abrayudhistira.cobafinal.ui.pemilik.view

import abrayudhistira.cobafinal.ui.PenyediaViewModel
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
import androidx.compose.material3.AlertDialog
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

object DestinasiEntryPemilik : DestinasiNavigasi {
    override val route = "entry_pemilik"
    override val titleRes = "Entry Pemilik"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertViewPemilik(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertPemilikViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    if (viewModel.uiState.error != null) {
        AlertDialog(
            onDismissRequest = { /* Handle dismiss */ },
            title = { Text("Error") },
            text = { Text(viewModel.uiState.error!!) },
            confirmButton = {
                Button(
                    onClick = { viewModel.uiState = PemilikUiState1() } // Reset error state
                ) {
                    Text("OK")
                }
            }
        )
    }
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiEntryPemilik.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        EntryPemilikBody(
            pemilikUiState1 = viewModel.uiState,
            onPemilikValueChange = {event -> viewModel.updateInsertPemilikState(event) },
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertpemilik()
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
    pemilikUiState1: PemilikUiState1,
    onPemilikValueChange: (PemilikUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInputPemilik(
            pemilikUiEvent = pemilikUiState1.pemilikUiEvent,
            onValueChange = onPemilikValueChange,
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
fun FormInputPemilik(
    pemilikUiEvent: PemilikUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (PemilikUiEvent) -> Unit,
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Nama Pemilik TextField
        OutlinedTextField(
            value = pemilikUiEvent.nama_pemilik,
            onValueChange = { onValueChange(pemilikUiEvent.copy(nama_pemilik = it)) },
            label = { Text("Nama Pemilik") },
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

        // Kontak Pemilik TextField
        OutlinedTextField(
            value = pemilikUiEvent.kontak_pemilik,
            onValueChange = { onValueChange(pemilikUiEvent.copy(kontak_pemilik = it)) },
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