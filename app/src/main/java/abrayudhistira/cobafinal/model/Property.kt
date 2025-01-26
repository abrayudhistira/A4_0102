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
    val id_jenis: Int,
    @SerialName("id_pemilik")
    val idPemilik: Int,
    val id_manajer: Int,
)

//@Serializable
//enum class StatusProperti {
//    Tersedia,
//    Tersewa,
//    Dijual
//}
