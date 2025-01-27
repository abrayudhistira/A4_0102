package abrayudhistira.cobafinal.ui.pemilik.view

import abrayudhistira.cobafinal.R
import abrayudhistira.cobafinal.model.Pemilik
import abrayudhistira.cobafinal.ui.PenyediaViewModel
import abrayudhistira.cobafinal.ui.pemilik.viewmodel.HomePemilikUiState
import abrayudhistira.cobafinal.ui.pemilik.viewmodel.HomePemilikViewModel
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePemilikView(
    navigateToItemEntry:()->Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit ={},
    navController: NavController,
    onBackClick: () -> Unit = {},
    viewModel: HomePemilikViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text(
                            text = "Daftar Pemilik",
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick ={navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                    Spacer(modifier = Modifier.padding(5.dp))
                    IconButton(onClick ={viewModel.getPemilik() }) {
                        Icon(imageVector = Icons.Default.Refresh, contentDescription = "Refresh")
                    }
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
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Pemilik")
            }
        },
    ) {innerPadding ->
        PemilikStatus(
            pemilikUiState = viewModel.homePemilikUiState,
            retryAction = {viewModel.getPemilik() },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,
            onDeleteClick = {
                viewModel.deletePemilik(it.idPemilik)
                viewModel.getPemilik()
            }
        )
    }
}

@Composable
fun PemilikStatus(
    pemilikUiState: HomePemilikUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Pemilik) -> Unit = {},
    onDetailClick: (String) -> Unit
){
    when (pemilikUiState){
        is HomePemilikUiState.Loading-> OnLoading(modifier = modifier.fillMaxSize())


        is HomePemilikUiState.Success ->
            if(pemilikUiState.pemilik.isEmpty()){
                return Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    Text(text = "Tidak ada data", style = MaterialTheme.typography.bodyMedium)
                }
            }else{
                PemilikLayout(
                    pemilik = pemilikUiState.pemilik,
                    modifier = modifier.fillMaxWidth(),
                    onDetailClick = { pemilik ->
                        println("PemilikLayout: onDetailClick called for ID : ${pemilik.idPemilik}")
                        onDetailClick(pemilik.idPemilik.toString())
                    },
                    onDeleteClick={
                        onDeleteClick(it)
                    }
                )
            }
        is HomePemilikUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
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
fun PemilikLayout(
    pemilik: List<Pemilik>,
    modifier: Modifier = Modifier,
    onDetailClick: (Pemilik) -> Unit,
    onDeleteClick: (Pemilik) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(pemilik) { pemilik ->
            PemilikCard(
                pemilik = pemilik,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        println("Card clicked for pemilik: ${pemilik.nama_pemilik}, ID: ${pemilik.idPemilik}") // Log untuk debugging
                        onDetailClick(pemilik)
                    },
                onDeleteClick = { onDeleteClick(pemilik) }
            )
        }
    }
}

@Composable
fun PemilikCard(
    pemilik: Pemilik,
    modifier: Modifier = Modifier,
    onDeleteClick: (Pemilik) -> Unit = {},
    onEditClick: (Pemilik) -> Unit = {}
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
                    text = pemilik.nama_pemilik,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.weight(1f))

                // Delete Button
                IconButton(onClick = { onDeleteClick(pemilik) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Jenis Property",
                        tint = MaterialTheme.colorScheme.error
                    )
                }

//                // Edit Button
//                IconButton(onClick = { onEditClick(pemilik) }) {
//                    Icon(
//                        imageVector = Icons.Default.Edit, // You can use a built-in edit icon or a custom one
//                        contentDescription = "Edit Property",
//                        tint = MaterialTheme.colorScheme.primary
//                    )
//                }
            }

            Divider()

            Column {
                Text(
                    text = "Nomor Kontak: ${pemilik.kontak_pemilik}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}
