package abrayudhistira.cobafinal.ui.property.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import abrayudhistira.cobafinal.model.JenisProperti
import abrayudhistira.cobafinal.ui.navigasi.CostumeTopAppBar
import abrayudhistira.cobafinal.ui.navigasi.DestinasiNavigasi
import androidx.compose.ui.text.font.FontWeight


object DestinasiJenisPropertiinProperti : DestinasiNavigasi {
    override val route = "jenis_properti_in_properti"
    override val titleRes = "Jenis Properti In Properti"
    const val idPropertiArg = "idProperti"
    val routewithArgument = "$route/{$idPropertiArg}"
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JenisPropertiInPropertiView(
    jenisPropertiList: List<JenisProperti>, // Daftar jenis properti
    navController: NavController // NavController untuk navigasi
) {
    Scaffold(
        topBar = {
            CostumeTopAppBar(
                title = "Jenis Properti",
                canNavigateBack = true,
                navigateUp = { navController.popBackStack() }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            items(jenisPropertiList) { jenis ->
                JenisPropertiInPropertiItem(
                    jenisProperti = jenis,
                    onItemClick = {
                        // Navigasi ke detail jenis properti
                        navController.navigate("detail_jenis_properti/${jenis.id_jenis}")
                    }
                )
            }
        }
    }
}

@Composable
fun JenisPropertiInPropertiItem(
    jenisProperti: JenisProperti,
    onItemClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = onItemClick
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = jenisProperti.nama_jenis,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = jenisProperti.deskripsi_jenis ?: "Tidak ada deskripsi",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}