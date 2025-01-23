package abrayudhistira.cobafinal.model

import kotlinx.serialization.Serializable

@Serializable
data class Pemilik(
    val id_pemilik: Int,
    val nama_pemilik: String,
    val kontak_pemilik: String
)
