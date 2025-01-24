package abrayudhistira.cobafinal.ui.pemilik.view

import abrayudhistira.cobafinal.model.Pemilik
import abrayudhistira.cobafinal.ui.PenyediaViewModel
import abrayudhistira.cobafinal.ui.navigasi.CostumeTopAppBar
import abrayudhistira.cobafinal.ui.navigasi.DestinasiDetailPemilik
import abrayudhistira.cobafinal.ui.navigasi.DestinasiNavigasi
import abrayudhistira.cobafinal.ui.pemilik.viewmodel.DetailPemilikUiState
import abrayudhistira.cobafinal.ui.pemilik.viewmodel.DetailPemilikViewModel
import abrayudhistira.cobafinal.ui.pemilik.viewmodel.toPemilik
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

object DestinasiDetailPemilik : DestinasiNavigasi {
    override val route = "detail_pemilik"
    override val titleRes = "Detail Pemilik"
    const val idPemilikArg = "idPemilik"
    val routewithArgument = "$route/{$idPemilikArg}"
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPemilikView(
    navigateBack: () -> Unit,
    navigateToEdit: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailPemilikViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    println("DetailPemilikView: ViewModel initialized")
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiDetailPemilik.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToEdit,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Pemilik"
                )
            }
        }
    ) { innerPadding ->
        BodyDetailPemilik(
            detailPemilikUiState = viewModel.detailPemilikUiState,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BodyDetailPemilik(
    detailPemilikUiState: DetailPemilikUiState,
    modifier: Modifier = Modifier
) {
    println("BodyDetailPemilik: UI State - $detailPemilikUiState")
    when {
        detailPemilikUiState.isLoading -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        detailPemilikUiState.isError -> {
            println("BodyDetailPemilik: Error - ${detailPemilikUiState.errorMessage}")
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = detailPemilikUiState.errorMessage,
                    color = Color.Red
                )
            }
        }
        detailPemilikUiState.isUiEventNotEmpty -> {
            println("BodyDetailPemilik: Data loaded successfully")
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                ItemDetailPemilik(
                    pemilik = detailPemilikUiState.detailPemilikUiEvent.toPemilik(),
                    modifier = modifier
                )
            }
        }
    }
}

@Composable
fun ItemDetailPemilik(
    modifier: Modifier = Modifier,
    pemilik: Pemilik
) {
    println("ItemDetailPemilik: Displaying data for pemilik - $pemilik")
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            ComponentDetailPemilik(judul = "ID Pemilik", isinya = pemilik.idPemilik.toString())
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailPemilik(judul = "Nama Pemilik", isinya = pemilik.nama_pemilik)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailPemilik(judul = "Kontak Pemilik", isinya = pemilik.kontak_pemilik)
        }
    }
}

@Composable
fun ComponentDetailPemilik(
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String
) {
    println("ComponentDetailPemilik: Displaying $judul - $isinya")
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "$judul : ",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Text(
            text = isinya,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}