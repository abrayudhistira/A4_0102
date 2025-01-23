package abrayudhistira.cobafinal.ui.property

import abrayudhistira.cobafinal.model.Properti
import abrayudhistira.cobafinal.ui.navigasi.DestinasiNavigasi
import android.content.Context
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.recyclerview.widget.RecyclerView

object DestinasiHome: DestinasiNavigasi {
    override val route = "home"
    override val titleRes = "Home Property"
}

@Composable
fun MhsCard(
    properti: Properti,
    modifier: Modifier = Modifier,
    onDeleteClick: (Properti) -> Unit = {},
    onEditClick: (Properti) -> Unit = {}
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
                    text = properti.nama_properti,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.weight(1f))

                // Delete Button
                IconButton(onClick = { onDeleteClick(properti) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Property",
                        tint = MaterialTheme.colorScheme.error
                    )
                }

                // Edit Button
                IconButton(onClick = { onEditClick(properti) }) {
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
                    text = "Lokasi: ${properti.lokasi}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Harga: ${properti.harga}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}
