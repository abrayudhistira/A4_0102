package abrayudhistira.cobafinal.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Properti(
    @SerialName("idProperti")
    val id_properti: Int,

    val nama_properti: String,
    val deskripsi_properti: String?,
    val lokasi: String,
    val harga: Double,
    val statusProperti: StatusProperti,
    val jenisProperti: JenisProperti,  // Relasi ke JenisProperti
    val pemilik: Pemilik,              // Relasi ke Pemilik
    val manajer: ManajerProperti       // Relasi ke ManajerProperti
)

@Serializable
data class JenisProperti(
    @SerialName("idJenis")
    val id_jenis: Int,

    val nama_jenis: String,
    val deskripsi_jenis: String?
)

@Serializable
data class Pemilik(
    @SerialName("idPemilik")
    val id_pemilik: Int,

    val nama_pemilik: String,
    val kontak_pemilik: String
)

@Serializable
data class ManajerProperti(
    @SerialName("idManajer")
    val id_manajer: Int,

    val nama_manajer: String,
    val kontak_manajer: String
)
@Serializable
enum class StatusProperti {
    Tersedia,
    Tersewa,
    Dijual
}
