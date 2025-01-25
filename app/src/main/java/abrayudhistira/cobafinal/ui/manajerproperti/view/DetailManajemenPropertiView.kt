package abrayudhistira.cobafinal.ui.manajerproperti.view

import abrayudhistira.cobafinal.model.ManajerProperti
import abrayudhistira.cobafinal.model.Pemilik
import abrayudhistira.cobafinal.ui.PenyediaViewModel
import abrayudhistira.cobafinal.ui.manajerproperti.viewmodel.DetailManajemenPropertiUiState
import abrayudhistira.cobafinal.ui.manajerproperti.viewmodel.DetailManajemenPropertiViewModel
import abrayudhistira.cobafinal.ui.manajerproperti.viewmodel.toManajemenProperti
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

object DestinasiDetailManajemenProperti : DestinasiNavigasi {
    override val route = "detail_manajemen_properti"
    override val titleRes = "Detail Manajemen Properti"
    const val iddManajerArg = "idManajer"
    val routewithArgument = "$route/{$iddManajerArg}"
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailManajemenPropertiView(
    navigateBack: () -> Unit,
    navigateToEdit: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailManajemenPropertiViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    println("Detail Manajeme Properti : ViewModel initialized")
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiDetailManajemenProperti.titleRes,
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
        BodyDetailManajemenProperti(
            detailManajemenPropertiUiState = viewModel.detailManajemenPropertiUiState,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BodyDetailManajemenProperti(
    detailManajemenPropertiUiState: DetailManajemenPropertiUiState,
    modifier: Modifier = Modifier
) {
    println("BodyDetailManajemenProperti: UI State - $detailManajemenPropertiUiState")
    when {
        detailManajemenPropertiUiState.isLoading -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        detailManajemenPropertiUiState.isError -> {
            println("BodyDetailManajemenProperti: Error - ${detailManajemenPropertiUiState.errorMessage}")
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = detailManajemenPropertiUiState.errorMessage,
                    color = Color.Red
                )
            }
        }
        detailManajemenPropertiUiState.isUiEventNotEmpty -> {
            println("BodyDetailManajemenProperti: Data loaded successfully")
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                ItemDetailManajemenProperti(
                    manajemenProperti = detailManajemenPropertiUiState.detailManajemenPropertiUiEvent.toManajemenProperti(),
                    modifier = modifier
                )
            }
        }
    }
}

@Composable
fun ItemDetailManajemenProperti(
    modifier: Modifier = Modifier,
    manajemenProperti: ManajerProperti
) {
    println("ItemDetailManajemenProperti: Displaying data for ManajemenProperti - $manajemenProperti")
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
            ComponentDetailManajemenProperti(judul = "ID Manajer", isinya = manajemenProperti.id_manajer.toString())
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailManajemenProperti(judul = "Nama Manajer", isinya = manajemenProperti.nama_manajer)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailManajemenProperti(judul = "Kontak Pemilik", isinya = manajemenProperti.kontak_manajer)
        }
    }
}

@Composable
fun ComponentDetailManajemenProperti(
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String
) {
    println("ComponentDetailManajemenPropeti: Displaying $judul - $isinya")
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