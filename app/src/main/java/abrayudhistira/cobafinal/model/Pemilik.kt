package abrayudhistira.cobafinal.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pemilik(
    @SerialName("id_pemilik")
    val idPemilik: Int,
    val nama_pemilik: String,
    val kontak_pemilik: String
)
