package abrayudhistira.cobafinal.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JenisProperti(
    @SerialName("id_jenis")
    val idJenis: Int,
    val nama_jenis: String,
    val deskripsi_jenis: String?
)
