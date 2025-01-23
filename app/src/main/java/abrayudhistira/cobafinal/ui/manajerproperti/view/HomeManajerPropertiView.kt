package abrayudhistira.cobafinal.ui.manajerproperti.view

import abrayudhistira.cobafinal.ui.property.HomePropertyViewModel
import abrayudhistira.cobafinal.ui.property.HomeUiState

import abrayudhistira.cobafinal.R
import abrayudhistira.cobafinal.model.JenisProperti
import abrayudhistira.cobafinal.model.ManajerProperti
import abrayudhistira.cobafinal.model.Properti
import abrayudhistira.cobafinal.ui.PenyediaViewModel
import abrayudhistira.cobafinal.ui.jenisproperti.viewmodel.HomeJenisPropertiUiState
import abrayudhistira.cobafinal.ui.jenisproperti.viewmodel.HomeJenisPropertiViewModel
import abrayudhistira.cobafinal.ui.manajerproperti.viewmodel.HomeManajerPropertiUiState
import abrayudhistira.cobafinal.ui.manajerproperti.viewmodel.HomeManajerPropertiViewModel
import abrayudhistira.cobafinal.ui.navigasi.CostumeTopAppBar
import abrayudhistira.cobafinal.ui.navigasi.DestinasiNavigasi
import android.content.Context
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeManajerPropertiView(
    navigateToItemEntry:()->Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit ={},
    navController: NavController,
    onBackClick: () -> Unit = {},
    viewModel: HomeManajerPropertiViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text(
                            text = "Daftar Manajer Property",
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                            color = Color.Black
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick ={navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
//                    IconButton(onClick ={viewModel.getManajerProperti() }) {
//                        Icon(imageVector = Icons.Default.Refresh, contentDescription = "Refresh")
//                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Manajer Properti")
            }
        },
    ) {innerPadding ->
        ManajerPropertyStatus(
            homeManajerPropertiUiState = viewModel.homemanajerPropertiUiState,
            retryAction = {viewModel.getManajerProperti() },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,
            onDeleteClick = {
                //viewModel.delete
                viewModel.getManajerProperti()
            }
        )
    }
}

@Composable
fun ManajerPropertyStatus(
    homeManajerPropertiUiState: HomeManajerPropertiUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (ManajerProperti) -> Unit = {},
    onDetailClick: (String) -> Unit
){
    when (homeManajerPropertiUiState){
        is HomeManajerPropertiUiState.Loading-> OnLoading(modifier = modifier.fillMaxSize())


        is HomeManajerPropertiUiState.Success ->
            if(homeManajerPropertiUiState.manajerProperti.isEmpty()){
                return Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    Text(text = "Tidak ada data", style = MaterialTheme.typography.bodyMedium)
                }
            }else{
                ManajerPropertyLayout(
                    manajerProperti = homeManajerPropertiUiState.manajerProperti,
                    modifier = modifier.fillMaxWidth(),
                    onDetailClick = {
                        onDetailClick(it.id_manajer.toString())
                    },
                    onDeleteClick={
                        onDeleteClick(it)
                    }
                )
            }
        is HomeManajerPropertiUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun OnLoading(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(R.drawable.no_wifi),
            contentDescription = stringResource(R.string.loading)
        )
    }
}

@Composable
fun OnError(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(painter = painterResource(id = R.drawable.no_wifi), contentDescription = "")
            Text(text = stringResource(R.string.loading_failed), style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(16.dp))
            Button(onClick = retryAction) {
                Text(stringResource(R.string.retry))
            }
        }
    }
}

@Composable
fun ManajerPropertyLayout(
    manajerProperti: List<ManajerProperti>,
    modifier: Modifier = Modifier,
    onDetailClick: (ManajerProperti) -> Unit,
    onDeleteClick: (ManajerProperti) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(manajerProperti) { manajerProperti ->
            ManajerPropertyCard(
                manajerProperti = manajerProperti,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(manajerProperti) },
                onDeleteClick = { onDeleteClick(manajerProperti) }
            )
        }
    }
}

@Composable
fun ManajerPropertyCard(
    manajerProperti: ManajerProperti,
    modifier: Modifier = Modifier,
    onDeleteClick: (ManajerProperti) -> Unit = {},
    onEditClick: (ManajerProperti) -> Unit = {}
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = manajerProperti.nama_manajer,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.weight(1f))

                // Delete Button
                IconButton(onClick = { onDeleteClick(manajerProperti) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Jenis Property",
                        tint = MaterialTheme.colorScheme.error
                    )
                }

                // Edit Button
                IconButton(onClick = { onEditClick(manajerProperti) }) {
                    Icon(
                        imageVector = Icons.Default.Edit, // You can use a built-in edit icon or a custom one
                        contentDescription = "Edit Property",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Divider()

            Column {
                Text(
                    text = "Deskripsi: ${manajerProperti.kontak_manajer}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}
