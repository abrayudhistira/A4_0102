package abrayudhistira.cobafinal.model

import kotlinx.serialization.Serializable

@Serializable
data class ManajerProperti(
    val id_manajer: Int,
    val nama_manajer: String,
    val kontak_manajer: String
)