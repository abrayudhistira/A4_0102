package abrayudhistira.cobafinal.ui.property.view

import abrayudhistira.cobafinal.model.JenisProperti
import abrayudhistira.cobafinal.model.ManajerProperti
import abrayudhistira.cobafinal.model.Pemilik
import abrayudhistira.cobafinal.model.Properti
import abrayudhistira.cobafinal.ui.PenyediaViewModel
import abrayudhistira.cobafinal.ui.navigasi.CostumeTopAppBar
import abrayudhistira.cobafinal.ui.navigasi.DestinasiNavigasi
import abrayudhistira.cobafinal.ui.property.viewmodel.DetailPropertyUiState
import abrayudhistira.cobafinal.ui.property.viewmodel.DetailPropertyViewModel
import abrayudhistira.cobafinal.ui.property.viewmodel.toProperti
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.List
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

object DestinasiDetailProperti : DestinasiNavigasi {
    override val route = "detail_properti"
    override val titleRes = "Detail Properti"
    const val idPropertiArg = "idProperti"
    val routeWithArgument = "$route/{$idPropertiArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPropertyView(
    navigateBack: () -> Unit,
    navigateToEdit: () -> Unit,
    navigateToJenisProperti: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailPropertyViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    println("DetailPropertyView: ViewModel initialized")
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(connection = scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiDetailProperti.titleRes,
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
                    contentDescription = "Edit Properti"
                )
            }
//            // Tombol untuk melihat jenis properti
//            FloatingActionButton(
//                onClick = navigateToJenisProperti, // Navigasi ke halaman jenis properti
//                shape = MaterialTheme.shapes.medium,
//                modifier = Modifier.padding(8.dp)
//            ) {
//                Icon(
//                    imageVector = Icons.Default.List,
//                    contentDescription = "Lihat Jenis Properti"
//                )
//            }
        }
    ) { innerPadding ->
        BodyDetailProperty(
            detailPropertyUiState = viewModel.detailPropertyUiState,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BodyDetailProperty(
    detailPropertyUiState: DetailPropertyUiState,
    modifier: Modifier = Modifier
) {
    println("BodyDetailProperty: UI State - $detailPropertyUiState")
    when {
        detailPropertyUiState.isLoading -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        detailPropertyUiState.isError -> {
            println("BodyDetailProperty: Error - ${detailPropertyUiState.errorMessage}")
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = detailPropertyUiState.errorMessage,
                    color = Color.Red
                )
            }
        }
        detailPropertyUiState.isUiEventNotEmpty -> {
            println("BodyDetailProperty: Data loaded successfully")
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                // Ambil data jenisProperti, pemilik, dan manajerProperti dari state
                val jenisProperti = detailPropertyUiState.jenisProperti
                val pemilik = detailPropertyUiState.pemilik
                val manajerProperti = detailPropertyUiState.manajerProperti

                // Panggil ItemDetailProperty dengan semua parameter yang diperlukan
                ItemDetailProperty(
                    properti = detailPropertyUiState.detailPropertyUiEvent.toProperti(),
                    jenisProperti = detailPropertyUiState.jenisProperti,
                    pemilik = detailPropertyUiState.pemilik,
                    manajerProperti = detailPropertyUiState.manajerProperti,
                    modifier = modifier
                )
            }
        }
    }
}

@Composable
fun ItemDetailProperty(
    modifier: Modifier = Modifier,
    properti: Properti,
    jenisProperti: JenisProperti,
    pemilik: Pemilik,
    manajerProperti: ManajerProperti
) {
    println("ItemDetailProperty: Displaying data for properti - $properti")
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
            ComponentDetailProperty(judul = "ID Properti", isinya = properti.idProperti.toString())
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailProperty(judul = "Nama Properti", isinya = properti.nama_properti)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailProperty(judul = "Deskripsi", isinya = properti.deskripsi_properti)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailProperty(judul = "Lokasi", isinya = properti.lokasi)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailProperty(judul = "Harga", isinya = properti.Harga)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailProperty(judul = "Status", isinya = properti.statusProperti)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailProperty(judul = "Jenis Properti", isinya = jenisProperti.nama_jenis)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailProperty(judul = "Pemilik", isinya = pemilik.nama_pemilik)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailProperty(judul = "Manajer", isinya = manajerProperti.nama_manajer)
        }
    }
}

@Composable
fun ComponentDetailProperty(
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String
) {
    println("ComponentDetailProperty: Displaying $judul - $isinya")
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "$judul : ",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = isinya,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}