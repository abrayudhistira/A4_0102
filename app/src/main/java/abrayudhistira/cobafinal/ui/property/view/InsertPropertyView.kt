package abrayudhistira.cobafinal.ui.property.view

import abrayudhistira.cobafinal.model.JenisProperti
import abrayudhistira.cobafinal.model.ManajerProperti
import abrayudhistira.cobafinal.model.Pemilik
import androidx.compose.ui.input.nestedscroll.nestedScroll
import abrayudhistira.cobafinal.ui.PenyediaViewModel
import abrayudhistira.cobafinal.ui.navigasi.CostumeTopAppBar
import abrayudhistira.cobafinal.ui.navigasi.DestinasiNavigasi
import abrayudhistira.cobafinal.ui.properti.viewmodel.InsertPropertiViewModel
import abrayudhistira.cobafinal.ui.properti.viewmodel.PropertiUiEvent
import abrayudhistira.cobafinal.ui.properti.viewmodel.PropertiUiState
import abrayudhistira.cobafinal.ui.properti.viewmodel.StatusProperti
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

object DestinasiEntryProperti : DestinasiNavigasi {
    override val route = "entry_properti"
    override val titleRes = "Entry Properti"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertViewProperti(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertPropertiViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    // Fetch dropdown data saat pertama kali di-load
    LaunchedEffect(Unit) {
        viewModel.fetchDropdownData()
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            CostumeTopAppBar(
                title = "Tambah Properti",
                canNavigateBack = true,
                navigateUp = navigateBack
            )
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
                    viewModel.insertProperti()
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
fun EntryPropertiBody(
    uiState: PropertiUiState,
    jenisPropertiList: List<JenisProperti>,
    pemilikList: List<Pemilik>,
    manajerList: List<ManajerProperti>,
    onPropertiValueChange: (PropertiUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        // Form input
        FormInputProperti(
            propertiUiEvent = uiState.propertiUiEvent,
            jenisPropertiList = jenisPropertiList,
            pemilikList = pemilikList,
            manajerList = manajerList,
            onValueChange = onPropertiValueChange,
            modifier = Modifier.fillMaxWidth()
        )

        // Tombol simpan
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            Text(text = "Simpan", style = MaterialTheme.typography.bodyLarge)
        }

        // Tampilkan pesan error jika ada
        if (uiState.error != null) {
            Text(
                text = uiState.error,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun FormInputProperti(
    propertiUiEvent: PropertiUiEvent,
    jenisPropertiList: List<JenisProperti>,
    pemilikList: List<Pemilik>,
    manajerList: List<ManajerProperti>,
    onValueChange: (PropertiUiEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Input fields
        OutlinedTextField(
            value = propertiUiEvent.namaProperti,
            onValueChange = { onValueChange(propertiUiEvent.copy(namaProperti = it)) },
            label = { Text("Nama Properti") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = propertiUiEvent.deskripsiProperti,
            onValueChange = { onValueChange(propertiUiEvent.copy(deskripsiProperti = it)) },
            label = { Text("Deskripsi Properti") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = propertiUiEvent.lokasi,
            onValueChange = { onValueChange(propertiUiEvent.copy(lokasi = it)) },
            label = { Text("Lokasi Properti") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = propertiUiEvent.harga,
            onValueChange = { onValueChange(propertiUiEvent.copy(harga = it)) },
            label = { Text("Harga Properti") },
            modifier = Modifier.fillMaxWidth()
        )

        // Dropdown untuk status properti
        DropdownMenu(
            items = StatusProperti.values().toList(),
            selectedItem = propertiUiEvent.statusProperti,
            onItemSelected = { status -> onValueChange(propertiUiEvent.copy(statusProperti = status)) },
            label = "Status Properti"
        )

        // Dropdown untuk jenis properti
        DropdownMenu(
            items = jenisPropertiList,
            selectedItem = jenisPropertiList.find { it.id_jenis == propertiUiEvent.idJenis },
            onItemSelected = { jenis -> onValueChange(propertiUiEvent.copy(idJenis = jenis.id_jenis)) },
            label = "Jenis Properti"
        )

        // Dropdown untuk pemilik
        DropdownMenu(
            items = pemilikList,
            selectedItem = pemilikList.find { it.idPemilik == propertiUiEvent.idPemilik },
            onItemSelected = { pemilik -> onValueChange(propertiUiEvent.copy(idPemilik = pemilik.idPemilik)) },
            label = "Pemilik"
        )

        // Dropdown untuk manajer
        DropdownMenu(
            items = manajerList,
            selectedItem = manajerList.find { it.id_manajer == propertiUiEvent.idManajer },
            onItemSelected = { manajer -> onValueChange(propertiUiEvent.copy(idManajer = manajer.id_manajer)) },
            label = "Manajer"
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> DropdownMenu(
    items: List<T>,
    selectedItem: T?,
    onItemSelected: (T) -> Unit,
    label: String,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = selectedItem?.toString() ?: "",
            onValueChange = {},
            label = { Text(label) },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(), // Menghubungkan TextField dengan DropdownMenu
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            }
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = { Text(item.toString()) },
                    onClick = {
                        onItemSelected(item)
                        expanded = false
                    }
                )
            }
        }
    }
}