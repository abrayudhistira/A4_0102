package abrayudhistira.cobafinal.ui.jenisproperti.view

import abrayudhistira.cobafinal.model.JenisProperti
import abrayudhistira.cobafinal.model.Pemilik
import abrayudhistira.cobafinal.ui.PenyediaViewModel
import abrayudhistira.cobafinal.ui.jenisproperti.viewmodel.DetailJenisPropertiViewModel
import abrayudhistira.cobafinal.ui.jenisproperti.viewmodel.DetailJenisUiState
import abrayudhistira.cobafinal.ui.jenisproperti.viewmodel.toJenisProperti
import abrayudhistira.cobafinal.ui.navigasi.CostumeTopAppBar
import abrayudhistira.cobafinal.ui.navigasi.DestinasiDetailPemilik
import abrayudhistira.cobafinal.ui.navigasi.DestinasiNavigasi
import abrayudhistira.cobafinal.ui.pemilik.viewmodel.DetailPemilikUiState
import abrayudhistira.cobafinal.ui.pemilik.viewmodel.DetailPemilikViewModel
import abrayudhistira.cobafinal.ui.pemilik.viewmodel.toPemilik
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

object DestinasiDetailJenisProperti : DestinasiNavigasi {
    override val route = "detail_jenis_properti"
    override val titleRes = "Detail Jenis Property"
    const val idJenisArg = "idJenis"
    val routewithArgument = "$route/{$idJenisArg}"
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailJenisPropertiView(
    navigateBack: () -> Unit,
    navigateToEdit: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailJenisPropertiViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    println("DetailJenisPropertiView: ViewModel initialized")
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiDetailJenisProperti.titleRes,
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
        BodyDetailJenisProperti(
            detailJenisUiState = viewModel.detailJenisUiState,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BodyDetailJenisProperti(
    detailJenisUiState: DetailJenisUiState,
    modifier: Modifier = Modifier
) {
    println("BodyDetailPemilik: UI State - $detailJenisUiState")
    when {
        detailJenisUiState.isLoading -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        detailJenisUiState.isError -> {
            println("BodyDetailPemilik: Error - ${detailJenisUiState.errorMessage}")
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = detailJenisUiState.errorMessage,
                    color = Color.Red
                )
            }
        }
        detailJenisUiState.isUiEventNotEmpty -> {
            println("BodyDetailPemilik: Data loaded successfully")
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                ItemDetailJenisProperti(
                    jenisProperti = detailJenisUiState.detailJenisUiEvent.toJenisProperti(),
                    modifier = modifier
                )
            }
        }
    }
}

@Composable
fun ItemDetailJenisProperti(
    modifier: Modifier = Modifier,
    jenisProperti: JenisProperti
) {
    println("ItemDetailJenisProperti: Displaying data for Jenis Pemilik - $jenisProperti")
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
            ComponentDetailJenisProperti(judul = "ID Jenis Property", isinya = jenisProperti.idJenis.toString())
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailJenisProperti(judul = "Nama Jenis Property", isinya = jenisProperti.nama_jenis)
            Spacer(modifier = Modifier.padding(4.dp))
            jenisProperti.deskripsi_jenis?.let {
                ComponentDetailJenisProperti(
                    judul = "Deskripsi Jenis",
                    isinya = it
                )
            }
        }
    }
}

@Composable
fun ComponentDetailJenisProperti(
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String
) {
    println("ComponentDetailJenisProperti: Displaying $judul - $isinya")
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