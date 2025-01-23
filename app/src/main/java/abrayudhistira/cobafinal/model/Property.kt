package abrayudhistira.cobafinal.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Properti(
    val id_properti: String,
    val nama_properti: String,
    val deskripsi_properti: String,
    val lokasi: String,
    val harga: String,
    val status_properti: String,
    val id_jenis: String,  // Relasi ke JenisProperti
    val id_pemilik: String,              // Relasi ke Pemilik
    val id_manajer: String       // Relasi ke ManajerProperti
)

//@Serializable
//enum class StatusProperti {
//    Tersedia,
//    Tersewa,
//    Dijual
//}
