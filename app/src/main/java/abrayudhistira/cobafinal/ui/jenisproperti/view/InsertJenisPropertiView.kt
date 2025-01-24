package abrayudhistira.cobafinal.ui.jenisproperti.view

import abrayudhistira.cobafinal.ui.PenyediaViewModel
import abrayudhistira.cobafinal.ui.jenisproperti.viewmodel.InsertJenisPopertiViewModel
import abrayudhistira.cobafinal.ui.jenisproperti.viewmodel.JenisPropertiUiEvent
import abrayudhistira.cobafinal.ui.jenisproperti.viewmodel.JenisPropertiUiState1
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

object DestinasiInsertJenisProperty : DestinasiNavigasi {
    override val route = "insert_jenis_property"
    override val titleRes = "Insert Jenis Property"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertViewJenisProperty(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertJenisPopertiViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiInsertJenisProperty.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        EntryJenisPropertiBody(
            jenisPropertiUiState1 = viewModel.uiState,
            onJenisPropertiValueChange = {event -> viewModel.updateInsertJenisPropertiState(event) },
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertjenisPemilik()
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
fun EntryJenisPropertiBody(
    jenisPropertiUiState1: JenisPropertiUiState1,
    onJenisPropertiValueChange: (JenisPropertiUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInputPemilik(
            jenisPropertiUiEvent = jenisPropertiUiState1.jenisPropertiUiEvent,
            onValueChange = onJenisPropertiValueChange,
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
    jenisPropertiUiEvent: JenisPropertiUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (JenisPropertiUiEvent) -> Unit,
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Nama Pemilik TextField
        OutlinedTextField(
            value = jenisPropertiUiEvent.nama_jenis,
            onValueChange = { onValueChange(jenisPropertiUiEvent.copy(nama_jenis = it)) },
            label = { Text("Nama Jenis Property") },
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
        jenisPropertiUiEvent.deskripsi_jenis?.let {
            OutlinedTextField(
                value = it,
                onValueChange = { onValueChange(jenisPropertiUiEvent.copy(deskripsi_jenis = it)) },
                label = { Text("Deskripsi Jenis") },
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
        }

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