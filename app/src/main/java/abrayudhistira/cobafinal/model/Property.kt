package abrayudhistira.cobafinal.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Properti(
    @SerialName("id_properti")
    val idProperti: Int,
    val nama_properti: String,
    val deskripsi_properti: String,
    val lokasi: String,
    @SerialName("harga")
    val Harga: String,
    @SerialName("status_properti")
    val statusProperti: String,
    val nama_jenis: String,
    val nama_pemilik: String,
    val nama_manajer: String,
)

//@Serializable
//enum class StatusProperti {
//    Tersedia,
//    Tersewa,
//    Dijual
//}
