package abrayudhistira.cobafinal.model

import kotlinx.serialization.Serializable

@Serializable
data class JenisProperti(
    val id_jenis: Int,
    val nama_jenis: String,
    val deskripsi_jenis: String?
)
